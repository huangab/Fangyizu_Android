package com.fangku.fyz.modular_house.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 租赁合同
 * Created by bowen.ye
 * Date: 2016/9/19
 * Time: 16:40
 */
public class Bean_House_All_Contract implements Serializable {

    /**
     * code : 200
     * data : {"cbxx":[{"accScale":0,"createDate":"2016-10-09","createTime":"10:15:39","createUser":"1470736392341zHQVF","demo":"","flag":null,"houseId":"11","lastDate":"2016-10-09","lastScale":6667,"meterName":"水表","meterType":"E","orderBy":0,"roomId":"20160930100000021610","roomNo":"676","state":"已收","sysId":"20161009100000000643","thisDate":"2016-10-09","thisScale":6667,"updDate":null,"updTime":null,"updUser":null},{"accScale":0,"createDate":"2016-10-09","createTime":"10:15:39","createUser":"1470736392341zHQVF","demo":"","flag":null,"houseId":"11","lastDate":"2016-10-09","lastScale":9667,"meterName":"电表","meterType":"W","orderBy":0,"roomId":"20160930100000021610","roomNo":"676","state":"已收","sysId":"20161009100000000644","thisDate":"2016-10-09","thisScale":9667,"updDate":null,"updTime":null,"updUser":null}],"fkxx":[{"birthday":null,"certificateAuthority":null,"contractId":"20161009100000000243","createDate":"2016-10-09","createTime":"10:15:33","createUser":"1470736392341zHQVF","gender":"男","houseId":"11","idCardAddress":null,"idCardImage1":null,"idCardImage2":null,"idCardNo":"64669949568959","invalidDate":null,"isCheck":"n","isMaster":"y","landlordId":"1470736392341zHQVF","realName":"弄","roomId":"20160930100000021610","roomerMoblie":"13696933529","sysId":"20161009100000000108","updDate":"2016-10-09","updTime":"10:28:04","updUser":"1470736392341zHQVF","userId":null,"workPlace":null}],"htxx":{"chargeCycle":1,"content":"合约","contractType":"新签","createDate":"2016-10-09","createTime":"10:15:38","createUser":"1470736392341zHQVF","demo":null,"deposit":333,"endDate":"2017-01-09","houseId":"11","landlordCardId":"350702199406301316","landlordId":"1470736392341zHQVF","landlordMobile":"15606091536","landlordName":null,"rent":333,"rentCycle":1,"roomId":"20160930100000021610","roomerCardId":"64669949568959","roomerId":"20161009100000000108","roomerMobile":"13696933529","roomerName":"弄","startDate":"2016-10-09","sysId":"20161009100000000243","updDate":null,"updTime":null,"updUser":null,"warmDay":3},"zjmx":[{"chargeType":"1","costName":"管理费","costPrice":0,"costUnit":"元/月","createDate":"2016-10-09","createTime":"10:27:09","createUser":"1470736392341zHQVF","lastDate":null,"lastScale":null,"rentId":"20161009100000000174","sysId":"20161009100000000717","thisDate":null,"thisScale":null,"totalCost":0,"totalScale":null,"updDate":null,"updTime":null,"updUser":null},{"chargeType":null,"costName":"租金","costPrice":333,"costUnit":"元/月","createDate":"2016-10-09","createTime":"10:27:09","createUser":"1470736392341zHQVF","lastDate":null,"lastScale":null,"rentId":"20161009100000000174","sysId":"20161009100000000718","thisDate":null,"thisScale":null,"totalCost":333,"totalScale":null,"updDate":null,"updTime":null,"updUser":null},{"chargeType":null,"costName":"押金","costPrice":333,"costUnit":null,"createDate":"2016-10-09","createTime":"10:27:09","createUser":"1470736392341zHQVF","lastDate":null,"lastScale":null,"rentId":"20161009100000000174","sysId":"20161009100000000719","thisDate":null,"thisScale":null,"totalCost":333,"totalScale":null,"updDate":null,"updTime":null,"updUser":null}],"zjxx":{"contractId":"20161009100000000243","cost":333,"createDate":"2016-10-09","createTime":"10:15:39","createUser":"1470736392341zHQVF","endDate":"2017-01-09","houseId":"11","landlordId":"1470736392341zHQVF","roomId":"20160930100000021610","roomerId":"20161009100000000108","startDate":"2016-10-09","state":"已收","sysId":"20161009100000000174","updDate":"2016-10-09","updTime":"10:27:09","updUser":"1470736392341zHQVF"}}
     * message : 数据获取成功
     */

