package com.fangku.fyz.constant;

import android.content.Context;

import com.fangku.commonlibrary.utils.postutil.PostUtil;
import com.fangku.commonlibrary.utils.postutil.callback.BeanList_Callback;
import com.fangku.fyz.MyApplication;
import com.fangku.fyz.modular_house.bean.Bean_House_Data;
import com.fangku.fyz.util.Only_CallBack;

/**
 * Created by bowen.ye
 * Date: 2016/7/12
 * Time: 17:32
 */
public class Static_Login {
    public static long codeTime = 120000;

    /**
     * 获取房产列表
     * 只在登录成功之后,添加房产 ,删除房产3种操作成功之后获取  其他时候不获取
     *
     * @param
     */
    public static void updateHouseName(Context context, Only_CallBack callBack) {


        PostUtil postUtil = new PostUtil();
        postUtil.Post_Bean(Url_final.GETHOUSEDATA)
                .execute(new BeanList_Callback<Bean_House_Data>(context) {
                    @Override
                    protected void onSuccess_Code200(Bean_House_Data response, String message) {
                        String a[] = new String[response.getData().size()];
                        String id[] = new String[response.getData().size()];
                        for (int i = 0; i < response.getData().size(); i++) {
                            a[i] = response.getData().get(i).getHouseName();
                            id[i] = response.getData().get(i).getSysId();
                        }
                        MyApplication.stringItems = a;
                        MyApplication.houseSysID = id;
                        callBack.isSuccess("成功");
                    }

                    @Override
                    protected void onOver() {
                    }

                    @Override
                    protected void onSize_0() {
                        callBack.isSuccess("空");
                    }

                    @Override
                    protected void onFailed(String message) {
                        MyApplication.stringItems = new String[]{};
                        MyApplication.houseSysID = new String[]{};
                        callBack.isSuccess("失败");
                    }
                });

    }

}
