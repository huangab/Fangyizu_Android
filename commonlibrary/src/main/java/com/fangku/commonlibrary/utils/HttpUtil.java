package com.fangku.commonlibrary.utils;

import android.text.TextUtils;
import android.util.Log;

import com.fangku.commonlibrary.JsonCallBack;
import com.fangku.commonlibrary.common.UserDataUtil;
import com.fangku.commonlibrary.common.UserEntity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Map;

import okhttp3.Call;

/**
 * 网络请求工具类
 * Created by chenwei.li
 * Date: 2016/2/21
 * Time: 23:26
 */
public class HttpUtil {

    public final String TAG = this.getClass().getSimpleName();



    /**
     * 执行Post请求返回Json字符串
     *
     * @param url
     * @param params
     * @param jsonCallBack
     */
    public void doPost(String url, Map<String, String> params, final JsonCallBack jsonCallBack) {
        // 添加用户校验
        UserEntity.DataBean user = UserDataUtil.getUserInfo().getData();
        if (user != null) {
            if (!TextUtils.isEmpty(user.getUserId())) {
                params.put("loginId", user.getUserId());
            }
        }
        //打印URL
        Log.i(TAG, "url:" + url);
        //打印参数
        for (String key : params.keySet()) {
            Log.i(TAG, "params:" + "key= " + key + " and value= " + params.get(key));
        }

        OkHttpUtils.post().url(url).params(params).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                jsonCallBack.onFailed(e);
            }

            @Override
            public void onResponse(String response, int id) {


                jsonCallBack.onSuccess(response);
            }
        });
    }


    /**
     * 执行Post请求返回Json字符串
     *
     * @param url
     * @param jsonCallBack
     */
    public void doPost(String url, final JsonCallBack jsonCallBack) {
        // 添加用户校验
        UserEntity.DataBean user = UserDataUtil.getUserInfo().getData();
        if (user != null) {
            if (!TextUtils.isEmpty(user.getUserId())) {
                if (!url.contains("?")) {
                    //没有参数
                    url += "?loginId=" + user.getUserId();
                } else {
                    //带有参数
                    url += "&loginId=" + user.getUserId();
                }
            }
        }
        //打印URL
        Log.i(TAG, "url:" + url);

        OkHttpUtils.post().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                jsonCallBack.onFailed(e);
            }

            @Override
            public void onResponse(String response, int id) {
                jsonCallBack.onSuccess(response);
            }
        });
    }

    /**
     * 执行Get请求返回Json字符串
     *
     * @param url
     * @param jsonCallBack
     */
    public void doGet(String url, final JsonCallBack jsonCallBack) {
        // 添加用户校验
        UserEntity.DataBean user = UserDataUtil.getUserInfo().getData();
        if (user != null) {
            if (!TextUtils.isEmpty(user.getUserId())) {
                if (!url.contains("?")) {
                    //没有参数
                    url += "?loginId=" + user.getUserId();
                } else {
                    //带有参数
                    url += "&loginId=" + user.getUserId();
                }
            }
        }
        //打印URL
        Log.i(TAG, "url:" + url);

        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                jsonCallBack.onFailed(e);
            }

            @Override
            public void onResponse(String response, int id) {
                jsonCallBack.onSuccess(response);
            }
        });
    }


    public void doGet(String url, Map<String, String> params, final JsonCallBack jsonCallBack) {
        // 添加用户校验
        UserEntity.DataBean user = UserDataUtil.getUserInfo().getData();
        if (user != null) {
            if (!TextUtils.isEmpty(user.getUserId())) {
                params.put("loginId", user.getUserId());
            }
        }
        //打印URL
        Log.i(TAG, "url:" + url);
        //打印参数
        for (String key : params.keySet()) {
            Log.i(TAG, "params:" + "key= " + key + " and value= " + params.get(key));
        }

        OkHttpUtils.get().url(url).params(params).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                jsonCallBack.onFailed(e);
            }

            @Override
            public void onResponse(String response, int id) {
                jsonCallBack.onSuccess(response);
            }
        });
    }


    /**
     * 基于Http表单形式上传文件
     *
     * @param url
     * @param filePath
     * @param jsonCallBack
     */
    public void uploadFile(String url, String filePath, final JsonCallBack jsonCallBack) {
        File file = new File(filePath);
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        OkHttpUtils.post().url(url).addFile("file", fileName, file).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                jsonCallBack.onFailed(e);
            }

            @Override
            public void onResponse(String response, int id) {
                jsonCallBack.onSuccess(response);
            }
        });
    }


    /**
     * 执行文件下载操作
     *
     * @param url
     * @param fileName
     * @param jsonCallBack
     */
    public void downLoadFile(String url, String fileName, final JsonCallBack jsonCallBack) {
        OkHttpUtils.get().url(url).build().execute(new FileCallBack(StorageUtil.DOWNLOAD_DIR, fileName) {

            @Override
            public void onError(Call call, Exception e, int id) {
                jsonCallBack.onFailed(e);
            }

            @Override
            public void onResponse(File response, int id) {
                jsonCallBack.onSuccess(response.getAbsolutePath());
            }

        });



    }






}
