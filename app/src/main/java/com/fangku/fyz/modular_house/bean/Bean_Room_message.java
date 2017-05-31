package com.fangku.fyz.modular_house.bean;

import java.util.List;

/**   房间信息
 * Created by   jie.wang
 * Date: 2016/9/23
 * Time: 15:36
 */
public class Bean_Room_message {


    /**
     * code : 200
     * data : {"fj":{"checkDate":"2016-09-08","createDate":"2016-09-08","createTime":"15:36:18","createUser":"1470736392341zHQVF","depositNumber":1,"hallNumber":1,"houseId":"11","houseLeve":1,"image1":null,"image2":null,"image3":null,"image4":null,"image5":null,"image6":null,"image7":null,"image8":null,"image9":null,"mating":"","orderBy":null,"payNumber":1,"rent":0,"roomDesc":null,"roomNo":"蘑菇","roomNumber":1,"roomTitle":null,"state":"已出租","sysId":"20160908100000019218","toiletNumber":1,"updDate":"2016-09-23","updTime":"09:43:49","updUser":"1470736392341zHQVF","userId":"1470736392341zHQVF"},"fy":[{"chargeType":"2","contractId":null,"costName":"水表","costPrice":5,"costUnit":"元/方","createDate":"2016-09-23","createTime":"09:43:49","createUser":"1470736392341zHQVF","houseId":"11","maxValue":null,"meterCode":null,"minValue":0,"roomId":"20160908100000019218","sysId":"20160923100000000206","updDate":null,"updTime":null,"updUser":null},{"chargeType":"2","contractId":null,"costName":"电表","costPrice":1.5,"costUnit":"元/度","createDate":"2016-09-23","createTime":"09:43:49","createUser":"1470736392341zHQVF","houseId":"11","maxValue":null,"meterCode":null,"minValue":0,"roomId":"20160908100000019218","sysId":"20160923100000000207","updDate":null,"updTime":null,"updUser":null},{"chargeType":"1","contractId":null,"costName":"管理费","costPrice":0,"costUnit":"元/月","createDate":"2016-09-23","createTime":"09:43:49","createUser":"1470736392341zHQVF","houseId":"11","maxValue":null,"meterCode":null,"minValue":0,"roomId":"20160908100000019218","sysId":"20160923100000000208","updDate":null,"updTime":null,"updUser":null}]}
     * message : 数据获取成功
     */

    private String code;
    /**
     * fj : {"checkDate":"2016-09-08","createDate":"2016-09-08","createTime":"15:36:18","createUser":"1470736392341zHQVF","depositNumber":1,"hallNumber":1,"houseId":"11","houseLeve":1,"image1":null,"image2":null,"image3":null,"image4":null,"image5":null,"image6":null,"image7":null,"image8":null,"image9":null,"mating":"","orderBy":null,"payNumber":1,"rent":0,"roomDesc":null,"roomNo":"蘑菇","roomNumber":1,"roomTitle":null,"state":"已出租","sysId":"20160908100000019218","toiletNumber":1,"updDate":"2016-09-23","updTime":"09:43:49","updUser":"1470736392341zHQVF","userId":"1470736392341zHQVF"}
     * fy : [{"chargeType":"2","contractId":null,"costName":"水表","costPrice":5,"costUnit":"元/方","createDate":"2016-09-23","createTime":"09:43:49","createUser":"1470736392341zHQVF","houseId":"11","maxValue":null,"meterCode":null,"minValue":0,"roomId":"20160908100000019218","sysId":"20160923100000000206","updDate":null,"updTime":null,"updUser":null},{"chargeType":"2","contractId":null,"costName":"电表","costPrice":1.5,"costUnit":"元/度","createDate":"2016-09-23","createTime":"09:43:49","createUser":"1470736392341zHQVF","houseId":"11","maxValue":null,"meterCode":null,"minValue":0,"roomId":"20160908100000019218","sysId":"20160923100000000207","updDate":null,"updTime":null,"updUser":null},{"chargeType":"1","contractId":null,"costName":"管理费","costPrice":0,"costUnit":"元/月","createDate":"2016-09-23","createTime":"09:43:49","createUser":"1470736392341zHQVF","houseId":"11","maxValue":null,"meterCode":null,"minValue":0,"roomId":"20160908100000019218","sysId":"20160923100000000208","updDate":null,"updTime":null,"updUser":null}]
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
        /**
         * checkDate : 2016-09-08
         * createDate : 2016-09-08
         * createTime : 15:36:18
         * createUser : 1470736392341zHQVF
         * depositNumber : 1
         * hallNumber : 1
         * houseId : 11
         * houseLeve : 1
         * image1 : null
         * image2 : null
         * image3 : null
         * image4 : null
         * image5 : null
         * image6 : null
         * image7 : null
         * image8 : null
         * image9 : null
         * mating :
         * orderBy : null
         * payNumber : 1
         * rent : 0.0
         * roomDesc : null
         * roomNo : 蘑菇
         * roomNumber : 1
         * roomTitle : null
         * state : 已出租
         * sysId : 20160908100000019218
         * toiletNumber : 1
         * updDate : 2016-09-23
         * updTime : 09:43:49
         * updUser : 1470736392341zHQVF
         * userId : 1470736392341zHQVF
         */

