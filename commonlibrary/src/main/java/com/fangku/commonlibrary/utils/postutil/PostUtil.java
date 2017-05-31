package com.fangku.commonlibrary.utils.postutil;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.fangku.commonlibrary.common.UserDataUtil;
import com.fangku.commonlibrary.common.UserEntity;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.postutil.callback.Upload_CallBack;
import com.fangku.commonlibrary.utils.postutil.response.BeanResponse;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by   jie.wang
 * Date: 2016/10/9
 * Time: 22:38
 */
public class PostUtil<T> {

    public final String TAG = ">>发起网络请求<<";

    RequestCall mCall;
    Context mContext;

    public PostUtil() {

    }

    public PostUtil(Context context) {//当有需要在类似 关闭页面的关闭请求 或者关闭一个下载的时候 (长时间的连接)才使用这个构造方法
        mContext = context;
    }

    public void Cancel() {//
        OkHttpUtils.getInstance().cancelTag(mContext);
    }

    /**
     * todo      post map
     * todo 2016/10/10 json 直接解析返回实体类 参数为map
     */
    public RequestCall Post_Bean(String url, Map<String, String> params) {

        UserEntity.DataBean user = UserDataUtil.getUserInfo().getData();
        if (user != null && !TextUtils.isEmpty(user.getUserId())) {//当用户不为空并且userid不为空
            params.put("loginId", user.getUserId());
        }
        Log.i(TAG, "url:" + url);//打印URL
        if (params != null) {
            for (String key : params.keySet()) {
                Log.i(TAG, "发送的参数:" + "key= " + key + " , value= " + params.get(key));//打印参数
            }
        }


        if (mContext == null)
            mCall = OkHttpUtils.post().url(url).params(params).build();
        else
            mCall = OkHttpUtils.post().url(url).tag(mContext).params(params).build();

        return mCall;

    }

    /**
     * todo      get map
     * todo 2016/10/10 json 直接解析返回实体类 参数为map
     */
    public RequestCall Get_Bean(String url, Map<String, String> params) {

        UserEntity.DataBean user = UserDataUtil.getUserInfo().getData();
        if (user != null && !TextUtils.isEmpty(user.getUserId())) {//当用户不为空并且userid不为空
            params.put("loginId", user.getUserId());
        }
        Log.i(TAG, "url:" + url);//打印URL
        if (params != null) {
            for (String key : params.keySet()) {
                Log.i(TAG, "发送的参数:" + "key= " + key + " , value= " + params.get(key));//打印参数
            }
        }


        if (mContext == null)
            mCall = OkHttpUtils.get().url(url).params(params).build();
        else
            mCall = OkHttpUtils.get().url(url).tag(mContext).params(params).build();

        return mCall;

    }

    /**
     * todo      post url
     * todo 2016/10/10 json 直接解析返回实体类  直接URL
     */
    public RequestCall Post_Bean(String url) {

        // 添加用户校验
        UserEntity.DataBean user = UserDataUtil.getUserInfo().getData();
        if (user != null && !TextUtils.isEmpty(user.getUserId())) {
            if (!url.contains("?")) {//没有参数
                url += "?loginId=" + user.getUserId();
            } else {//带有参数
                url += "&loginId=" + user.getUserId();
            }
        }
        Log.i(TAG, "url:" + url);//打印URL


        if (mContext == null)
            mCall = OkHttpUtils.post().url(url).build();
        else
            mCall = OkHttpUtils.post().url(url).tag(mContext).build();

        return mCall;
    }

    /**
     * todo      get url
     * todo 2016/10/10 json 直接解析返回实体类  直接URL
     */
    public RequestCall Get_Bean(String url) {

        // 添加用户校验
        UserEntity.DataBean user = UserDataUtil.getUserInfo().getData();
        if (user != null && !TextUtils.isEmpty(user.getUserId())) {
            if (!url.contains("?")) {//没有参数
                url += "?loginId=" + user.getUserId();
            } else {//带有参数
                url += "&loginId=" + user.getUserId();
            }
        }
        Log.i(TAG, "url:" + url);//打印URL


        if (mContext == null)
            mCall = OkHttpUtils.get().url(url).build();
        else
            mCall = OkHttpUtils.get().url(url).tag(mContext).build();

        return mCall;
    }

