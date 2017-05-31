package com.fangku.fyz.modular_house.bean;

import java.util.List;

/**
 * 5合一   可以查询 未交租 ,未出租+即将到期,已出租,未出租,合同到期 的房间集合
 * Created by   jie.wang
 * Date: 2016/9/21
 * Time: 16:05
 */
public class Bean_House_Type_Room {


    /**
     * code : 200
     * data : [{"chargeCycle":null,"chargeType":null,"content":null,"contractId":null,"contractType":null,"costName":null,"costPrice":null,"costUnit":null,"demo":null,"deposit":null,"houseId":"11","landlordCardId":null,"landlordId":"1470736392341zHQVF","landlordMobile":null,"landlordName":null,"maxValue":null,"minValue":null,"realName":null,"remaining":0,"rent":0,"rentCycle":null,"roomId":null,"roomNo":"ds","roomerCardId":null,"roomerId":null,"roomerMobile":null,"roomerName":null,"warmDay":null},{"chargeCycle":null,"chargeType":null,"content":null,"contractId":null,"contractType":null,"costName":null,"costPrice":null,"costUnit":null,"demo":null,"deposit":null,"houseId":"11","landlordCardId":null,"landlordId":"1470736392341zHQVF","landlordMobile":null,"landlordName":null,"maxValue":null,"minValue":null,"realName":null,"remaining":0,"rent":0,"rentCycle":null,"roomId":null,"roomNo":"we","roomerCardId":null,"roomerId":null,"roomerMobile":null,"roomerName":null,"warmDay":null},{"chargeCycle":null,"chargeType":null,"content":null,"contractId":null,"contractType":null,"costName":null,"costPrice":null,"costUnit":null,"demo":null,"deposit":null,"houseId":"11","landlordCardId":null,"landlordId":"1470736392341zHQVF","landlordMobile":null,"landlordName":null,"maxValue":null,"minValue":null,"realName":null,"remaining":0,"rent":0,"rentCycle":null,"roomId":null,"roomNo":"可以","roomerCardId":null,"roomerId":null,"roomerMobile":null,"roomerName":null,"warmDay":null},{"chargeCycle":null,"chargeType":null,"content":null,"contractId":null,"contractType":null,"costName":null,"costPrice":null,"costUnit":null,"demo":null,"deposit":null,"houseId":"11","landlordCardId":null,"landlordId":"1470736392341zHQVF","landlordMobile":null,"landlordName":null,"maxValue":null,"minValue":null,"realName":null,"remaining":0,"rent":0,"rentCycle":null,"roomId":null,"roomNo":"磨破","roomerCardId":null,"roomerId":null,"roomerMobile":null,"roomerName":null,"warmDay":null},{"chargeCycle":null,"chargeType":null,"content":null,"contractId":null,"contractType":null,"costName":null,"costPrice":null,"costUnit":null,"demo":null,"deposit":null,"houseId":"11","landlordCardId":null,"landlordId":"1470736392341zHQVF","landlordMobile":null,"landlordName":null,"maxValue":null,"minValue":null,"realName":null,"remaining":0,"rent":0,"rentCycle":null,"roomId":null,"roomNo":"蘑菇","roomerCardId":null,"roomerId":null,"roomerMobile":null,"roomerName":null,"warmDay":null},{"chargeCycle":null,"chargeType":null,"content":null,"contractId":null,"contractType":null,"costName":null,"costPrice":null,"costUnit":null,"demo":null,"deposit":null,"houseId":"11","landlordCardId":null,"landlordId":"1470736392341zHQVF","landlordMobile":null,"landlordName":null,"maxValue":null,"minValue":null,"realName":null,"remaining":0,"rent":0,"rentCycle":null,"roomId":null,"roomNo":"蘑菇","roomerCardId":null,"roomerId":null,"roomerMobile":null,"roomerName":null,"warmDay":null},{"chargeCycle":null,"chargeType":null,"content":null,"contractId":null,"contractType":null,"costName":null,"costPrice":null,"costUnit":null,"demo":null,"deposit":null,"houseId":"11","landlordCardId":null,"landlordId":"1470736392341zHQVF","landlordMobile":null,"landlordName":null,"maxValue":null,"minValue":null,"realName":null,"remaining":0,"rent":0,"rentCycle":null,"roomId":null,"roomNo":"ewe","roomerCardId":null,"roomerId":null,"roomerMobile":null,"roomerName":null,"warmDay":null},{"chargeCycle":null,"chargeType":null,"content":null,"contractId":null,"contractType":null,"costName":null,"costPrice":null,"costUnit":null,"demo":null,"deposit":null,"houseId":"11","landlordCardId":null,"landlordId":"1470736392341zHQVF","landlordMobile":null,"landlordName":null,"maxValue":null,"minValue":null,"realName":null,"remaining":0,"rent":0,"rentCycle":null,"roomId":null,"roomNo":"ewwe","roomerCardId":null,"roomerId":null,"roomerMobile":null,"roomerName":null,"warmDay":null}]
     * message : 数据加载成功
     */