    private String code;
    /**
     * cbxx : [{"accScale":0,"createDate":"2016-10-09","createTime":"10:15:39","createUser":"1470736392341zHQVF","demo":"","flag":null,"houseId":"11","lastDate":"2016-10-09","lastScale":6667,"meterName":"水表","meterType":"E","orderBy":0,"roomId":"20160930100000021610","roomNo":"676","state":"已收","sysId":"20161009100000000643","thisDate":"2016-10-09","thisScale":6667,"updDate":null,"updTime":null,"updUser":null},{"accScale":0,"createDate":"2016-10-09","createTime":"10:15:39","createUser":"1470736392341zHQVF","demo":"","flag":null,"houseId":"11","lastDate":"2016-10-09","lastScale":9667,"meterName":"电表","meterType":"W","orderBy":0,"roomId":"20160930100000021610","roomNo":"676","state":"已收","sysId":"20161009100000000644","thisDate":"2016-10-09","thisScale":9667,"updDate":null,"updTime":null,"updUser":null}]
     * fkxx : [{"birthday":null,"certificateAuthority":null,"contractId":"20161009100000000243","createDate":"2016-10-09","createTime":"10:15:33","createUser":"1470736392341zHQVF","gender":"男","houseId":"11","idCardAddress":null,"idCardImage1":null,"idCardImage2":null,"idCardNo":"64669949568959","invalidDate":null,"isCheck":"n","isMaster":"y","landlordId":"1470736392341zHQVF","realName":"弄","roomId":"20160930100000021610","roomerMoblie":"13696933529","sysId":"20161009100000000108","updDate":"2016-10-09","updTime":"10:28:04","updUser":"1470736392341zHQVF","userId":null,"workPlace":null}]
     * htxx : {"chargeCycle":1,"content":"合约","contractType":"新签","createDate":"2016-10-09","createTime":"10:15:38","createUser":"1470736392341zHQVF","demo":null,"deposit":333,"endDate":"2017-01-09","houseId":"11","landlordCardId":"350702199406301316","landlordId":"1470736392341zHQVF","landlordMobile":"15606091536","landlordName":null,"rent":333,"rentCycle":1,"roomId":"20160930100000021610","roomerCardId":"64669949568959","roomerId":"20161009100000000108","roomerMobile":"13696933529","roomerName":"弄","startDate":"2016-10-09","sysId":"20161009100000000243","updDate":null,"updTime":null,"updUser":null,"warmDay":3}
     * zjmx : [{"chargeType":"1","costName":"管理费","costPrice":0,"costUnit":"元/月","createDate":"2016-10-09","createTime":"10:27:09","createUser":"1470736392341zHQVF","lastDate":null,"lastScale":null,"rentId":"20161009100000000174","sysId":"20161009100000000717","thisDate":null,"thisScale":null,"totalCost":0,"totalScale":null,"updDate":null,"updTime":null,"updUser":null},{"chargeType":null,"costName":"租金","costPrice":333,"costUnit":"元/月","createDate":"2016-10-09","createTime":"10:27:09","createUser":"1470736392341zHQVF","lastDate":null,"lastScale":null,"rentId":"20161009100000000174","sysId":"20161009100000000718","thisDate":null,"thisScale":null,"totalCost":333,"totalScale":null,"updDate":null,"updTime":null,"updUser":null},{"chargeType":null,"costName":"押金","costPrice":333,"costUnit":null,"createDate":"2016-10-09","createTime":"10:27:09","createUser":"1470736392341zHQVF","lastDate":null,"lastScale":null,"rentId":"20161009100000000174","sysId":"20161009100000000719","thisDate":null,"thisScale":null,"totalCost":333,"totalScale":null,"updDate":null,"updTime":null,"updUser":null}]
     * zjxx : {"contractId":"20161009100000000243","cost":333,"createDate":"2016-10-09","createTime":"10:15:39","createUser":"1470736392341zHQVF","endDate":"2017-01-09","houseId":"11","landlordId":"1470736392341zHQVF","roomId":"20160930100000021610","roomerId":"20161009100000000108","startDate":"2016-10-09","state":"已收","sysId":"20161009100000000174","updDate":"2016-10-09","updTime":"10:27:09","updUser":"1470736392341zHQVF"}
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

    public static class DataEntity implements Serializable {
        /**
         * chargeCycle : 1
         * content : 合约
         * contractType : 新签
         * createDate : 2016-10-09
         * createTime : 10:15:38
         * createUser : 1470736392341zHQVF
         * demo : null
         * deposit : 333.0
         * endDate : 2017-01-09
         * houseId : 11
         * landlordCardId : 350702199406301316
         * landlordId : 1470736392341zHQVF
         * landlordMobile : 15606091536
         * landlordName : null
         * rent : 333.0
         * rentCycle : 1
         * roomId : 20160930100000021610
         * roomerCardId : 64669949568959
         * roomerId : 20161009100000000108
         * roomerMobile : 13696933529
         * roomerName : 弄
         * startDate : 2016-10-09
         * sysId : 20161009100000000243
         * updDate : null
         * updTime : null
         * updUser : null
         * warmDay : 3
         */

