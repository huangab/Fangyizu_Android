package com.fangku.fyz.modular_house.bean;

import java.util.List;

/**
 * 退租费用和时间
 * Created by bowen.ye
 * Date: 2016/9/23
 * Time: 21:38
 */
public class Bean_House_All_Tuizu_Rent {

    /**
     * code : 200
     * data : {"rde":[{"chargeType":"1","costName":"管理费","costPrice":0,"costUnit":"元/月","createDate":"2016-09-23","createTime":"21:36:19","createUser":"1470736392341zHQVF","lastDate":null,"lastScale":null,"rentId":"20160923100000000096","sysId":"20160923100000000214","thisDate":null,"thisScale":null,"totalCost":0,"totalScale":null,"updDate":null,"updTime":null,"updUser":null},{"chargeType":null,"costName":"租金","costPrice":666,"costUnit":"元/月","createDate":"2016-09-23","createTime":"21:36:19","createUser":"1470736392341zHQVF","lastDate":null,"lastScale":null,"rentId":"20160923100000000096","sysId":"20160923100000000215","thisDate":null,"thisScale":null,"totalCost":666,"totalScale":null,"updDate":null,"updTime":null,"updUser":null},{"chargeType":null,"costName":"押金","costPrice":666,"costUnit":null,"createDate":"2016-09-23","createTime":"21:36:19","createUser":"1470736392341zHQVF","lastDate":null,"lastScale":null,"rentId":"20160923100000000096","sysId":"20160923100000000216","thisDate":null,"thisScale":null,"totalCost":666,"totalScale":null,"updDate":null,"updTime":null,"updUser":null}],"re":{"contractId":"20160923100000000196","cost":1332,"createDate":"2016-09-23","createTime":"21:36:18","createUser":"1470736392341zHQVF","endDate":"2016-12-23","houseId":"11","landlordId":"1470736392341zHQVF","roomId":"20160908100000019220","roomerId":"20160923100000000042","startDate":"2016-09-23","state":"待收","sysId":"20160923100000000096","updDate":"2016-09-23","updTime":"21:36:19","updUser":"1470736392341zHQVF"}}
     * message : 数据获取成功
     */

    private String code;
    /**
     * rde : [{"chargeType":"1","costName":"管理费","costPrice":0,"costUnit":"元/月","createDate":"2016-09-23","createTime":"21:36:19","createUser":"1470736392341zHQVF","lastDate":null,"lastScale":null,"rentId":"20160923100000000096","sysId":"20160923100000000214","thisDate":null,"thisScale":null,"totalCost":0,"totalScale":null,"updDate":null,"updTime":null,"updUser":null},{"chargeType":null,"costName":"租金","costPrice":666,"costUnit":"元/月","createDate":"2016-09-23","createTime":"21:36:19","createUser":"1470736392341zHQVF","lastDate":null,"lastScale":null,"rentId":"20160923100000000096","sysId":"20160923100000000215","thisDate":null,"thisScale":null,"totalCost":666,"totalScale":null,"updDate":null,"updTime":null,"updUser":null},{"chargeType":null,"costName":"押金","costPrice":666,"costUnit":null,"createDate":"2016-09-23","createTime":"21:36:19","createUser":"1470736392341zHQVF","lastDate":null,"lastScale":null,"rentId":"20160923100000000096","sysId":"20160923100000000216","thisDate":null,"thisScale":null,"totalCost":666,"totalScale":null,"updDate":null,"updTime":null,"updUser":null}]
     * re : {"contractId":"20160923100000000196","cost":1332,"createDate":"2016-09-23","createTime":"21:36:18","createUser":"1470736392341zHQVF","endDate":"2016-12-23","houseId":"11","landlordId":"1470736392341zHQVF","roomId":"20160908100000019220","roomerId":"20160923100000000042","startDate":"2016-09-23","state":"待收","sysId":"20160923100000000096","updDate":"2016-09-23","updTime":"21:36:19","updUser":"1470736392341zHQVF"}
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
        /**
         * contractId : 20160923100000000196
         * cost : 1332.0
         * createDate : 2016-09-23
         * createTime : 21:36:18
         * createUser : 1470736392341zHQVF
         * endDate : 2016-12-23
         * houseId : 11
         * landlordId : 1470736392341zHQVF
         * roomId : 20160908100000019220
         * roomerId : 20160923100000000042
         * startDate : 2016-09-23
         * state : 待收
         * sysId : 20160923100000000096
         * updDate : 2016-09-23
         * updTime : 21:36:19
         * updUser : 1470736392341zHQVF
         */

