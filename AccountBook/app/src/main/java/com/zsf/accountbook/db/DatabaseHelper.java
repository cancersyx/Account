package com.zsf.accountbook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zsf.accountbook.model.CostBean;

/**
 * Created by zsf on 2017/2/28.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_COST = "create table cost ("
            + "id integer primary key autoincrement,"
            + "cost_title varchar,"
            + "cost_date varchar,"
            + "cost_money varchar)";
    public static final String COST_TITLE = "cost_title";
    public static final String COST_DATE = "cost_date";
    public static final String COST_MONEY = "cost_money";
    public static final String COST = "cost";//表名
    public static final String DAILY_COST = "daily_cost";//数据库名


    /**
     * 数据库名是daily_cost
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DAILY_COST, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COST);
    }

    public void insertCost(CostBean costBean) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COST_TITLE, costBean.costTitle);
        values.put(COST_DATE, costBean.costDate);
        values.put(COST_MONEY, costBean.costMoney);
        database.insert(COST, null, values);
    }

    public Cursor getAllCostData(){
        SQLiteDatabase database = getWritableDatabase();
        return database.query(COST,null,null,null,null,null,COST_DATE + " ASC");
    }

    public void deleteAllCostData(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(COST,null,null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