        private HtxxEntity htxx;
        /**
         * contractId : 20161009100000000243
         * cost : 333.0
         * createDate : 2016-10-09
         * createTime : 10:15:39
         * createUser : 1470736392341zHQVF
         * endDate : 2017-01-09
         * houseId : 11
         * landlordId : 1470736392341zHQVF
         * roomId : 20160930100000021610
         * roomerId : 20161009100000000108
         * startDate : 2016-10-09
         * state : 已收
         * sysId : 20161009100000000174
         * updDate : 2016-10-09
         * updTime : 10:27:09
         * updUser : 1470736392341zHQVF
         */

        private ZjxxEntity zjxx;
        /**
         * accScale : 0.0
         * createDate : 2016-10-09
         * createTime : 10:15:39
         * createUser : 1470736392341zHQVF
         * demo :
         * flag : null
         * houseId : 11
         * lastDate : 2016-10-09
         * lastScale : 6667.0
         * meterName : 水表
         * meterType : E
         * orderBy : 0
         * roomId : 20160930100000021610
         * roomNo : 676
         * state : 已收
         * sysId : 20161009100000000643
         * thisDate : 2016-10-09
         * thisScale : 6667.0
         * updDate : null
         * updTime : null
         * updUser : null
         */

        private List<CbxxEntity> cbxx;
        /**
         * birthday : null
         * certificateAuthority : null
         * contractId : 20161009100000000243
         * createDate : 2016-10-09
         * createTime : 10:15:33
         * createUser : 1470736392341zHQVF
         * gender : 男
         * houseId : 11
         * idCardAddress : null
         * idCardImage1 : null
         * idCardImage2 : null
         * idCardNo : 64669949568959
         * invalidDate : null
         * isCheck : n
         * isMaster : y
         * landlordId : 1470736392341zHQVF
         * realName : 弄
         * roomId : 20160930100000021610
         * roomerMoblie : 13696933529
         * sysId : 20161009100000000108
         * updDate : 2016-10-09
         * updTime : 10:28:04
         * updUser : 1470736392341zHQVF
         * userId : null
         * workPlace : null
         */

        private List<FkxxEntity> fkxx;
        /**
         * chargeType : 1
         * costName : 管理费
         * costPrice : 0.0
         * costUnit : 元/月
         * createDate : 2016-10-09
         * createTime : 10:27:09
         * createUser : 1470736392341zHQVF
         * lastDate : null
         * lastScale : null
         * rentId : 20161009100000000174
         * sysId : 20161009100000000717
         * thisDate : null
         * thisScale : null
         * totalCost : 0.0
         * totalScale : null
         * updDate : null
         * updTime : null
         * updUser : null
         */

        private List<ZjmxEntity> zjmx;

        public HtxxEntity getHtxx() {
            return htxx;
        }

        public void setHtxx(HtxxEntity htxx) {
            this.htxx = htxx;
        }

        public ZjxxEntity getZjxx() {
            return zjxx;
        }

        public void setZjxx(ZjxxEntity zjxx) {
            this.zjxx = zjxx;
        }

        public List<CbxxEntity> getCbxx() {
            return cbxx;
        }

        public void setCbxx(List<CbxxEntity> cbxx) {
            this.cbxx = cbxx;
        }