        private ReEntity re;
        /**
         * chargeType : 1
         * costName : 管理费
         * costPrice : 0.0
         * costUnit : 元/月
         * createDate : 2016-09-23
         * createTime : 21:36:19
         * createUser : 1470736392341zHQVF
         * lastDate : null
         * lastScale : null
         * rentId : 20160923100000000096
         * sysId : 20160923100000000214
         * thisDate : null
         * thisScale : null
         * totalCost : 0.0
         * totalScale : null
         * updDate : null
         * updTime : null
         * updUser : null
         */

        private List<RdeEntity> rde;

        public ReEntity getRe() {
            return re;
        }

        public void setRe(ReEntity re) {
            this.re = re;
        }

        public List<RdeEntity> getRde() {
            return rde;
        }

        public void setRde(List<RdeEntity> rde) {
            this.rde = rde;
        }

        public static class ReEntity {
            private String contractId;
            private double cost;
            private String createDate;
            private String createTime;
            private String createUser;
            private String endDate;
            private String houseId;
            private String landlordId;
            private String roomId;
            private String roomerId;
            private String startDate;
            private String state;
            private String sysId;
            private String updDate;
            private String updTime;
            private String updUser;

            public String getContractId() {
                return contractId;
            }

            public void setContractId(String contractId) {
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
        }

        public static class RdeEntity {
            private String chargeType;
            private String costName;
            private double costPrice;
            private String costUnit;
            private String createDate;
            private String createTime;
            private String createUser;
            private Object lastDate;
            private Object lastScale;
            private String rentId;
            private String sysId;
            private Object thisDate;
            private Object thisScale;
            private double totalCost;
            private Object totalScale;
            private Object updDate;
            private Object updTime;
            private Object updUser;

            public String getChargeType() {
                return chargeType;
            }

            public void setChargeType(String chargeType) {
                this.chargeType = chargeType;
            }

            public String getCostName() {
                return costName;
            }

            public void setCostName(String costName) {
                this.costName = costName;
            }

            public double getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(double costPrice) {
                this.costPrice = costPrice;
            }

            public String getCostUnit() {
                return costUnit;
            }

            public void setCostUnit(String costUnit) {
                this.costUnit = costUnit;
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

            public Object getLastDate() {
                return lastDate;
            }

            public void setLastDate(Object lastDate) {
                this.lastDate = lastDate;
            }

            public Object getLastScale() {
                return lastScale;
            }

            public void setLastScale(Object lastScale) {
                this.lastScale = lastScale;
            }

            public String getRentId() {
                return rentId;
            }

            public void setRentId(String rentId) {
                this.rentId = rentId;
            }

            public String getSysId() {
                return sysId;
            }

            public void setSysId(String sysId) {
                this.sysId = sysId;
            }

            public Object getThisDate() {
                return thisDate;
            }

            public void setThisDate(Object thisDate) {
                this.thisDate = thisDate;
            }

            public Object getThisScale() {
                return thisScale;
            }

            public void setThisScale(Object thisScale) {
                this.thisScale = thisScale;
            }

            public double getTotalCost() {
                return totalCost;
            }

            public void setTotalCost(double totalCost) {
                this.totalCost = totalCost;
            }

            public Object getTotalScale() {
                return totalScale;
            }

            public void setTotalScale(Object totalScale) {
                this.totalScale = totalScale;
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
}
