package com.fangku.commonlibrary.utils.postutil.response;

/**
 * 基类Http响应流
 * Created by chenwei.li
 * Date: 2016-01-04
 * Time: 00:15
 */
public class BeanResponse<T> {

    private String code;
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;





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
        return "BeanResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
