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
            + "cost_category varchar,"
            + "cost_date varchar,"
            + "cost_money varchar,"
            + "cost_type varchar,"
            + "cost_remark varchar)";

    public static final String COST_CATEGORY = "cost_category";
    public static final String COST_DATE = "cost_date";
    public static final String COST_MONEY = "cost_money";
    public static final String COST = "cost";//表名
    public static final String DAILY_COST = "daily_cost";//数据库名
    public static final String COST_TYPE = "cost_type";//收入，支出类型
    public static final String COST_REMARK = "cost_remark";//备注


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
        values.put(COST_CATEGORY, costBean.costCategory);
        values.put(COST_DATE, costBean.costDate);
        values.put(COST_MONEY, costBean.costMoney);
        values.put(COST_TYPE,costBean.costType);
        values.put(COST_REMARK,costBean.costRemark);
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
