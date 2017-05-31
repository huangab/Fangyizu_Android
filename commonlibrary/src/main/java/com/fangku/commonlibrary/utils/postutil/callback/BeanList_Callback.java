package com.fangku.commonlibrary.utils.postutil.callback;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;

import com.fangku.commonlibrary.common.UserDataUtil;
import com.fangku.commonlibrary.common.UserEntity;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.LogUtil;
import com.fangku.commonlibrary.utils.NetUtil;
import com.fangku.commonlibrary.utils.ToastUtil;
import com.fangku.commonlibrary.utils.postutil.json.IJsonFather;
import com.fangku.commonlibrary.utils.postutil.json.JsonChild;
import com.fangku.commonlibrary.utils.postutil.response.BeanListResponse;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 这是一个看起来比较复杂的类  返回的是data是  list<data>集合类型的实体类
 * Created by jie.wang on 2016/10/10.
 */

public abstract class BeanList_Callback<T> extends Callback<T> {

    IJsonFather jsonUtil;
    Context mContext;

    //根据这几个常量决定进入哪个方法   因为onResponse是在主线程执行  所以才这样做 省时省力
    final int SUCCESS = 1;
    final int FAILED = 0;
    final int ZERO = 2;
    int Type = -1;
    boolean isOver;
    String mMessage = "";

    public BeanList_Callback(Context context) {
        jsonUtil = new JsonChild();
        mContext = context;
    }

    /**
     * todo  各种判断在这方法里面完成
     *
     * @param response
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public T parseNetworkResponse(Response response, int id) throws IOException {

        String json = response.body().string();

        if (response.code() == 200 && json != null) {//一 丶当连接成功时候
            LogUtil.i("返回的json为", json + "");
            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];


            BeanListResponse data = JsonMananger.jsonToBean(json, BeanListResponse.class);

            //1.如果是200的话 表示成功
            if ("200".equals(data.getCode())) {

                LogUtil.i("json的Data数量", data.getData().size() + "");

                //2.当data的数据不为空的时候 返回实体类
                if (data.getData().size() != 0) {

                    T bean = jsonUtil.transform(json, entityClass); //将json转换为泛型实体类
                    Type = SUCCESS;
                    mMessage = data.getMessage();
                    isOver = true;
                    return bean;

                } else {//3.当data的数量为0 ,则返回 数量为0的方法
                    T bean = jsonUtil.transform(json, entityClass); //将json转换为泛型实体类
                    Type = ZERO;
                    mMessage = data.getMessage();
                    isOver = true;
                    return bean;
                }

            } else {
                Type = FAILED;
                mMessage = data.getMessage();
                isOver = true;
                return null;
            }


//            if (entityClass == String.class) {
//                onSuccess_Code200((T) json,data.getMessage());
//            }
        } else {//二丶当有网络 却连接失败时候
            Type = FAILED;
            mMessage = "返回参数有误";
            isOver = true;
            return null;
        }


    }


    //todo 必定实现的方法1
    //请求成功的回调
    protected abstract void onSuccess_Code200(T response, String message);


    //todo 必定实现的方法2
    //请求完的回调，可以在里面停止loaddialog
    protected abstract void onOver();


    // todo 不一定定实现的方法3
    // 当data为0 或者为空的时候的时候的回调
    protected void onSize_0() {

    }

    ;


    //todo 不一定实现的方法4
    //有网的时候出现了返回空 或者code不等于200的时候  执行的方法
    protected void onFailed(String message) {

    }

    ;


    //复写 父类的方法 使它变为不是必须实现的方法  并且在后台实现逻辑
    public void onError(Call call, Exception e, int id) {
        onFailed("error");
        onOver();

        if (!NetUtil.isConnected(mContext)) {
            ToastUtil.showShort(mContext, "网络无信号,请检查网络设置");
            return;
        }

        final BaseAnimatorSet bas_in = new BounceEnter();
        final BaseAnimatorSet bas_out = new FadeExit();
        final NormalDialog dialog = new NormalDialog(mContext);
        if ("request failed , reponse's code is : 404".equals(e.getMessage())) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    dialog//
                            .style(NormalDialog.STYLE_TWO)//
                            .contentGravity(Gravity.CENTER)
                            .btnNum(2)
                            .title("您的帐号在其他设备登录")//标题
                            .titleTextColor(Color.parseColor("#384248"))
                            .contentTextColor(Color.parseColor("#3b7299"))
                            .titleLineColor(Color.parseColor("#ffffff"))
                            .btnTextColor(Color.parseColor("#3b7299"))
                            .content("如非本人操作,请尽快修改密码.\n是否重新登录?")//正文
                            .btnText("返回", "重新登录")//按钮文字
                            .showAnim(bas_in)//
                            .dismissAnim(bas_out)//
                            .show();

                    dialog.setOnBtnClickL(new OnBtnClickL() {//返回按钮
                                              @Override
                                              public void onBtnClick() {
                                                  dialog.superDismiss();


                                              }
                                          },
                            new OnBtnClickL() {//重登陆按钮
                                @Override
                                public void onBtnClick() {

                                    UserEntity.DataBean user = UserDataUtil.getUserInfo().getData();
                                    if (user != null) {
                                        OkHttpUtils
                                                .post()
                                                .url("http://59.61.87.126:8888/fyz/api/sys/user/login/" + user.getUsername() + "/" + user.getPassword() + "/0.0.3")
                                                .build().execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                ToastUtil.showShort(mContext, "连接失败!请尝试重启程序");
                                                e.printStackTrace();
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                UserEntity entity = JsonMananger.jsonToBean(response, UserEntity.class);
                                                if ("200".equals(entity.getCode())) {
                                                    ToastUtil.showLong(mContext, entity.getMessage() + ",请重新进入本页面");

                                                } else {
                                                    ToastUtil.showShort(mContext, entity.getMessage());
                                                }
                                            }
                                        });
                                    } else {
                                        ToastUtil.showShort(mContext, "非法操作!");
                                    }


                                    dialog.superDismiss();


                                }
                            });
                }
            }, 100);
        } else

        {
            ToastUtil.showShort(mContext, "网络异常,错误代码:" + e.getMessage());

        }

        e.printStackTrace();

    }

    //复写 父类的方法 使它变为不是必须实现的方法  并且在后台实现逻辑
    public void onResponse(T response, int id) {

        switch (Type) {
            case SUCCESS:
                onSuccess_Code200(response, mMessage);
                ToastUtil.showShort(mContext, mMessage);
                if (isOver) onOver();
                break;
            case FAILED:
                onFailed(mMessage);
                ToastUtil.showShort(mContext, mMessage);
                if (isOver) onOver();
                break;
            case ZERO:
                onSize_0();
                ToastUtil.showShort(mContext, mMessage);
                if (isOver) onOver();
                break;
        }
    }

}
