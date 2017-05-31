package com.fangku.fyz.modular_house.bean;/**
 * Created by 67343 on 2016/8/14.
 */

import java.util.List;

/**
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Bean_House_Add_GetAddress {
    /**
     * code : 200
     * data : [{"address":null,"city":"厦门市","district":"思明区","id":null,"latitude":24.473788,"longitude":118.178902,"name":"前埔医院","province":null,"streetId":null,"tag":null,"telephone":null},{"address":null,"city":"厦门市","district":"思明区","id":null,"latitude":24.477463,"longitude":118.186194,"name":"前埔村","province":null,"streetId":null,"tag":null,"telephone":null}]
     * message :
     */

    private String code;
    private String message;
    /**
     * address : null
     * city : 厦门市
     * district : 思明区
     * id : null
     * latitude : 24.473788
     * longitude : 118.178902
     * name : 前埔医院
     * province : null
     * streetId : null
     * tag : null
     * telephone : null
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
        private Object address;
        private String city;
        private String district;
        private Object id;
        private double latitude;
        private double longitude;
        private String name;
        private Object province;
        private Object streetId;
        private Object tag;
        private Object telephone;

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getStreetId() {
            return streetId;
        }

        public void setStreetId(Object streetId) {
            this.streetId = streetId;
        }

        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }

        public Object getTelephone() {
            return telephone;
        }

        public void setTelephone(Object telephone) {
            this.telephone = telephone;
        }
    }
}
