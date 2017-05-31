package com.fangku.fyz.modular_house.bean;

import java.util.List;

/**
 * 收租记录
 * Created by   jie.wang
 * Date: 2016/9/22
 * Time: 9:41
 */
public class Bean_House_All_History {


    /**
     * code : 200
     * data : {"contractId":null,"cost":1260,"createDate":"2016-09-21","createTime":"16:15:24","createUser":"1470736392341zHQVF","endDate":"2016-12-21","houseId":"11","landlordId":"1470736392341zHQVF","rentType":null,"roomId":"20160810100000000002","roomerId":"14715099776361VBdV","startDate":"2016-09-21","state":"待收","sysId":"20160921100000000071","updDate":null,"updTime":null,"updUser":null}
     * message : 数据查询成功
     */

    private String code;
    /**
     * contractId : null
     * cost : 1260.0
     * createDate : 2016-09-21
     * createTime : 16:15:24
     * createUser : 1470736392341zHQVF
     * endDate : 2016-12-21
     * houseId : 11
     * landlordId : 1470736392341zHQVF
     * rentType : null
     * roomId : 20160810100000000002
     * roomerId : 14715099776361VBdV
     * startDate : 2016-09-21
     * state : 待收
     * sysId : 20160921100000000071
     * updDate : null
     * updTime : null
     * updUser : null
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
        private Object contractId;
        private double cost;
        private String createDate;
        private String createTime;
        private String createUser;
        private String endDate;
        private String houseId;
        private String landlordId;
        private Object rentType;
        private String roomId;
        private String roomerId;
        private String startDate;
        private String state;
        private String sysId;
        private Object updDate;
        private Object updTime;
        private Object updUser;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        private String realName;

        public Object getContractId() {
            return contractId;
        }

        public void setContractId(Object contractId) {
            this.contractId = contractId;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
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

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getHouseId() {
            return houseId;
        }

        public void setHouseId(String houseId) {
            this.houseId = houseId;
        }

        public String getLandlordId() {
            return landlordId;
        }

        public void setLandlordId(String landlordId) {
            this.landlordId = landlordId;
        }

        public Object getRentType() {
            return rentType;
        }

        public void setRentType(Object rentType) {
            this.rentType = rentType;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getRoomerId() {
            return roomerId;
        }

        public void setRoomerId(String roomerId) {
            this.roomerId = roomerId;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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
    }
}