        public List<FkxxEntity> getFkxx() {
            return fkxx;
        }

        public void setFkxx(List<FkxxEntity> fkxx) {
            this.fkxx = fkxx;
        }

        public List<ZjmxEntity> getZjmx() {
            return zjmx;
        }

        public void setZjmx(List<ZjmxEntity> zjmx) {
            this.zjmx = zjmx;
        }

        public static class HtxxEntity implements Serializable {
            private int chargeCycle;
            private String content;
            private String contractType;
            private String createDate;
            private String createTime;
            private String createUser;
            private Object demo;
            private double deposit;
            private String endDate;
            private String houseId;
            private String landlordCardId;
            private String landlordId;
            private String landlordMobile;
            private Object landlordName;
            private double rent;
            private int rentCycle;
            private String roomId;
            private String roomerCardId;
            private String roomerId;
            private String roomerMobile;
            private String roomerName;
            private String startDate;
            private String sysId;
            private Object updDate;
            private Object updTime;
            private Object updUser;
            private int warmDay;

            public int getChargeCycle() {
                return chargeCycle;
            }

            public void setChargeCycle(int chargeCycle) {
                this.chargeCycle = chargeCycle;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getContractType() {
                return contractType;
            }

            public void setContractType(String contractType) {
                this.contractType = contractType;
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

            public Object getDemo() {
                return demo;
            }

            public void setDemo(Object demo) {
                this.demo = demo;
            }

            public double getDeposit() {
                return deposit;
            }

            public void setDeposit(double deposit) {
                this.deposit = deposit;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getHouseId() {
                return houseId;
            }

            public void setHouseId(String houseId) {
                this.houseId = houseId;
            }

            public String getLandlordCardId() {
                return landlordCardId;
            }

            public void setLandlordCardId(String landlordCardId) {
                this.landlordCardId = landlordCardId;
            }

            public String getLandlordId() {
                return landlordId;
            }

            public void setLandlordId(String landlordId) {
                this.landlordId = landlordId;
            }

            public String getLandlordMobile() {
                return landlordMobile;
            }

            public void setLandlordMobile(String landlordMobile) {
                this.landlordMobile = landlordMobile;
            }

            public Object getLandlordName() {
                return landlordName;
            }

            public void setLandlordName(Object landlordName) {
                this.landlordName = landlordName;
            }

            public double getRent() {
                return rent;
            }

            public void setRent(double rent) {
                this.rent = rent;
            }

            public int getRentCycle() {
                return rentCycle;
            }

            public void setRentCycle(int rentCycle) {
                this.rentCycle = rentCycle;
            }

            public String getRoomId() {
                return roomId;
            }

            public void setRoomId(String roomId) {
                this.roomId = roomId;
            }

            public String getRoomerCardId() {
                return roomerCardId;
            }

            public void setRoomerCardId(String roomerCardId) {
                this.roomerCardId = roomerCardId;
            }

            public String getRoomerId() {
                return roomerId;
            }

            public void setRoomerId(String roomerId) {
                this.roomerId = roomerId;
            }

            public String getRoomerMobile() {
                return roomerMobile;
            }

            public void setRoomerMobile(String roomerMobile) {
                this.roomerMobile = roomerMobile;
            }

            public String getRoomerName() {
                return roomerName;
            }

            public void setRoomerName(String roomerName) {
                this.roomerName = roomerName;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
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

            public int getWarmDay() {
                return warmDay;
            }

            public void setWarmDay(int warmDay) {
                this.warmDay = warmDay;
            }
        }

        public static class ZjxxEntity implements Serializable  {
            private String contractId;
            private double cost;
            private String createDate;
            private String createTime;
            private String createUser;
            private String endDate;
            private String houseId;
            private String landlordId;
            private String roomId;
            private String roomerId;
            private String startDate;
            private String state;
            private String sysId;
            private String updDate;
            private String updTime;
            private String updUser;

            public String getContractId() {
                return contractId;
            }

            public void setContractId(String contractId) {
                this.contractId = contractId;
            }

            public double getCost() {
                return cost;
            }

            public void setCost(double cost) {
                this.cost = cost;
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

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getHouseId() {
                return houseId;
            }

            public void setHouseId(String houseId) {
                this.houseId = houseId;
            }

            public String getLandlordId() {
                return landlordId;
            }

            public void setLandlordId(String landlordId) {
                this.landlordId = landlordId;
            }

            public String getRoomId() {
                return roomId;
            }

            public void setRoomId(String roomId) {
                this.roomId = roomId;
            }

            public String getRoomerId() {
                return roomerId;
            }

            public void setRoomerId(String roomerId) {
                this.roomerId = roomerId;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
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
        }

        public static class CbxxEntity implements Serializable  {
            private double accScale;
            private String createDate;
            private String createTime;
            private String createUser;
            private String demo;
            private Object flag;
            private String houseId;
            private String lastDate;
            private double lastScale;
            private String meterName;
            private String meterType;
            private int orderBy;
            private String roomId;
            private String roomNo;
            private String state;
            private String sysId;
            private String thisDate;
            private double thisScale;
            private Object updDate;
            private Object updTime;
            private Object updUser;

            public double getAccScale() {
                return accScale;
            }

            public void setAccScale(double accScale) {
                this.accScale = accScale;
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

            public String getDemo() {
                return demo;
            }

            public void setDemo(String demo) {
                this.demo = demo;
            }

            public Object getFlag() {
                return flag;
            }

            public void setFlag(Object flag) {
                this.flag = flag;
            }

            public String getHouseId() {
                return houseId;
            }

            public void setHouseId(String houseId) {
                this.houseId = houseId;
            }

            public String getLastDate() {
                return lastDate;
            }

            public void setLastDate(String lastDate) {
                this.lastDate = lastDate;
            }

            public double getLastScale() {
                return lastScale;
            }

            public void setLastScale(double lastScale) {
                this.lastScale = lastScale;
            }

            public String getMeterName() {
                return meterName;
            }

            public void setMeterName(String meterName) {
                this.meterName = meterName;
            }

            public String getMeterType() {
                return meterType;
            }

            public void setMeterType(String meterType) {
                this.meterType = meterType;
            }

            public int getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(int orderBy) {
                this.orderBy = orderBy;
            }

            public String getRoomId() {
                return roomId;
            }

            public void setRoomId(String roomId) {
                this.roomId = roomId;
            }

            public String getRoomNo() {
                return roomNo;
            }

            public void setRoomNo(String roomNo) {
                this.roomNo = roomNo;
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

            public String getThisDate() {
                return thisDate;
            }

            public void setThisDate(String thisDate) {
                this.thisDate = thisDate;
            }

            public double getThisScale() {
                return thisScale;
            }

            public void setThisScale(double thisScale) {
                this.thisScale = thisScale;
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

        public static class FkxxEntity implements Serializable  {
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

        public static class ZjmxEntity implements Serializable  {
            private String chargeType;
            private String costName;
            private double costPrice;
            private String costUnit;
            private String createDate;
            private String createTime;
            private String createUser;
            private Object lastDate;
            private Object lastScale;
            private String rentId;
            private String sysId;
            private Object thisDate;
            private Object thisScale;
            private double totalCost;
            private Object totalScale;
            private Object updDate;
            private Object updTime;
            private Object updUser;

            public String getChargeType() {
                return chargeType;
            }

            public void setChargeType(String chargeType) {
                this.chargeType = chargeType;
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

            public Object getLastDate() {
                return lastDate;
            }

            public void setLastDate(Object lastDate) {
                this.lastDate = lastDate;
            }

            public Object getLastScale() {
                return lastScale;
            }

            public void setLastScale(Object lastScale) {
                this.lastScale = lastScale;
            }

            public String getRentId() {
                return rentId;
            }

            public void setRentId(String rentId) {
                this.rentId = rentId;
            }

            public String getSysId() {
                return sysId;
            }

            public void setSysId(String sysId) {
                this.sysId = sysId;
            }

            public Object getThisDate() {
                return thisDate;
            }

            public void setThisDate(Object thisDate) {
                this.thisDate = thisDate;
            }

            public Object getThisScale() {
                return thisScale;
            }

            public void setThisScale(Object thisScale) {
                this.thisScale = thisScale;
            }

            public double getTotalCost() {
                return totalCost;
            }

            public void setTotalCost(double totalCost) {
                this.totalCost = totalCost;
            }

            public Object getTotalScale() {
                return totalScale;
            }

            public void setTotalScale(Object totalScale) {
                this.totalScale = totalScale;
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
