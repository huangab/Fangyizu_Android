package com.fangku.fyz.modular_me.bean;

import java.util.List;

/**
 * Created by   jie.wang
 * Date: 2016/8/22
 * Time: 17:30
 */
public class Bean_Me_Message {
    /**
     * code : 200
     * data : [{"createDate":"2016-08-19","createTime":"10:23:01","createUser":"1469498309126Kp8kA","msgContent":"132","msgType":"231","publish":"12","publishDate":"2016-08-19","publishDemo":"","publishTime":"10:22:40","publishUserId":"1469498309126Kp8kA","receiverId":"","sysId":"20150719100000000001","title":"123","updDate":"","updTime":"","updUser":""},{"createDate":"2016-08-19","createTime":"10:23:01","createUser":"1469498309126Kp8kA","msgContent":"132","msgType":"231","publish":"12","publishDate":"2016-08-19","publishDemo":null,"publishTime":"10:22:40","publishUserId":"1469498309126Kp8kA","receiverId":"1470736392341zHQVF","sysId":"20160819100000000001","title":"123","updDate":null,"updTime":null,"updUser":null}]
     * message : 加载数据成功
     */

    private String code;
    private String message;
    /**
     * createDate : 2016-08-19
     * createTime : 10:23:01
     * createUser : 1469498309126Kp8kA
     * msgContent : 132
     * msgType : 231
     * publish : 12
     * publishDate : 2016-08-19
     * publishDemo :
     * publishTime : 10:22:40
     * publishUserId : 1469498309126Kp8kA
     * receiverId :
     * sysId : 20150719100000000001
     * title : 123
     * updDate :
     * updTime :
     * updUser :
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
        private String createDate;
        private String createTime;
        private String createUser;
        private String msgContent;
        private String msgType;
        private String publish;
        private String publishDate;
        private String publishDemo;
        private String publishTime;
        private String publishUserId;
        private String receiverId;
        private String sysId;
        private String title;
        private String updDate;
        private String updTime;
        private String updUser;

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

        public String getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(String msgContent) {
            this.msgContent = msgContent;
        }

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public String getPublish() {
            return publish;
        }

        public void setPublish(String publish) {
            this.publish = publish;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getPublishDemo() {
            return publishDemo;
        }

        public void setPublishDemo(String publishDemo) {
            this.publishDemo = publishDemo;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getPublishUserId() {
            return publishUserId;
        }

        public void setPublishUserId(String publishUserId) {
            this.publishUserId = publishUserId;
        }

        public String getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(String receiverId) {
            this.receiverId = receiverId;
        }

        public String getSysId() {
            return sysId;
        }

        public void setSysId(String sysId) {
            this.sysId = sysId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
}
