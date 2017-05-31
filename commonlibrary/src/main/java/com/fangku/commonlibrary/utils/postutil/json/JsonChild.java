package com.fangku.commonlibrary.utils.postutil.json;

import com.fangku.commonlibrary.parse.JsonMananger;

/**
 * Created by jie.wang on 2016/10/10.
 */
public class JsonChild implements IJsonFather {
    JsonMananger mFastJson = new JsonMananger();
    @Override
    public <T> T transform(String response, Class<T> classOfT) {
        return mFastJson.jsonToBean(response, classOfT);
    }
}
