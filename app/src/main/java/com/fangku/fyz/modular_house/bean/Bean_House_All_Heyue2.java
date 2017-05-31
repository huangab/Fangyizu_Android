package com.fangku.fyz.modular_house.bean;

/**
 * Created by bowen.ye
 * Date: 2016/9/23
 * Time: 19:29
 */
public class Bean_House_All_Heyue2 {

    /**
     * code : 200
     * data : {"cID":"20160923100000000188"}
     * message : 数据存储成功
     */

    private String code;
    /**
     * cID : 20160923100000000188
     */

    private DataEntity data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataEntity {
        private String cID;

        public String getCID() {
            return cID;
        }

        public void setCID(String cID) {
            this.cID = cID;
        }
    }
}
