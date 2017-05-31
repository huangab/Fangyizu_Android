package com.fangku.fyz.modular_house.bean;/**
 * Created by 67343 on 2016/9/7.
 */

import java.util.List;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Bean_House_Data {

    /**
     * code : 200
     * data : [{"addrDetail":"厦门","arroundSupport":null,"createDate":"2016-07-29","createTime":"17:23:21","createUser":"1469498309126Kp8kA","emptyNumber":null,"houseLevel":10,"houseName":"幸福大厦","houseType":"集中","image1":null,"image2":null,"image3":null,"isLift":null,"lat":null,"lng":null,"region":"厦门","rentType":null,"roomNumber":null,"sysId":"11","updDate":null,"updTime":null,"updUser":null,"userId":"1470736392341zHQVF"},{"addrDetail":"湖里区,集美大桥入口","arroundSupport":"","createDate":"2016-08-22","createTime":"17:42:34","createUser":"1470736392341zHQVF","emptyNumber":null,"houseLevel":2,"houseName":"为","houseType":"集中","image1":"null","image2":"null","image3":"null","isLift":"1","lat":null,"lng":118.15,"region":"厦门市","rentType":"合租/整租","roomNumber":8,"sysId":"20160822100000000003","updDate":null,"updTime":null,"updUser":null,"userId":"1470736392341zHQVF"},{"addrDetail":"集美区,万达广场(集美店)","arroundSupport":"","createDate":"2016-08-22","createTime":"17:44:54","createUser":"1470736392341zHQVF","emptyNumber":0,"houseLevel":2,"houseName":"为","houseType":"集中","image1":"null","image2":"null","image3":"null","isLift":"0","lat":24.58,"lng":118.1,"region":"厦门市","rentType":"合租/整租","roomNumber":13,"sysId":"20160822100000000004","updDate":null,"updTime":null,"updUser":null,"userId":"1470736392341zHQVF"},{"addrDetail":"集美区,万达广场(集美店)","arroundSupport":"","createDate":"2016-08-22","createTime":"17:58:00","createUser":"1470736392341zHQVF","emptyNumber":0,"houseLevel":1,"houseName":"为","houseType":"集中","image1":"null","image2":"null","image3":"null","isLift":"0","lat":24.58,"lng":118.1,"region":"厦门市","rentType":"合租/整租","roomNumber":3,"sysId":"20160822100000000005","updDate":null,"updTime":null,"updUser":null,"userId":"1470736392341zHQVF"},{"addrDetail":"集美区,集美学村","arroundSupport":null,"createDate":"2016-09-01","createTime":"08:52:13","createUser":"1470736392341zHQVF","emptyNumber":54,"houseLevel":6,"houseName":"额外","houseType":"集中","image1":null,"image2":null,"image3":null,"isLift":"1","lat":24.57,"lng":118.1,"region":"厦门市","rentType":"整租","roomNumber":54,"sysId":"20160901100000000008","updDate":null,"updTime":null,"updUser":null,"userId":"1470736392341zHQVF"},{"addrDetail":"集美区,集美学村","arroundSupport":null,"createDate":"2016-09-01","createTime":"08:53:02","createUser":"1470736392341zHQVF","emptyNumber":108,"houseLevel":6,"houseName":"额外","houseType":"集中","image1":null,"image2":null,"image3":null,"isLift":"1","lat":24.57,"lng":118.1,"region":"厦门市","rentType":"整租","roomNumber":108,"sysId":"20160901100000000009","updDate":null,"updTime":null,"updUser":null,"userId":"1470736392341zHQVF"},{"addrDetail":"集美区,集美学村","arroundSupport":null,"createDate":"2016-09-01","createTime":"16:29:36","createUser":"1470736392341zHQVF","emptyNumber":288,"houseLevel":19,"houseName":"啦啦啦","houseType":"集中","image1":null,"image2":null,"image3":null,"isLift":"0","lat":24.57,"lng":118.1,"region":"厦门市","rentType":"整租","roomNumber":288,"sysId":"20160901100000000010","updDate":null,"updTime":null,"updUser":null,"userId":"1470736392341zHQVF"},{"addrDetail":"集美区,集美学村","arroundSupport":null,"createDate":"2016-09-01","createTime":"16:29:38","createUser":"1470736392341zHQVF","emptyNumber":576,"houseLevel":19,"houseName":"啦啦啦","houseType":"集中","image1":null,"image2":null,"image3":null,"isLift":"0","lat":24.57,"lng":118.1,"region":"厦门市","rentType":"整租","roomNumber":576,"sysId":"20160901100000000011","updDate":null,"updTime":null,"updUser":null,"userId":"1470736392341zHQVF"}]
     */

    private String code;
    /**
     * addrDetail : 厦门
     * arroundSupport : null
     * createDate : 2016-07-29
     * createTime : 17:23:21
     * createUser : 1469498309126Kp8kA
     * emptyNumber : null
     * houseLevel : 10
     * houseName : 幸福大厦
     * houseType : 集中
     * image1 : null
     * image2 : null
     * image3 : null
     * isLift : null
     * lat : null
     * lng : null
     * region : 厦门
     * rentType : null
     * roomNumber : null
     * sysId : 11
     * updDate : null
     * updTime : null
     * updUser : null
     * userId : 1470736392341zHQVF
     */

    private List<DataBean> data;

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

    public static class DataBean {
        private String addrDetail;
        private Object arroundSupport;
        private String createDate;
        private String createTime;
        private String createUser;
        private Object emptyNumber;
        private int houseLevel;
        private String houseName;
        private String houseType;
        private Object image1;
        private Object image2;
        private Object image3;
        private Object isLift;
        private Object lat;
        private Object lng;
        private String region;
        private Object rentType;
        private Object roomNumber;
        private String sysId;
        private Object updDate;
        private Object updTime;
        private Object updUser;
        private String userId;

        public String getAddrDetail() {
            return addrDetail;
        }

        public void setAddrDetail(String addrDetail) {
            this.addrDetail = addrDetail;
        }

        public Object getArroundSupport() {
            return arroundSupport;
        }

        public void setArroundSupport(Object arroundSupport) {
            this.arroundSupport = arroundSupport;
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

        public Object getEmptyNumber() {
            return emptyNumber;
        }

        public void setEmptyNumber(Object emptyNumber) {
            this.emptyNumber = emptyNumber;
        }

        public int getHouseLevel() {
            return houseLevel;
        }

        public void setHouseLevel(int houseLevel) {
            this.houseLevel = houseLevel;
        }

        public String getHouseName() {
            return houseName;
        }

        public void setHouseName(String houseName) {
            this.houseName = houseName;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
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

        public Object getIsLift() {
            return isLift;
        }

        public void setIsLift(Object isLift) {
            this.isLift = isLift;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public Object getLng() {
            return lng;
        }

        public void setLng(Object lng) {
            this.lng = lng;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public Object getRentType() {
            return rentType;
        }

        public void setRentType(Object rentType) {
            this.rentType = rentType;
        }

        public Object getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(Object roomNumber) {
            this.roomNumber = roomNumber;
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
