package com.fangku.fyz.modular_house.bean;

import java.io.Serializable;

/**
 * 房间费用
 * Created by bowen.ye
 * Date: 2016/9/19
 * Time: 16:37
 */
public class Bean_House_All_RoomCharge implements Serializable {

    private String sysId;//系统Id
    private String houseId;  //房产Id
    private String roomId;  //房间Id
    private String contractId;  //合同Id
    private String costName;  // 费用名称
    private Double costPrice;  //单价
    private String costUnit;  //单位
    private Integer minValue;  // 最低值  -->不足最低值按最低值收
    private String chargeType;  //1:无度数 2：有度数
    private Integer maxValue;  //表刻度最大数
    private String meterCode;//费用表类型代号
    private String operate;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
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

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
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

    public String getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }


    boolean isCheck = false;

    boolean needDelete = false;

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
}
