package com.fangku.commonlibrary.common;

/**
 * 用户信息实体类(需要对应不同的项目进行对应改动)
 * Create by: chenwei.li
 * Date: 2016-06-21
 * time: 15:30
 * Email: lichenwei.me@foxmail.com
 */
public class UserEntity {


    /**
     * code : 200
     * data : {"accountBalance":null,"checkCode":null,"imageId":"160809100000018ikRd1","jiFen":null,"locationCheck":"0","name":"房东5567","token":null,"userId":"1470672554118fhn4Y","username":"15606095567"}
     * message : 登陆成功
     */

    private String code;
    /**
     * accountBalance : null
     * checkCode : null
     * imageId : 160809100000018ikRd1
     * jiFen : null
     * locationCheck : 0
     * name : 房东5567
     * token : null
     * userId : 1470672554118fhn4Y
     * username : 15606095567
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
        private Object accountBalance;
        private Object checkCode;
        private String imageId;
        private Object jiFen;
        private String locationCheck;
        private String name;
        private Object token;
        private String userId;
        private String username;


        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        private String password;


        public Object getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(Object accountBalance) {
            this.accountBalance = accountBalance;
        }

        public Object getCheckCode() {
            return checkCode;
        }

        public void setCheckCode(Object checkCode) {
            this.checkCode = checkCode;
        }

        public String getImageId() {
            return imageId;
        }

        public void setImageId(String imageId) {
            this.imageId = imageId;
        }

        public Object getJiFen() {
            return jiFen;
        }

        public void setJiFen(Object jiFen) {
            this.jiFen = jiFen;
        }

        public String getLocationCheck() {
            return locationCheck;
        }

        public void setLocationCheck(String locationCheck) {
            this.locationCheck = locationCheck;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "accountBalance=" + accountBalance +
                    ", checkCode=" + checkCode +
                    ", imageId='" + imageId + '\'' +
                    ", jiFen=" + jiFen +
                    ", locationCheck='" + locationCheck + '\'' +
                    ", name='" + name + '\'' +
                    ", token=" + token +
                    ", userId='" + userId + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