    /**
     * todo  post 上传单个文件
     */
    public void Post_uploadFile(String url, String filePath, final Upload_CallBack<String> callBack) {
        File file = new File(filePath);
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        if (mContext == null)

            OkHttpUtils.post().url(url).addFile("file", fileName, file).build().execute(new StringCallback() {
                @Override
                public void inProgress(float progress, long total, int id) {
                    super.inProgress(progress, total, id);

                    callBack.inProgress(progress, total);

                }

                @Override
                public void onError(Call call, Exception e, int id) {
                    e.printStackTrace();
                    callBack.onError(e);


                }

                @Override
                public void onResponse(String response, int id) {
                    BeanResponse beanResponse = JsonMananger.jsonToBean(response, BeanResponse.class);
                    if ("200".equals(beanResponse.getCode()))
                        callBack.is200(response);
                    else
                        callBack.not200(beanResponse.getMessage());

                }
            });

        else

            OkHttpUtils.post().url(url).addFile("file", fileName, file).tag(mContext).build().execute(new StringCallback() {
                @Override
                public void inProgress(float progress, long total, int id) {
                    super.inProgress(progress, total, id);

                    callBack.inProgress(progress, total);

                }

                @Override
                public void onError(Call call, Exception e, int id) {
                    e.printStackTrace();
                    callBack.onError(e);


                }

                @Override
                public void onResponse(String response, int id) {
                    BeanResponse beanResponse = JsonMananger.jsonToBean(response, BeanResponse.class);
                    if ("200".equals(beanResponse.getCode()))
                        callBack.is200(response);
                    else
                        callBack.not200(response);

                }
            });
    }

    /**
     * todo post 上传多个文件
     * <p>
     * todo 这里注意 使用这个方法 要跟服务端同步测试是否有成功 如果不清楚就不要用 要问下:
     * todo  1.是否有开循环获取多个上传的文件的接口
     * todo  2.map里面的文件名是否需要规范
     */
    public void Post_uploadFile(String url, Map<String, File> map, final Upload_CallBack callBack) {
        if (mContext == null)

            OkHttpUtils.post().url(url).files("file", map).build().execute(new StringCallback() {
                @Override
                public void inProgress(float progress, long total, int id) {
                    super.inProgress(progress, total, id);

                    callBack.inProgress(progress, total);

                }

                @Override
                public void onError(Call call, Exception e, int id) {
                    e.printStackTrace();
                    callBack.onError(e);


                }

                @Override
                public void onResponse(String response, int id) {
                    BeanResponse beanResponse = JsonMananger.jsonToBean(response, BeanResponse.class);
                    if ("200".equals(beanResponse.getCode()))
                        callBack.is200(response);
                    else
                        callBack.not200(response);

                }
            });

        else

            OkHttpUtils.post().url(url).files("file", map).tag(mContext).build().execute(new StringCallback() {
                @Override
                public void inProgress(float progress, long total, int id) {
                    super.inProgress(progress, total, id);

                    callBack.inProgress(progress, total);

                }

                @Override
                public void onError(Call call, Exception e, int id) {
                    e.printStackTrace();
                    callBack.onError(e);


                }

                @Override
                public void onResponse(String response, int id) {
                    BeanResponse beanResponse = JsonMananger.jsonToBean(response, BeanResponse.class);
                    if ("200".equals(beanResponse.getCode()))
                        callBack.is200(response);
                    else
                        callBack.not200(response);

                }
            });


    }

    //todo 下载 不需要再封装了  这里只展示用法    复制方法里面的代码直接使用 不需要调用postutil类
//    public void DownloadFile(){
//        OkHttpUtils//
//                .get()//
//                .url(url)//
    //            .tag(this) //如果不让他在后台下载  就设置tag 在ondestroy方法里使用        OkHttpUtils.getInstance().cancelTag(this);方法
//                .build()//
//                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName) {//传入路径 和 保存为什么文件名
//                    @Override
//                    public void inProgress(float progress, long total, int id) {//显示进度
//                        super.inProgress(progress, total, id);
//                                  progress为进度   total为总大小
//                    }
//
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(File response, int id) {
//                    }
//                });
//    }


}