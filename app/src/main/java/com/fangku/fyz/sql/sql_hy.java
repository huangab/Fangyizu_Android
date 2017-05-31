package com.fangku.fyz.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fangku.fyz.modular_hydropower.Bean_Hydropower;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地数据库抄表操作
 * Created by bowen.ye
 * Date: 2016/8/17
 * Time: 17:12
 */
public class sql_hy {

    public SQLiteDatabase db;

    public sql_hy(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // 插入数据库
    public void insert(Bean_Hydropower.DataEntity hy) {
        ContentValues values = new ContentValues();
        values.put("sysId", hy.getSysId());
        values.put("createDate", hy.getCreateDate());
        values.put("createTime", hy.getCreateTime());
        values.put("createUser", hy.getCreateUser());
        values.put("updDate", String.valueOf(hy.getUpdDate()));
        values.put("updTime", String.valueOf(hy.getUpdTime()));
        values.put("updUser", String.valueOf(hy.getUpdUser()));
        values.put("houseId", hy.getHouseId());
        values.put("roomId", hy.getRoomId());
        values.put("roomNo", hy.getRoomNo());
        values.put("meterType", hy.getMeterType());
        values.put("meterName", hy.getMeterName());
        values.put("thisDate", hy.getThisDate());
        values.put("thisScale", hy.getThisScale());
        values.put("lastDate", hy.getLastDate());
        values.put("lastScale", hy.getLastScale());
        values.put("accScale", hy.getAccScale());
        values.put("demo", hy.getDemo());
        values.put("state", hy.getState());
        values.put("flag", hy.getFlag());
        values.put("orderBy", hy.getOrderBy());
        db.insert("hy", null, values);
    }

    public void update(Bean_Hydropower.DataEntity hy,String sysId){
        ContentValues values = new ContentValues();
//        values.put("sysId", hy.getSysId());
        values.put("createDate", hy.getCreateDate());
        values.put("createTime", hy.getCreateTime());
        values.put("createUser", hy.getCreateUser());
        values.put("updDate", String.valueOf(hy.getUpdDate()));
        values.put("updTime", String.valueOf(hy.getUpdTime()));
        values.put("updUser", String.valueOf(hy.getUpdUser()));
        values.put("houseId", hy.getHouseId());
        values.put("roomId", hy.getRoomId());
        values.put("roomNo", hy.getRoomNo());
        values.put("meterType", hy.getMeterType());
        values.put("meterName", hy.getMeterName());
        values.put("thisDate", hy.getThisDate());
        values.put("thisScale", hy.getThisScale());
        values.put("lastDate", hy.getLastDate());
        values.put("lastScale", hy.getLastScale());
        values.put("accScale", hy.getAccScale());
        values.put("demo", hy.getDemo());
        values.put("state", hy.getState());
        values.put("flag", hy.getFlag());
        values.put("orderBy", hy.getOrderBy());
        values.put("isInput",hy.isInput());
        values.put("isRevise",hy.isRevise());
        db.update("hy", values, "sysId = ?", new String[]{sysId});
    }

    public void delete(){
        db.delete("hy",null,null);
    }

    public List<Bean_Hydropower.DataEntity> mQuery() {
        Cursor cursor = db.rawQuery("select * from hy", null);
        List<Bean_Hydropower.DataEntity> mList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Bean_Hydropower.DataEntity mBean=new Bean_Hydropower.DataEntity();
            mBean.setDemo(cursor.getString(cursor.getColumnIndex("demo")));
            mBean.setSysId(cursor.getString(cursor.getColumnIndex("sysId")));
            mBean.setRoomId(cursor.getString(cursor.getColumnIndex("roomId")));
            mBean.setRoomNo(cursor.getString(cursor.getColumnIndex("roomNo")));
            mBean.setCreateDate(cursor.getString(cursor.getColumnIndex("createDate")));
            mBean.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
            mBean.setCreateUser(cursor.getString(cursor.getColumnIndex("createUser")));
            mBean.setThisDate(cursor.getString(cursor.getColumnIndex("thisDate")));
            mBean.setThisScale(cursor.getDouble(cursor.getColumnIndex("thisScale")));
            mBean.setAccScale(cursor.getDouble(cursor.getColumnIndex("accScale")));
            mBean.setLastDate(cursor.getString(cursor.getColumnIndex("lastDate")));
            mBean.setLastScale(cursor.getDouble(cursor.getColumnIndex("lastScale")));
            mBean.setHouseId(cursor.getString(cursor.getColumnIndex("houseId")));
            mBean.setState(cursor.getString(cursor.getColumnIndex("state")));
            mBean.setUpdDate(cursor.getString(cursor.getColumnIndex("updDate")));
            mBean.setUpdTime(cursor.getString(cursor.getColumnIndex("updTime")));
            mBean.setUpdUser(cursor.getString(cursor.getColumnIndex("updUser")));
            mBean.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
            mBean.setOrderBy(cursor.getInt(cursor.getColumnIndex("orderBy")));
            mBean.setMeterName(cursor.getString(cursor.getColumnIndex("meterName")));
            mBean.setMeterType(cursor.getString(cursor.getColumnIndex("meterType")));
            mBean.setIsRevise(cursor.getString(cursor.getColumnIndex("isRevise")));
            mBean.setIsInput(cursor.getString(cursor.getColumnIndex("isInput")));
            mList.add(mBean);
        }

        //关闭游标
        cursor.moveToFirst();

        return mList;
    }
}
