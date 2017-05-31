package com.fangku.fyz.sql;/**
 * Created by 67343 on 2016/9/23.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fangku.fyz.modular_house.bean.Bean_House_RoomData;

import java.util.ArrayList;
import java.util.List;

/**
 * 在新建房产过程中，用于存储房间信息
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Sql_Table_Bean_House_RoomData {
    public SQLiteDatabase db;

    public Sql_Table_Bean_House_RoomData(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // 插入数据库
    public boolean insert(Bean_House_RoomData hy) {
        ContentValues values = new ContentValues();
        values.put("roomNumber", hy.getRoomNumber() + "");
        values.put("toiletNumber", hy.getToiletNumber());
        values.put("hallNumber", hy.getHallNumber());
        values.put("depositNumber", hy.getDepositNumber());
        values.put("payNumber", hy.getPayNumber());
        values.put("checkDate", hy.getCheckDate().toString());
        values.put("roomDesc", hy.getRoomDesc());
        long i = db.insert("Bean_House_RoomData", null, values);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 清空表中的数据
     */
    public void clear() {
        db.delete("Bean_House_RoomData", null, null);
    }

    //查询表
    public List<Bean_House_RoomData> select() {
        Cursor cursor = db.rawQuery("select * from Bean_House_RoomData", null);
        List<Bean_House_RoomData> mList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Bean_House_RoomData mBean = new Bean_House_RoomData();
            mBean.setRoomNumber(cursor.getString(cursor.getColumnIndex("roomNumber")));
            mBean.setToiletNumber(cursor.getString(cursor.getColumnIndex("toiletNumber")));
            mBean.setHallNumber(cursor.getString(cursor.getColumnIndex("hallNumber")));
            mBean.setDepositNumber(cursor.getString(cursor.getColumnIndex("depositNumber")));
            mBean.setPayNumber(cursor.getString(cursor.getColumnIndex("payNumber")));
            mBean.setRoomDesc(cursor.getString(cursor.getColumnIndex("roomDesc")));
            mBean.setCheckDate(cursor.getString(cursor.getColumnIndex("checkDate")));
            mList.add(mBean);
        }

        //关闭游标
        cursor.moveToFirst();

        return mList;
    }
}