        private FjBean fj;
        /**
         * chargeType : 2
         * contractId : null
         * costName : 水表
         * costPrice : 5.0
         * costUnit : 元/方
         * createDate : 2016-09-23
         * createTime : 09:43:49
         * createUser : 1470736392341zHQVF
         * houseId : 11
         * maxValue : null
         * meterCode : null
         * minValue : 0
         * roomId : 20160908100000019218
         * sysId : 20160923100000000206
         * updDate : null
         * updTime : null
         * updUser : null
         */

        private List<FyBean> fy;

        public FjBean getFj() {
            return fj;
        }

        public void setFj(FjBean fj) {
            this.fj = fj;
        }

        public List<FyBean> getFy() {
            return fy;
        }

        public void setFy(List<FyBean> fy) {
            this.fy = fy;
        }

        public static class FjBean {
            private String checkDate;
            private String createDate;
            private String createTime;
            private String createUser;
            private Integer depositNumber;
            private int hallNumber;
            private String houseId;
            private int houseLeve;
            private Object image1;
            private Object image2;
            private Object image3;
            private Object image4;
            private Object image5;
            private Object image6;
            private Object image7;
            private Object image8;
            private Object image9;
            private String mating;
            private Object orderBy;
            private int payNumber;
            private double rent;
            private Object roomDesc;
            private String roomNo;
            private int roomNumber;
            private Object roomTitle;
            private String state;
            private String sysId;
            private int toiletNumber;
            private String updDate;
            private String updTime;
            private String updUser;
            private String userId;

            public String getCheckDate() {
                return checkDate;
            }

