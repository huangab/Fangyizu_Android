package com.fangku.fyz.sql;/**
 * Created by 67343 on 2016/9/22.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fangku.fyz.modular_house.bean.Bean_House_All_RoomCharge;

import java.util.ArrayList;
import java.util.List;

/**
 * 房产费用操纵表
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Sql_Table_HouseMoney {
    public SQLiteDatabase db;

    public Sql_Table_HouseMoney(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // 插入数据库
    public void insert(List<Bean_House_All_RoomCharge> style) {
        ContentValues values = new ContentValues();
        for (int j = 0; j < style.size(); j++) {
            values.put("sysId", style.get(j).getSysId());
            values.put("houseId", style.get(j).getHouseId());
            values.put("contractId", style.get(j).getContractId());
            values.put("costName", style.get(j).getCostName());
            values.put("costPrice", style.get(j).getCostPrice());
            values.put("costUnit", style.get(j).getCostUnit());
            values.put("minValue", style.get(j).getMinValue());
            values.put("chargeType", style.get(j).getChargeType());
            values.put("maxValue", style.get(j).getMaxValue());
            values.put("meterCode", style.get(j).getMeterCode());
            values.put("operate", style.get(j).getOperate());
            long i = db.insert("HouseMoney", null, values);
        }
    }

    //删除全部数据
    public void clear() {
        db.delete("HouseMoney", null, null);
    }

    //查询表
    public List<Bean_House_All_RoomCharge> select() {
        Cursor cursor = db.rawQuery("select * from HouseMoney", null);
        List<Bean_House_All_RoomCharge> mList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Bean_House_All_RoomCharge mBean = new Bean_House_All_RoomCharge();
            mBean.setSysId(cursor.getString(cursor.getColumnIndex("sysId")));
            mBean.setHouseId(cursor.getString(cursor.getColumnIndex("houseId")));
            mBean.setContractId(cursor.getString(cursor.getColumnIndex("contractId")));
            mBean.setCostName(cursor.getString(cursor.getColumnIndex("costName")));
            mBean.setCostPrice(cursor.getDouble(cursor.getColumnIndex("costPrice")));
            mBean.setCostUnit(cursor.getString(cursor.getColumnIndex("costUnit")));
            mBean.setMinValue(cursor.getInt(cursor.getColumnIndex("minValue")));
            mBean.setChargeType(cursor.getString(cursor.getColumnIndex("chargeType")));
            mBean.setMaxValue(cursor.getInt(cursor.getColumnIndex("maxValue")));
            mBean.setMeterCode(cursor.getString(cursor.getColumnIndex("meterCode")));
            mBean.setOperate(cursor.getString(cursor.getColumnIndex("operate")));
            mList.add(mBean);
        }

        //关闭游标
        cursor.moveToFirst();

        return mList;
    }
}
