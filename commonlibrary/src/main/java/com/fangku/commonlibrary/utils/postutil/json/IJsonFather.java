package com.fangku.commonlibrary.utils.postutil.json;

/**
 * Created by jie.wang on 2016/10/10.
 */
public interface IJsonFather {
    <T> T transform(String response, Class<T> classOfT);
}
