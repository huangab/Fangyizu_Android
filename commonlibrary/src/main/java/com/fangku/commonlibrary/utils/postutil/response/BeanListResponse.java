package com.fangku.commonlibrary.utils.postutil.response;

import java.util.List;

/**
 * 基类Http响应流
 * Created by chenwei.li
 * Date: 2016-01-04
 * Time: 00:15
 */
public class BeanListResponse<T> {

    private String code;
    private String message;
    private List<T> data;


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


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
