package com.fangku.commonlibrary.base;

/**
 * 基类View接口
 * Created by chenwei.li
 * Date: 2016-01-11
 * Time: 00:22
 */
public interface IBaseView {

     void showLoadingDialog();

     void cancelLoadingDialog();

     void showErrorMsg(String errorMsg);

}
