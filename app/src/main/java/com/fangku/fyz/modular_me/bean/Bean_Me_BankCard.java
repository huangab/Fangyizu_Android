package com.fangku.fyz.modular_me.bean;

import java.util.List;

/**
 * Created by   jie.wang
 * Date: 2016/8/22
 * Time: 11:01
 */
public class Bean_Me_BankCard {


    /**
     * code : 200
     * data : [{"cardNo":"1469498309126Kp8kA","cardType":"123456789","createDate":"2016-08-18","createTime":"20:38:44","createUser":"1469498309126Kp8kA","demo":"信用卡","mobile":"","name":"132859266555","sysId":"20160818100000000023","time":"朱","updDate":"","updTime":"","updUser":"","userId":"1470736392341zHQVF","validNo":"2017-09-09"},{"cardNo":"1469498309126Kp8kA","cardType":"123456789","createDate":"2016-08-19","createTime":"11:18:50","createUser":"1469498309126Kp8kA","demo":"信用卡","mobile":"建行","name":"132859266555","sysId":"20160819100000000024","time":"朱","updDate":"","updTime":"","updUser":"","userId":"1470736392341zHQVF","validNo":"2017"},{"cardNo":"1234123412341234","cardType":"金卡","createDate":"2016-08-21","createTime":"17:06:34","createUser":"1470736392341zHQVF","demo":null,"mobile":"15606091536","name":"石志红","sysId":"20160821100000000025","time":null,"updDate":null,"updTime":null,"updUser":null,"userId":"1470736392341zHQVF","validNo":"金卡"},{"cardNo":"1234123412341234","cardType":"金卡","createDate":"2016-08-21","createTime":"17:31:21","createUser":"1470736392341zHQVF","demo":null,"mobile":"15606091536","name":"石志红","sysId":"20160821100000000027","time":null,"updDate":null,"updTime":null,"updUser":null,"userId":"1470736392341zHQVF","validNo":"金卡"}]
     * message : 查询成功
     */

    private String code;
    private String message;
    /**
     * cardNo : 1469498309126Kp8kA
     * cardType : 123456789
     * createDate : 2016-08-18
     * createTime : 20:38:44
     * createUser : 1469498309126Kp8kA
     * demo : 信用卡
     * mobile :
     * name : 132859266555
     * sysId : 20160818100000000023
     * time : 朱
     * updDate :
     * updTime :
     * updUser :
     * userId : 1470736392341zHQVF
     * validNo : 2017-09-09
     */

    private List<DataDataBean> data;

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

    public List<DataDataBean> getData() {
        return data;
    }

    public void setData(List<DataDataBean> data) {
        this.data = data;
    }

    public static class DataDataBean {
        private String cardNo;
        private String cardType;
        private String createDate;
        private String createTime;
        private String createUser;
        private String demo;
        private String mobile;
        private String name;
        private String sysId;
        private String time;
        private String updDate;
        private String updTime;
        private String updUser;
        private String userId;
        private String validNo;

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getDemo() {
            return demo;
        }

        public void setDemo(String demo) {
            this.demo = demo;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSysId() {
            return sysId;
        }

        public void setSysId(String sysId) {
            this.sysId = sysId;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUpdDate() {
            return updDate;
        }

        public void setUpdDate(String updDate) {
            this.updDate = updDate;
        }

        public String getUpdTime() {
            return updTime;
        }

        public void setUpdTime(String updTime) {
            this.updTime = updTime;
        }

        public String getUpdUser() {
            return updUser;
        }

        public void setUpdUser(String updUser) {
            this.updUser = updUser;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getValidNo() {
            return validNo;
        }

        public void setValidNo(String validNo) {
            this.validNo = validNo;
        }
    }
}
