/*
    ShengDao Android Client, JsonMananger
    Copyright (c) 2014 ShengDao Tech Company Limited
 */

package com.fangku.commonlibrary.parse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;

import java.util.List;


/**
 * JSON解析工具类
 * Created by chenwei.li
 * Date: 2015-12-11
 * Time: 23:45
 */
public class JsonMananger {

    private static final String TAG = JsonMananger.class.getSimpleName();

    static {
        TypeUtils.compatibleWithJavaBean = true;
    }

    /**
     * 将json字符串转换成java对象
     *
     * @param json
     * @param cls
     * @return
     * @throws Exception
     */
    public static <T> T jsonToBean(String json, Class<T> cls) {
        try {
            return JSON.parseObject(json, cls);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将json字符串转换成java List对象
     *
     * @param json
     * @param cls
     * @return
     * @throws Exception
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        try {
            return JSON.parseArray(json, cls);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将bean对象转化成json字符串
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String beanToJson(Object obj) {
        try {
            String result = JSON.toJSONString(obj);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
