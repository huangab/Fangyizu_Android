package com.fangku.fyz.modular_me.bean;

/**
 * Created by   jie.wang
 * Date: 2016/10/13
 * Time: 0:50
 */
public class Bean_Me_Contact {

    /**
     * code : 200
     * data : {"QQ":null,"phone":null,"weixin":null}
     * message : 获取数据成功
     */

    private String code;
    /**
     * QQ : null
     * phone : null
     * weixin : null
     */

    private DataBean data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private Object QQ;
        private Object phone;
        private Object weixin;

        public Object getQQ() {
            return QQ;
        }

        public void setQQ(Object QQ) {
            this.QQ = QQ;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getWeixin() {
            return weixin;
        }

        public void setWeixin(Object weixin) {
            this.weixin = weixin;
        }
    }
}
