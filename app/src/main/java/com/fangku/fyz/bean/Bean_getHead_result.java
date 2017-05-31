package com.fangku.fyz.bean;

/**
 * 获取头像返回的头像编号
 * Created by   jie.wang
 * Date: 2016/7/13
 * Time: 16:32
 */
public class Bean_getHead_result {

    /**
     * code : 200
     * data : {"sysId":"20160714100000000280"}
     * message : 上传文件成功
     */
    private String code;
    /**
     * sysId : 20160714100000000280
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
        private String sysId;

        public String getSysId() {
            return sysId;
        }

        public void setSysId(String sysId) {
            this.sysId = sysId;
        }
    }
}
