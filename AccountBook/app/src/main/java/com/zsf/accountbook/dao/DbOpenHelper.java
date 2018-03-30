package com.zsf.accountbook.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zsf
 * 2017/9/20
 * describe:创建数据库、创建数据表等功能
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "account.db";
    public static final String EXPEND_TABLE = "create table expend_table (id integer primary key,money decimal,time varchar(10),type varchar(10),address varchar(100),remark varchar(200))";
    public static final String INCOME_TABLE = "create table income_table (id integer primary key,money decimal,time varchar(10),type varchar(10),handler varchar(100),remark varchar(200))";
    public static final String PASSWORD_TABLE = "create table password_table (password varchar(20))";
    public static final String MEMO_TABLE = "create table memo_table (id integer primary key,memoContent,time varchar(200))";

    public DbOpenHelper(Context context){
        super(context,DB_NAME,null,VERSION);//重写基类的构造函数
    }

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /**
     * 创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EXPEND_TABLE);//创建支出信息表
        db.execSQL(INCOME_TABLE);//创建收入信息表
        db.execSQL(PASSWORD_TABLE);//创建密码表
        db.execSQL(MEMO_TABLE);//创建便签信息表
    }

    /**
     * 更新数据库版本
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