            public void setCheckDate(String checkDate) {
                this.checkDate = checkDate;
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

            public int getDepositNumber() {
                return depositNumber;
            }

            public void setDepositNumber(int depositNumber) {
                this.depositNumber = depositNumber;
            }

            public int getHallNumber() {
                return hallNumber;
            }

            public void setHallNumber(int hallNumber) {
                this.hallNumber = hallNumber;
            }

            public String getHouseId() {
                return houseId;
            }

            public void setHouseId(String houseId) {
                this.houseId = houseId;
            }

            public int getHouseLeve() {
                return houseLeve;
            }

            public void setHouseLeve(int houseLeve) {
                this.houseLeve = houseLeve;
            }

            public Object getImage1() {
                return image1;
            }

            public void setImage1(Object image1) {
                this.image1 = image1;
            }

            public Object getImage2() {
                return image2;
            }

            public void setImage2(Object image2) {
                this.image2 = image2;
            }

            public Object getImage3() {
                return image3;
            }

            public void setImage3(Object image3) {
                this.image3 = image3;
            }

            public Object getImage4() {
                return image4;
            }

            public void setImage4(Object image4) {
                this.image4 = image4;
            }

            public Object getImage5() {
                return image5;
            }

            public void setImage5(Object image5) {
                this.image5 = image5;
            }

            public Object getImage6() {
                return image6;
            }

            public void setImage6(Object image6) {
                this.image6 = image6;
            }

            public Object getImage7() {
                return image7;
            }

            public void setImage7(Object image7) {
                this.image7 = image7;
            }

            public Object getImage8() {
                return image8;
            }

            public void setImage8(Object image8) {
                this.image8 = image8;
            }

            public Object getImage9() {
                return image9;
            }

            public void setImage9(Object image9) {
                this.image9 = image9;
            }

            public String getMating() {
                return mating;
            }

            public void setMating(String mating) {
                this.mating = mating;
            }

            public Object getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(Object orderBy) {
                this.orderBy = orderBy;
            }

            public int getPayNumber() {
                return payNumber;
            }

            public void setPayNumber(int payNumber) {
                this.payNumber = payNumber;
            }

            public double getRent() {
                return rent;
            }

            public void setRent(double rent) {
                this.rent = rent;
            }

            public Object getRoomDesc() {
                return roomDesc;
            }

            public void setRoomDesc(Object roomDesc) {
                this.roomDesc = roomDesc;
            }

            public String getRoomNo() {
                return roomNo;
            }

            public void setRoomNo(String roomNo) {
                this.roomNo = roomNo;
            }

            public int getRoomNumber() {
                return roomNumber;
            }

            public void setRoomNumber(int roomNumber) {
                this.roomNumber = roomNumber;
            }

            public Object getRoomTitle() {
                return roomTitle;
            }

            public void setRoomTitle(Object roomTitle) {
                this.roomTitle = roomTitle;
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

            public int getToiletNumber() {
                return toiletNumber;
            }

            public void setToiletNumber(int toiletNumber) {
                this.toiletNumber = toiletNumber;
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
        }

        public static class FyBean {



            boolean isCheck = false;

            boolean needDelete = false;

            private String chargeType;
            private Object contractId;
            private String costName;
            private double costPrice;
            private String costUnit;
            private String createDate;
            private String createTime;
            private String createUser;
            private String houseId;
            private Object maxValue;
            private Object meterCode;
            private int minValue;
            private String roomId;
            private String sysId;
            private Object updDate;
            private Object updTime;
            private Object updUser;

            public boolean isCheck() {
                return isCheck;
            }

            public void setIsCheck(boolean isCheck) {
                this.isCheck = isCheck;
            }

            public boolean isNeedDelete() {
                return needDelete;
            }

            public void setNeedDelete(boolean needDelete) {
                this.needDelete = needDelete;
            }

            public String getChargeType() {
                return chargeType;
            }

            public void setChargeType(String chargeType) {
                this.chargeType = chargeType;
            }

            public Object getContractId() {
                return contractId;
            }

            public void setContractId(Object contractId) {
                this.contractId = contractId;
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

            public String getHouseId() {
                return houseId;
            }

            public void setHouseId(String houseId) {
                this.houseId = houseId;
            }

            public Object getMaxValue() {
                return maxValue;
            }

            public void setMaxValue(Object maxValue) {
                this.maxValue = maxValue;
            }

            public Object getMeterCode() {
                return meterCode;
            }

            public void setMeterCode(Object meterCode) {
                this.meterCode = meterCode;
            }

            public int getMinValue() {
                return minValue;
            }

            public void setMinValue(int minValue) {
                this.minValue = minValue;
            }

            public String getRoomId() {
                return roomId;
            }

            public void setRoomId(String roomId) {
                this.roomId = roomId;
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
}
