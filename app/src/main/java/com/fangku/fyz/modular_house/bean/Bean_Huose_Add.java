package com.fangku.fyz.modular_house.bean;/**
 * Created by 67343 on 2016/8/14.
 */

import com.fangku.fyz.bean.Bean_House_Floors_Number;

import java.util.List;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Bean_Huose_Add {
    private String addrDetali;      //详细地址
    private String region;          //区域（省市县）
    private String houseName;       //房产名称
    private String houseLevel = "1";      //楼层数，正整数
    private String roomNumber;      //房间总数，非负整数，默认0
    private String arroundSupportList; //周边配套，
    private Double lng = 0.0;             //经度
    private Double lat = 0.0;             //纬度
    private String houseType;       //房产类型，集中/分散
    private String rentType;        //出租方式，若集中，则无需传；若分散，必须为合租/整租
    private String isLift = "0";          //带电梯，默认不带。0：不带，1：带
    private String emptyNumber;    //空置房间数，非负整数，默认0
    private String image1;         //展示图1
    private String image2;         //展示图2
    private String image3;         //展示图3
    private List<Bean_House_Floors_Number> floorsAndRooms;//楼层和房间分布信息

    public List<Bean_House_Floors_Number> getFloorsAndRooms() {
        return floorsAndRooms;
    }

    public void setFloorsAndRooms(List<Bean_House_Floors_Number> floorsAndRooms) {
        this.floorsAndRooms = floorsAndRooms;
    }

    public String getAddrDetali() {
        return addrDetali;
    }

    public void setAddrDetali(String addrDetali) {
        this.addrDetali = addrDetali;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseLevel() {
        return houseLevel;
    }

    public void setHouseLevel(String houseLevel) {
        this.houseLevel = houseLevel;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getArroundSupportList() {
        return arroundSupportList;
    }

    public void setArroundSupportList(String arroundSupportList) {
        this.arroundSupportList = arroundSupportList;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public String getIsLift() {
        return isLift;
    }

    public void setIsLift(String isLift) {
        this.isLift = isLift;
    }

    public String getEmptyNumber() {
        return emptyNumber;
    }

    public void setEmptyNumber(String emptyNumber) {
        this.emptyNumber = emptyNumber;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }
}
