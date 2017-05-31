package com.fangku.fyz.sql;/**
 * Created by 67343 on 2016/9/20.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fangku.fyz.bean.Bean_ListData;

import java.util.ArrayList;
import java.util.List;

/**
 * 周边配套及房间配套操纵表
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Sql_Table_Bean_ListData_Add {

    public SQLiteDatabase db;

    public Sql_Table_Bean_ListData_Add(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // 插入数据库
    public boolean insert(Bean_ListData hy) {
        ContentValues values = new ContentValues();
        values.put("name", hy.getName());
        long i = db.insert("Bean_ListData_Add", null, values);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    //更新表
    public void update(Bean_ListData hy, String sysId) {
        ContentValues values = new ContentValues();
        values.put("name", hy.getName());
        db.update("Bean_ListData_Add", values, "sysId = ?", new String[]{sysId});
    }

    /**
     * 清空表中的数据
     */
    public void clear() {
        db.delete("Bean_ListData_Add", null, null);
    }

    //查询表
    public List<String> select() {
        Cursor cursor = db.rawQuery("select * from Bean_ListData_Add", null);
        List<String> mList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String mBean = cursor.getString(cursor.getColumnIndex("name"));
            mList.add(mBean);
        }

        //关闭游标
        cursor.moveToFirst();

        return mList;
    }
}
