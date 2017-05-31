package com.fangku.fyz.sql;/**
 * Created by 67343 on 2016/9/20.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * 照片操纵表
 * Created by   J
 * Date: 2016/7/11
 * Time: 15:04
 */
public class Sql_Table_Photo {

    public SQLiteDatabase db;

    public Sql_Table_Photo(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // 插入数据库
    public boolean insert(String name, String style) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("style", style);
        long i = db.insert("PhotoTable", null, values);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    //删除
    public void clear() {
        db.delete("PhotoTable", null, null);
    }

    //查询表
    public List<mPhotoClass> select() {
        Cursor cursor = db.rawQuery("select * from PhotoTable", null);
        List<mPhotoClass> mList = new ArrayList<>();

        while (cursor.moveToNext()) {
            mPhotoClass mBean = new mPhotoClass();
            mBean.setmPhotoName(cursor.getString(cursor.getColumnIndex("name")));
            mBean.setmStyle(cursor.getString(cursor.getColumnIndex("style")));
            mList.add(mBean);
        }

        //关闭游标
        cursor.moveToFirst();

        return mList;
    }

    public class mPhotoClass {
        String mPhotoName;
        String mStyle;

        public String getmPhotoName() {
            return mPhotoName;
        }

        public void setmPhotoName(String mPhotoName) {
            this.mPhotoName = mPhotoName;
        }

        public String getmStyle() {
            return mStyle;
        }

        public void setmStyle(String mStyle) {
            this.mStyle = mStyle;
        }
    }
}
