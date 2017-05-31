package com.fangku.fyz.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bowen.ye
 * Date: 2016/8/17
 * Time: 15:58
 */
public class DBHelper extends SQLiteOpenHelper {

    //抄表
    public static final String CREATE_FYZ_HY = "create table hy("
            + "sysId text primary key ,"
            + "createDate text,"
            + "createTime text,"
            + "createUser text,"
            + "roomNo text,"
            + "state text,"
            + "houseId text,"
            + "roomId text,"
            + "meterType text,"
            + "meterName text,"
            + "thisDate text,"
            + "thisScale Decimal,"
            + "lastDate text,"
            + "lastScale Decimal,"
            + "accScale Decimal,"
            + "demo text,"
            + "updDate text,"
            + "updTime text,"
            + "updUser text,"
            + "flag text,"
            + "isRevise text,"
            + "isInput text,"
            + "orderBy text)";


    //房产添加表
    public static final String CREATE_FYZ_Bean_Huose_Add = "create table Bean_Huose_Add("
            + "_sysId INTEGER primary key AUTOINCREMENT,"
            + "addrDetali text,"
            + "region text,"
            + "houseName text,"
            + "houseLevel text,"
            + "roomNumber text,"
            + "arroundSupportList text,"
            + "lng text,"
            + "lat text,"
            + "houseType text,"
            + "rentType text,"
            + "isLift text,"
            + "emptyNumber text,"
            + "image1 text,"
            + "image2 text,"
            + "image3 text)";

    //添加周边大类表
    public static final String CREATE_FYZ_Bean_ListData_Add = "create table Bean_ListData_Add(_sysId INTEGER primary key AUTOINCREMENT,name text)";

    //图片表
    private final String Create_FYZ_PhotoTable = "create table PhotoTable(_sysId INTEGER primary key AUTOINCREMENT,name text,style text)";

    //房产费用表
    public static final String CREATE_FYZ_HouseMoney = "create table HouseMoney("
            + "_sysId INTEGER primary key AUTOINCREMENT,"
            + "sysId INTEGER,"
            + "houseId INTEGER,"
            + "roomId text,"
            + "contractId text,"
            + "costName text,"
            + "costPrice text,"
            + "costUnit text,"
            + "minValue text,"
            + "chargeType text,"
            + "maxValue text,"
            + "meterCode text,"
            + "operate text)";

    //抄表
    public static final String CREATE_FYZ_Bean_House_RoomData = "create table Bean_House_RoomData("
            + "_sysId INTEGER primary key AUTOINCREMENT ,"
            + "roomNumber  text,"
            + "toiletNumber text,"
            + "hallNumber text,"
            + "depositNumber text,"
            + "payNumber text,"
            + "roomDesc text,"
            + "checkDate text)";


    public DBHelper(Context context) {
        super(context, "fyz", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FYZ_HY);
        db.execSQL(CREATE_FYZ_Bean_Huose_Add);
        db.execSQL(CREATE_FYZ_Bean_ListData_Add);
        db.execSQL(Create_FYZ_PhotoTable);
        db.execSQL(CREATE_FYZ_HouseMoney);
        db.execSQL(CREATE_FYZ_Bean_House_RoomData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists hy");
        db.execSQL("DROP TABLE if exists Bean_Huose_Add");
        db.execSQL("DROP TABLE if exists Bean_ListData_Add");
        db.execSQL("DROP TABLE if exists PhotoTable");
        db.execSQL("DROP TABLE if exists HouseMoney");
        db.execSQL("DROP TABLE if exists Bean_House_RoomData");
        onCreate(db);
    }
}
