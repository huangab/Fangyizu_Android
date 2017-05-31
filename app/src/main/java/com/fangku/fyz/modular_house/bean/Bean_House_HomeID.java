package com.fangku.fyz.modular_house.bean;/**
 * Created by 67343 on 2016/8/22.
 */

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Bean_House_HomeID {

    /**
     * code : 200
     * message : 新增成功
     * data : {"sysId":"111111"}
     */

    private String code;
    private String message;
    /**
     * sysId : 111111
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String sysId;

        public String getSysId() {
            return sysId;
        }

        public void setSysId(String sysId) {
            this.sysId = sysId;
        }
    }
}
