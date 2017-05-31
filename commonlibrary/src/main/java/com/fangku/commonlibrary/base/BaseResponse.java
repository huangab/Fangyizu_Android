package com.fangku.commonlibrary.base;

/**
 * 基类Http响应流
 * Created by chenwei.li
 * Date: 2016-01-04
 * Time: 00:15
 */
public class BaseResponse {

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
