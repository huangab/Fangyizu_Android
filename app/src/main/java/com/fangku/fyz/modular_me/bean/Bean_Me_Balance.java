package com.fangku.fyz.modular_me.bean;

/**
 * 余额实体类
 * Created by bowen.ye
 * Date: 2016/8/23
 * Time: 15:10
 */
public class Bean_Me_Balance {

    /**
     * code : 200
     * data : {"accountBalance":"0"}
     * message : 查询成功
     */

    private String code;
    /**
     * accountBalance : 0
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
        private String accountBalance;

        public String getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(String accountBalance) {
            this.accountBalance = accountBalance;
        }
    }
}
