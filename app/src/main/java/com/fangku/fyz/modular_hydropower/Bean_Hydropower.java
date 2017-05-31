package com.fangku.fyz.modular_hydropower;

import java.util.List;

/**
 * Created by bowen.ye
 * Date: 2016/8/17
 * Time: 23:12
 */
public class Bean_Hydropower {

    /**
     * code : 200
     * data : [{"createDate":"2016-08-12","createTime":"15:54:42","createUser":"1470709709029XSt9z","demo":"可能会存在换表的情况，换表说明需要学在这里","flag":"E","houseId":"11","lastDate":"2016-08-12","lastScale":0,"meterName":"电表","meterType":"W","roomId":"1","state":"未收","sysId":"20160812100000000013","thisDate":"2016-6-11","thisScale":5,"updDate":null,"updTime":null,"updUser":null},{"createDate":"2016-08-12","createTime":"15:53:34","createUser":"1470709709029XSt9z","demo":"可能会存在换表的情况，换表说明需要学在这里","flag":"E","houseId":"11","lastDate":"2016-6-1","lastScale":5,"meterName":"电表","meterType":"W","roomId":"10","state":"未收","sysId":"20160812100000000010","thisDate":"2016-6-11","thisScale":5,"updDate":null,"updTime":null,"updUser":null},{"createDate":"2016-08-12","createTime":"15:54:17","createUser":"1470709709029XSt9z","demo":"可能会存在换表的情况，换表说明需要学在这里","flag":"E","houseId":"11","lastDate":"2016-08-12","lastScale":0,"meterName":"电表","meterType":"W","roomId":"11","state":"未收","sysId":"20160812100000000011","thisDate":"2016-6-11","thisScale":5,"updDate":null,"updTime":null,"updUser":null},{"createDate":"2016-08-12","createTime":"15:54:20","createUser":"1470709709029XSt9z","demo":"可能会存在换表的情况，换表说明需要学在这里","flag":"E","houseId":"11","lastDate":"2016-08-12","lastScale":0,"meterName":"电表","meterType":"W","roomId":"12","state":"未收","sysId":"20160812100000000012","thisDate":"2016-6-11","thisScale":5,"updDate":null,"updTime":null,"updUser":null},{"createDate":"2016-08-12","createTime":"15:43:16","createUser":"1470709709029XSt9z","demo":"可能会存在换表的情况，换表说明需要学在这里","flag":"E","houseId":"11","lastDate":"2016-6-1","lastScale":5,"meterName":"电表","meterType":"W","roomId":"13","state":"已收","sysId":"20160812100000000003","thisDate":"2016-6-11","thisScale":5,"updDate":null,"updTime":null,"updUser":null},{"createDate":"2016-08-12","createTime":"15:43:16","createUser":"1470709709029XSt9z","demo":"可能会存在换表的情况，换表说明需要学在这里","flag":"E","houseId":"11","lastDate":"2016-6-1","lastScale":5,"meterName":"电表","meterType":"W","roomId":"4","state":"未收","sysId":"20160812100000000004","thisDate":"2016-6-11","thisScale":5,"updDate":null,"updTime":null,"updUser":null},{"createDate":"2016-08-12","createTime":"15:48:30","createUser":"1470709709029XSt9z","demo":"可能会存在换表的情况，换表说明需要学在这里","flag":"E","houseId":"11","lastDate":"2016-6-1","lastScale":5,"meterName":"电表","meterType":"W","roomId":"5","state":"未收","sysId":"20160812100000000005","thisDate":"2016-6-11","thisScale":5,"updDate":null,"updTime":null,"updUser":null},{"createDate":"2016-08-12","createTime":"15:48:41","createUser":"1470709709029XSt9z","demo":"可能会存在换表的情况，换表说明需要学在这里","flag":"E","houseId":"11","lastDate":"2016-6-1","lastScale":5,"meterName":"电表","meterType":"W","roomId":"6","state":"未收","sysId":"20160812100000000006","thisDate":"2016-6-11","thisScale":5,"updDate":null,"updTime":null,"updUser":null},{"createDate":"2016-08-12","createTime":"15:53:22","createUser":"1470709709029XSt9z","demo":"可能会存在换表的情况，换表说明需要学在这里","flag":"E","houseId":"11","lastDate":"2016-6-1","lastScale":5,"meterName":"电表","meterType":"W","roomId":"7","state":"未收","sysId":"20160812100000000007","thisDate":"2016-6-11","thisScale":5,"updDate":null,"updTime":null,"updUser":null},{"createDate":"2016-08-12","createTime":"15:53:23","createUser":"1470709709029XSt9z","demo":"可能会存在换表的情况，换表说明需要学在这里","flag":"E","houseId":"11","lastDate":"2016-6-1","lastScale":5,"meterName":"电表","meterType":"W","roomId":"8","state":"未收","sysId":"20160812100000000008","thisDate":"2016-6-11","thisScale":5,"updDate":null,"updTime":null,"updUser":null},{"createDate":"2016-08-12","createTime":"15:53:34","createUser":"1470709709029XSt9z","demo":"可能会存在换表的情况，换表说明需要学在这里","flag":"E","houseId":"11","lastDate":"2016-6-1","lastScale":5,"meterName":"电表","meterType":"W","roomId":"9","state":"未收","sysId":"20160812100000000009","thisDate":"2016-6-11","thisScale":5,"updDate":null,"updTime":null,"updUser":null}]
     * message : 查询成功
     */

