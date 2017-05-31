package com.fangku.commonlibrary.utils.postutil.callback;

/**
 * Created by   jie.wang
 * Date: 2016/10/14
 * Time: 17:23
 */
public interface Upload_CallBack<T> {

    void inProgress(float progress, long total);

    void onError(Exception e);

    void is200(T response);//返回成功 code是200

    void not200(String message); //返回成功 但是code不是200
}