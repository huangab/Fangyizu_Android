package com.fangku.fyz.modular_house.bean;/**
 * Created by 67343 on 2016/8/16.
 */

import java.io.Serializable;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Bean_HouseCharge implements Serializable {

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    boolean isCheck = false;

    boolean needDelete = false;

    public boolean isNeedDelete() {
        return needDelete;
    }

    public void setNeedDelete(boolean needDelete) {
        this.needDelete = needDelete;
    }

    String sysId;           //费用项目Id
    String houseId;         //房产Id
    String costName;        //费用名称
    Double costPrice;       //单价
    String costUnit;        //单位(有刻度必填)
    Double minValue;        //最低值(有刻度必填)
    String chargeType;      //费用类型(1:无度数 2：有度数)
    String operate;         //数据操作(A: 新增，D：删除，U:修改)
    Double maxValue;        //最大值

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

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }


    public String getCostUnit() {
        return costUnit;
    }

    public void setCostUnit(String costUnit) {
        this.costUnit = costUnit;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    @Override
    public String toString() {
        return "Bean_HouseCharge{" +
                "isCheck=" + isCheck +
                ", needDelete=" + needDelete +
                ", sysId='" + sysId + '\'' +
                ", houseId='" + houseId + '\'' +
                ", costName='" + costName + '\'' +
                ", costPrice='" + costPrice + '\'' +
                ", costUnit='" + costUnit + '\'' +
                ", minValue='" + minValue + '\'' +
                ", chargeType='" + chargeType + '\'' +
                ", operate='" + operate + '\'' +
                '}';
    }
}
