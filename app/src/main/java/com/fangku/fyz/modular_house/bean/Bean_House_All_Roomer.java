package com.fangku.fyz.modular_house.bean;

import java.io.Serializable;

/**
 * 房客信息表
 * Created by bowen.ye
 * Date: 2016/9/19
 * Time: 16:33
 */
public class Bean_House_All_Roomer implements Serializable {

    /**
     * code : 200
     * data : {"birthday":null,"certificateAuthority":null,"contractId":null,"createDate":"2016-09-23","createTime":"16:43:05","createUser":"1470736392341zHQVF","gender":"男","houseId":"11","idCardAddress":null,"idCardImage1":null,"idCardImage2":null,"idCardNo":"777","invalidDate":null,"isCheck":"n","isMaster":"y","landlordId":"1470736392341zHQVF","realName":"777","roomId":"20160908100000019219","roomerMoblie":"777","sysId":"20160923100000000017","updDate":null,"updTime":null,"updUser":null,"userId":null,"workPlace":""}
     * message : 新增租客成功
     */

    private String code;
    /**
     * birthday : null
     * certificateAuthority : null
     * contractId : null
     * createDate : 2016-09-23
     * createTime : 16:43:05
     * createUser : 1470736392341zHQVF
     * gender : 男
     * houseId : 11
     * idCardAddress : null
     * idCardImage1 : null
     * idCardImage2 : null
     * idCardNo : 777
     * invalidDate : null
     * isCheck : n
     * isMaster : y
     * landlordId : 1470736392341zHQVF
     * realName : 777
     * roomId : 20160908100000019219
     * roomerMoblie : 777
     * sysId : 20160923100000000017
     * updDate : null
     * updTime : null
     * updUser : null
     * userId : null
     * workPlace :
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

    public static class DataEntity implements Serializable{
        private Object birthday;
        private Object certificateAuthority;
        private Object contractId;
        private String createDate;
        private String createTime;
        private String createUser;
        private String gender;
        private String houseId;
        private Object idCardAddress;
        private Object idCardImage1;
        private Object idCardImage2;
        private String idCardNo;
        private Object invalidDate;
        private String isCheck;
        private String isMaster;
        private String landlordId;
        private String realName;
        private String roomId;
        private String roomerMoblie;
        private String sysId;
        private Object updDate;
        private Object updTime;
        private Object updUser;
        private Object userId;
        private String workPlace;

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getCertificateAuthority() {
            return certificateAuthority;
        }

        public void setCertificateAuthority(Object certificateAuthority) {
            this.certificateAuthority = certificateAuthority;
        }

        public Object getContractId() {
            return contractId;
        }

        public void setContractId(Object contractId) {
            this.contractId = contractId;
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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHouseId() {
            return houseId;
        }

        public void setHouseId(String houseId) {
            this.houseId = houseId;
        }

        public Object getIdCardAddress() {
            return idCardAddress;
        }

        public void setIdCardAddress(Object idCardAddress) {
            this.idCardAddress = idCardAddress;
        }

        public Object getIdCardImage1() {
            return idCardImage1;
        }

        public void setIdCardImage1(Object idCardImage1) {
            this.idCardImage1 = idCardImage1;
        }

        public Object getIdCardImage2() {
            return idCardImage2;
        }

        public void setIdCardImage2(Object idCardImage2) {
            this.idCardImage2 = idCardImage2;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
        }

        public Object getInvalidDate() {
            return invalidDate;
        }

        public void setInvalidDate(Object invalidDate) {
            this.invalidDate = invalidDate;
        }

        public String getIsCheck() {
            return isCheck;
        }

        public void setIsCheck(String isCheck) {
            this.isCheck = isCheck;
        }

        public String getIsMaster() {
            return isMaster;
        }

        public void setIsMaster(String isMaster) {
            this.isMaster = isMaster;
        }

        public String getLandlordId() {
            return landlordId;
        }

        public void setLandlordId(String landlordId) {
            this.landlordId = landlordId;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getRoomerMoblie() {
            return roomerMoblie;
        }

        public void setRoomerMoblie(String roomerMoblie) {
            this.roomerMoblie = roomerMoblie;
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

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public String getWorkPlace() {
            return workPlace;
        }

        public void setWorkPlace(String workPlace) {
            this.workPlace = workPlace;
        }
    }
}