    private String code;
    private String message;


    /**
     * createDate : 2016-08-12
     * createTime : 15:54:42
     * createUser : 1470709709029XSt9z
     * demo : 可能会存在换表的情况，换表说明需要学在这里
     * flag : E
     * houseId : 11
     * lastDate : 2016-08-12
     * lastScale : 0.0
     * meterName : 电表
     * meterType : W
     * roomId : 1
     * state : 未收
     * sysId : 20160812100000000013
     * thisDate : 2016-6-11
     * thisScale : 5.0
     * updDate : null
     * updTime : null
     * updUser : null
     */

    private List<DataEntity> data;

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

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        private String createDate;
        private String createTime;
        private String createUser;
        private String demo;
        private String flag;
        private String houseId;
        private String lastDate;
        private double lastScale;
        private String meterName;
        private String meterType;
        private String roomId;
        private String roomNo;
        private String state;
        private String sysId;
        private String thisDate;
        private double thisScale;
        private Object updDate;
        private Object updTime;
        private Object updUser;
        private double accScale;
        private int orderBy;

        //点击标识
        boolean isClick;
        //是否输入新值判断
        String isRevise;
        //输错越界标识
        String isInput;

        public int getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(int orderBy) {
            this.orderBy = orderBy;
        }

        public String getRoomNo() {
            return roomNo;
        }

        public void setRoomNo(String roomNo) {
            this.roomNo = roomNo;
        }

        public double getAccScale() {
            return accScale;
        }

        public void setAccScale(double accScale) {
            this.accScale = accScale;
        }

        public String isInput() {
            return isInput;
        }

        public void setIsInput(String isOpen) {
            this.isInput = isOpen;
        }

        public String isRevise() {
            return isRevise;
        }

        public void setIsRevise(String isRevise) {
            this.isRevise = isRevise;
        }

        public boolean getIsClick() {
            return isClick;
        }

        public void setIsClick(boolean isClick) {
            this.isClick = isClick;
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

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getHouseId() {
            return houseId;
        }

        public void setHouseId(String houseId) {
            this.houseId = houseId;
        }

        public String getLastDate() {
            return lastDate;
        }

        public void setLastDate(String lastDate) {
            this.lastDate = lastDate;
        }

        public double getLastScale() {
            return lastScale;
        }

        public void setLastScale(double lastScale) {
            this.lastScale = lastScale;
        }

        public String getMeterName() {
            return meterName;
        }

        public void setMeterName(String meterName) {
            this.meterName = meterName;
        }

        public String getMeterType() {
            return meterType;
        }

        public void setMeterType(String meterType) {
            this.meterType = meterType;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
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

        public String getThisDate() {
            return thisDate;
        }

        public void setThisDate(String thisDate) {
            this.thisDate = thisDate;
        }

        public double getThisScale() {
            return thisScale;
        }

        public void setThisScale(double thisScale) {
            this.thisScale = thisScale;
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

        @Override
        public String toString() {
            return "DataEntity{" +
                    "createDate='" + createDate + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", createUser='" + createUser + '\'' +
                    ", demo='" + demo + '\'' +
                    ", flag='" + flag + '\'' +
                    ", houseId='" + houseId + '\'' +
                    ", lastDate='" + lastDate + '\'' +
                    ", lastScale=" + lastScale +
                    ", meterName='" + meterName + '\'' +
                    ", meterType='" + meterType + '\'' +
                    ", roomId='" + roomId + '\'' +
                    ", roomNo='" + roomNo + '\'' +
                    ", state='" + state + '\'' +
                    ", sysId='" + sysId + '\'' +
                    ", thisDate='" + thisDate + '\'' +
                    ", thisScale=" + thisScale +
                    ", updDate=" + updDate +
                    ", updTime=" + updTime +
                    ", updUser=" + updUser +
                    ", accScale=" + accScale +
                    ", orderBy=" + orderBy +
                    ", isClick=" + isClick +
                    ", isRevise='" + isRevise + '\'' +
                    ", isInput='" + isInput + '\'' +
                    '}';
        }
    }


}
