package com.fangku.commonlibrary;

/**
 * 网络访问中间层
 * Created by chenwei.li
 * Date: 2016/2/22
 * Time: 14:35
 */
public interface JsonCallBack {

     void onSuccess(String result);

     void onFailed(Exception e);
}
