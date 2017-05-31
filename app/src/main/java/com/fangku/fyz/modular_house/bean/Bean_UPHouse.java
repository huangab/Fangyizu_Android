package com.fangku.fyz.modular_house.bean;/**
 * Created by 67343 on 2016/9/23.
 */

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Bean_UPHouse {

    /**
     * code : 200
     * data : {"addrDetail":"集美区,集美大学","arroundSupport":"网吧|超市|","createDate":"2016-09-22","createTime":"10:35:43","createUser":"1470736392341zHQVF","emptyNumber":6,"houseLevel":2,"houseName":"大厦","houseType":"集中","image1":null,"image2":null,"image3":null,"isLift":"1","lat":118.11,"lng":24.58,"region":"厦门市","rentType":"整租","roomNumber":6,"sysId":"20160922100000000053","updDate":null,"updTime":null,"updUser":null,"userId":"1470736392341zHQVF"}
     * message : 数据获取成功
     */

    private String code;
    /**
     * addrDetail : 集美区,集美大学
     * arroundSupport : 网吧|超市|
     * createDate : 2016-09-22
     * createTime : 10:35:43
     * createUser : 1470736392341zHQVF
     * emptyNumber : 6
     * houseLevel : 2
     * houseName : 大厦
     * houseType : 集中
     * image1 : null
     * image2 : null
     * image3 : null
     * isLift : 1
     * lat : 118.11
     * lng : 24.58
     * region : 厦门市
     * rentType : 整租
     * roomNumber : 6
     * sysId : 20160922100000000053
     * updDate : null
     * updTime : null
     * updUser : null
     * userId : 1470736392341zHQVF
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
        private String addrDetail;
        private String arroundSupport;
        private String createDate;
        private String createTime;
        private String createUser;
        private int emptyNumber;
        private int houseLevel;
        private String houseName;
        private String houseType;
        private Object image1;
        private Object image2;
        private Object image3;
        private String isLift;
        private double lat;
        private double lng;
        private String region;
        private String rentType;
        private int roomNumber;
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

        public String getArroundSupport() {
            return arroundSupport;
        }

        public void setArroundSupport(String arroundSupport) {
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

        public int getEmptyNumber() {
            return emptyNumber;
        }

        public void setEmptyNumber(int emptyNumber) {
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

        public String getIsLift() {
            return isLift;
        }

        public void setIsLift(String isLift) {
            this.isLift = isLift;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getRentType() {
            return rentType;
        }

        public void setRentType(String rentType) {
            this.rentType = rentType;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(int roomNumber) {
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

