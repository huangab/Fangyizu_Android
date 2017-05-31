package com.fangku.fyz.modular_house.bean;

import java.util.List;

/**
 * Created by bowen.ye
 * Date: 2016/9/23
 * Time: 19:44
 */
public class Bean_House_All_Roomer_List {


    /**
     * code : 200
     * data : [{"birthday":null,"certificateAuthority":null,"contractId":"20160923100000000193","createDate":"2016-09-23","createTime":"19:38:54","createUser":"1470736392341zHQVF","gender":"男","houseId":"11","idCardAddress":null,"idCardImage1":null,"idCardImage2":null,"idCardNo":"999","invalidDate":null,"isCheck":"n","isMaster":"y","landlordId":"1470736392341zHQVF","realName":"哦哦哦","roomId":"20160908100000019219","roomerMoblie":"966","sysId":"20160923100000000036","updDate":"2016-09-23","updTime":"19:38:56","updUser":"1470736392341zHQVF","userId":null,"workPlace":null},{"birthday":null,"certificateAuthority":null,"contractId":"20160923100000000193","createDate":"2016-09-23","createTime":"19:38:54","createUser":"1470736392341zHQVF","gender":"男","houseId":"11","idCardAddress":null,"idCardImage1":null,"idCardImage2":null,"idCardNo":"999","invalidDate":null,"isCheck":"n","isMaster":"y","landlordId":"1470736392341zHQVF","realName":"哦哦哦","roomId":"20160908100000019219","roomerMoblie":"966","sysId":"20160923100000000037","updDate":null,"updTime":null,"updUser":null,"userId":"1474630736306R85D4","workPlace":""}]
     * message : 数据获取成功
     */

    private String code;
    private String message;
    /**
     * birthday : null
     * certificateAuthority : null
     * contractId : 20160923100000000193
     * createDate : 2016-09-23
     * createTime : 19:38:54
     * createUser : 1470736392341zHQVF
     * gender : 男
     * houseId : 11
     * idCardAddress : null
     * idCardImage1 : null
     * idCardImage2 : null
     * idCardNo : 999
     * invalidDate : null
     * isCheck : n
     * isMaster : y
     * landlordId : 1470736392341zHQVF
     * realName : 哦哦哦
     * roomId : 20160908100000019219
     * roomerMoblie : 966
     * sysId : 20160923100000000036
     * updDate : 2016-09-23
     * updTime : 19:38:56
     * updUser : 1470736392341zHQVF
     * userId : null
     * workPlace : null
     */

    private List<DataEntity> data;

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

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        private Object birthday;
        private Object certificateAuthority;
        private String contractId;
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
        private String updDate;
        private String updTime;
        private String updUser;
        private Object userId;
        private Object workPlace;

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

        public String getContractId() {
            return contractId;
        }

        public void setContractId(String contractId) {
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

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public Object getWorkPlace() {
            return workPlace;
        }

        public void setWorkPlace(Object workPlace) {
            this.workPlace = workPlace;
        }
    }
}