    private String code;
    private String message;


    /**
     * chargeCycle : null
     * chargeType : null
     * content : null
     * contractId : null
     * contractType : null
     * costName : null
     * costPrice : null
     * costUnit : null
     * demo : null
     * deposit : null
     * houseId : 11
     * landlordCardId : null
     * landlordId : 1470736392341zHQVF
     * landlordMobile : null
     * landlordName : null
     * maxValue : null
     * minValue : null
     * realName : null
     * remaining : 0
     * rent : 0.0
     * rentCycle : null
     * roomId : null
     * roomNo : ds
     * roomerCardId : null
     * roomerId : null
     * roomerMobile : null
     * roomerName : null
     * warmDay : null
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String mType;//显示类型   有5个类型  未交租  未出租+即将到期  已出租 未出租 合同到期
        private int size;//5种各自的房间数量
        private String contractId;//合同Id
        private String roomNo;//房间号
        private String landlordId; //房东Id
        private String roomerId;  //房客Id
        private String houseId;  //房产Id
        private String roomId;  //房间Id
        private double rent;  //租金
        private double deposit;  //押金
        private String sDate;  //起租日期
        private String eDate;  //结束日期
        private Integer rentCycle;  //收租周期
        private Integer chargeCycle;  //收费项目周期
        private Integer warmDay;  //收租提前提醒-->   默认3天
        private String content;  //合同内容
        private String roomerCardId; //房客身份证号
        private String landlordCardId;  //房东身份证号
        private String landlordName;  //房东真实姓名
        private String landlordMobile;  //房东手机号码
        private String roomerMobile;  //房客手机号码
        private String demo;  //备注
        private String contractType;  //合同类型
        private String costName;  // 费用名称
        private double costPrice;  //单价
        private String costUnit;  //单位
        private Integer minValue;  // 最低值  -->不足最低值按最低值收
        private String chargeType;  //1:无度数 2：有度数
        private Integer maxValue;  //表刻度最大数
        private String remaining;  //剩余时间
        private String realName;    //真实姓名
        private double cost;//总费用
        private String startDate;
        private String endDate;
        private String roomerName;  //房客真实姓名

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }


        public String getType() {
            return mType;
        }

        public void setType(String type) {
            mType = type;
        }

        public String getContractId() {
            return contractId;
        }

        public void setContractId(String contractId) {
            this.contractId = contractId;
        }

        public String getRoomNo() {
            return roomNo;
        }

        public void setRoomNo(String roomNo) {
            this.roomNo = roomNo;
        }

        public String getLandlordId() {
            return landlordId;
        }

        public void setLandlordId(String landlordId) {
            this.landlordId = landlordId;
        }

        public String getRoomerId() {
            return roomerId;
        }

        public void setRoomerId(String roomerId) {
            this.roomerId = roomerId;
        }

        public String getHouseId() {
            return houseId;
        }

        public void setHouseId(String houseId) {
            this.houseId = houseId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public double getRent() {
            return rent;
        }

        public void setRent(double rent) {
            this.rent = rent;
        }

        public double getDeposit() {
            return deposit;
        }

        public void setDeposit(double deposit) {
            this.deposit = deposit;
        }

        public String getsDate() {
            return sDate;
        }

        public void setsDate(String sDate) {
            this.sDate = sDate;
        }

        public String geteDate() {
            return eDate;
        }

        public void seteDate(String eDate) {
            this.eDate = eDate;
        }

        public Integer getRentCycle() {
            return rentCycle;
        }

        public void setRentCycle(Integer rentCycle) {
            this.rentCycle = rentCycle;
        }

        public Integer getChargeCycle() {
            return chargeCycle;
        }

        public void setChargeCycle(Integer chargeCycle) {
            this.chargeCycle = chargeCycle;
        }

        public Integer getWarmDay() {
            return warmDay;
        }

        public void setWarmDay(Integer warmDay) {
            this.warmDay = warmDay;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRoomerCardId() {
            return roomerCardId;
        }

        public void setRoomerCardId(String roomerCardId) {
            this.roomerCardId = roomerCardId;
        }

        public String getLandlordCardId() {
            return landlordCardId;
        }

        public void setLandlordCardId(String landlordCardId) {
            this.landlordCardId = landlordCardId;
        }

        public String getLandlordName() {
            return landlordName;
        }

        public void setLandlordName(String landlordName) {
            this.landlordName = landlordName;
        }

        public String getLandlordMobile() {
            return landlordMobile;
        }

        public void setLandlordMobile(String landlordMobile) {
            this.landlordMobile = landlordMobile;
        }

        public String getRoomerMobile() {
            return roomerMobile;
        }

        public void setRoomerMobile(String roomerMobile) {
            this.roomerMobile = roomerMobile;
        }

        public String getDemo() {
            return demo;
        }

        public void setDemo(String demo) {
            this.demo = demo;
        }

        public String getContractType() {
            return contractType;
        }

        public void setContractType(String contractType) {
            this.contractType = contractType;
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

        public Integer getMinValue() {
            return minValue;
        }

        public void setMinValue(Integer minValue) {
            this.minValue = minValue;
        }

        public String getChargeType() {
            return chargeType;
        }

        public void setChargeType(String chargeType) {
            this.chargeType = chargeType;
        }

        public Integer getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(Integer maxValue) {
            this.maxValue = maxValue;
        }

        public String getRemaining() {
            return remaining;
        }

        public void setRemaining(String remaining) {
            this.remaining = remaining;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getRoomerName() {
            return roomerName;
        }

        public void setRoomerName(String roomerName) {
            this.roomerName = roomerName;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "mType='" + mType + '\'' +
                    ", contractId='" + contractId + '\'' +
                    ", roomNo='" + roomNo + '\'' +
                    ", landlordId='" + landlordId + '\'' +
                    ", roomerId='" + roomerId + '\'' +
                    ", houseId='" + houseId + '\'' +
                    ", roomId='" + roomId + '\'' +
                    ", rent=" + rent +
                    ", deposit=" + deposit +
                    ", sDate='" + sDate + '\'' +
                    ", eDate='" + eDate + '\'' +
                    ", rentCycle=" + rentCycle +
                    ", chargeCycle=" + chargeCycle +
                    ", warmDay=" + warmDay +
                    ", content='" + content + '\'' +
                    ", roomerCardId='" + roomerCardId + '\'' +
                    ", landlordCardId='" + landlordCardId + '\'' +
                    ", landlordName='" + landlordName + '\'' +
                    ", landlordMobile='" + landlordMobile + '\'' +
                    ", roomerMobile='" + roomerMobile + '\'' +
                    ", demo='" + demo + '\'' +
                    ", contractType='" + contractType + '\'' +
                    ", costName='" + costName + '\'' +
                    ", costPrice=" + costPrice +
                    ", costUnit='" + costUnit + '\'' +
                    ", minValue=" + minValue +
                    ", chargeType='" + chargeType + '\'' +
                    ", maxValue=" + maxValue +
                    ", remaining=" + remaining +
                    ", realName='" + realName + '\'' +
                    ", cost=" + cost +
                    ", startDate='" + startDate + '\'' +
                    ", endDate='" + endDate + '\'' +
                    ", roomerName='" + roomerName + '\'' +
                    '}';
        }
    }
}
