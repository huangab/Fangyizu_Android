package com.fangku.fyz.modular_house.bean;

/**
 * Created by   jie.wang
 * Date: 2016/8/18
 * Time: 10:30
 */
public class TestBean {
    private String mRoomNums;
    private String mNowNums;
    private String mNowTime;

    public String getRoomNums() {
        return mRoomNums;
    }

    public void setRoomNums(String roomNums) {
        mRoomNums = roomNums;
    }

    public String getNowNums() {
        return mNowNums;
    }

    public void setNowNums(String nowNums) {
        mNowNums = nowNums;
    }

    public String getNowTime() {
        return mNowTime;
    }

    public void setNowTime(String nowTime) {
        mNowTime = nowTime;
    }

    public String getOldNums() {
        return OldNums;
    }

    public void setOldNums(String oldNums) {
        OldNums = oldNums;
    }

    public String getOldTime() {
        return mOldTime;
    }

    public void setOldTime(String oldTime) {
        mOldTime = oldTime;
    }

    private String OldNums;
    private String mOldTime;
}
