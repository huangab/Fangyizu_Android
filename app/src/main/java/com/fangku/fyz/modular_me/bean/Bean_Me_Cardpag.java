package com.fangku.fyz.modular_me.bean;

import java.util.List;

/**
 * Created by   jie.wang
 * Date: 2016/8/22
 * Time: 17:04
 */
public class Bean_Me_Cardpag {

    /**
     * code : 200
     * data : {"amount":null,"cardNo":null,"cardTitle":"抵用券","createDate":null,"createTime":"","createUser":null,"demo":"三折","endDate":"2017-1-8","startDate":"2016-8-7","sysId":"1","updDate":null,"updTime":null,"updUser":null,"useCondition":null,"useType":null,"userId":"1470736392341zHQVF"}
     * message : 查询成功
     */

    private String code;
    /**
     * amount : null
     * cardNo : null
     * cardTitle : 抵用券
     * createDate : null
     * createTime :
     * createUser : null
     * demo : 三折
     * endDate : 2017-1-8
     * startDate : 2016-8-7
     * sysId : 1
     * updDate : null
     * updTime : null
     * updUser : null
     * useCondition : null
     * useType : null
     * userId : 1470736392341zHQVF
     */

    private List<DataBean> data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private Object amount;
        private Object cardNo;
        private String cardTitle;
        private Object createDate;
        private String createTime;
        private Object createUser;
        private String demo;
        private String endDate;
        private String startDate;
        private String sysId;
        private Object updDate;
        private Object updTime;
        private Object updUser;
        private Object useCondition;
        private Object useType;
        private String userId;

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
        }

        public Object getCardNo() {
            return cardNo;
        }

        public void setCardNo(Object cardNo) {
            this.cardNo = cardNo;
        }

        public String getCardTitle() {
            return cardTitle;
        }

        public void setCardTitle(String cardTitle) {
            this.cardTitle = cardTitle;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getCreateUser() {
            return createUser;
        }

        public void setCreateUser(Object createUser) {
            this.createUser = createUser;
        }

        public String getDemo() {
            return demo;
        }

        public void setDemo(String demo) {
            this.demo = demo;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getSysId() {
            return sysId;
        }

        public void setSysId(String sysId) {
            this.sysId = sysId;
        }

        public Object getUpdDate() {
            return updDate;
        }

        public void setUpdDate(Object updDate) {
            this.updDate = updDate;
        }

        public Object getUpdTime() {
            return updTime;
        }

        public void setUpdTime(Object updTime) {
            this.updTime = updTime;
        }

        public Object getUpdUser() {
            return updUser;
        }

        public void setUpdUser(Object updUser) {
            this.updUser = updUser;
        }

        public Object getUseCondition() {
            return useCondition;
        }

        public void setUseCondition(Object useCondition) {
            this.useCondition = useCondition;
        }

        public Object getUseType() {
            return useType;
        }

        public void setUseType(Object useType) {
            this.useType = useType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
