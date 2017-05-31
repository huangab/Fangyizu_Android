package com.fangku.fyz.sql;/**
 * Created by 67343 on 2016/9/20.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fangku.fyz.modular_house.bean.Bean_Huose_Add;

/**
 * 房产数据操纵表
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Sql_Table_Bean_Huose_Add {

    public SQLiteDatabase db;

    public Sql_Table_Bean_Huose_Add(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // 插入数据库
    public boolean insert(Bean_Huose_Add hy) {
        ContentValues values = new ContentValues();
        values.put("addrDetali", hy.getAddrDetali());
        values.put("region", hy.getRegion());
        values.put("houseName", hy.getHouseName());
        values.put("houseLevel", hy.getHouseLevel());
        values.put("roomNumber", hy.getRoomNumber());
//        values.put("arroundSupportList", hy.getArroundSupportList().toString());
        values.put("lng", hy.getLng());
        values.put("lat", hy.getLat());
        values.put("houseType", hy.getHouseType());
        values.put("rentType", hy.getRentType());
        values.put("isLift", hy.getIsLift());
        values.put("emptyNumber", hy.getEmptyNumber());
        values.put("image1", hy.getImage1());
        values.put("image2", hy.getImage2());
        values.put("image3", hy.getImage3());
        long i = db.insert("Bean_Huose_Add", null, values);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    //更新表
    public void update(Bean_Huose_Add hy, String sysId) {
        ContentValues values = new ContentValues();
//        values.put("sysId", hy.getSysId());
        values.put("addrDetali", hy.getAddrDetali());
        values.put("region", hy.getRegion());
        values.put("houseName", hy.getHouseName());
        values.put("houseLevel", hy.getHouseLevel());
        values.put("roomNumber", hy.getRoomNumber());
//        values.put("arroundSupportList", hy.getArroundSupportList().toString());
        values.put("lng", hy.getLng());
        values.put("lat", hy.getLat());
        values.put("houseType", hy.getHouseType());
        values.put("rentType", hy.getRentType());
        values.put("isLift", hy.getIsLift());
        values.put("emptyNumber", hy.getEmptyNumber());
        values.put("image1", hy.getImage1());
        values.put("image2", hy.getImage2());
        values.put("image3", hy.getImage3());
        db.update("Bean_Huose_Add", values, "sysId = ?", new String[]{sysId});
    }

    //删除
    public void clear() {
        db.delete("Bean_Huose_Add", null, null);
    }

    //查询表
    public Bean_Huose_Add select() {
        Cursor cursor = db.rawQuery("select * from Bean_Huose_Add", null);
        Bean_Huose_Add mList = new Bean_Huose_Add();

        while (cursor.moveToNext()) {
            Bean_Huose_Add mBean = new Bean_Huose_Add();
            mBean.setAddrDetali(cursor.getString(cursor.getColumnIndex("addrDetali")));
            mBean.setRegion(cursor.getString(cursor.getColumnIndex("region")));
            mBean.setHouseName(cursor.getString(cursor.getColumnIndex("houseName")));
            mBean.setHouseLevel(cursor.getString(cursor.getColumnIndex("houseLevel")));
            mBean.setRoomNumber(cursor.getString(cursor.getColumnIndex("roomNumber")));
//            mBean.setArroundSupportList(cursor.getString(cursor.getColumnIndex("arroundSupportList")));
            mBean.setLat(Double.valueOf(cursor.getString(cursor.getColumnIndex("lng"))));
            mBean.setLng(Double.valueOf(cursor.getString(cursor.getColumnIndex("lat"))));
            mBean.setHouseType(cursor.getString(cursor.getColumnIndex("houseType")));
            mBean.setRentType(cursor.getString(cursor.getColumnIndex("rentType")));
            mBean.setIsLift(cursor.getString(cursor.getColumnIndex("isLift")));
            mBean.setEmptyNumber(cursor.getString(cursor.getColumnIndex("emptyNumber")));
            mBean.setImage1(cursor.getString(cursor.getColumnIndex("image1")));
            mBean.setImage2(cursor.getString(cursor.getColumnIndex("image2")));
            mBean.setImage3(cursor.getString(cursor.getColumnIndex("image3")));
            mList = mBean;
        }

        //关闭游标
        cursor.moveToFirst();

        return mList;
    }
}
