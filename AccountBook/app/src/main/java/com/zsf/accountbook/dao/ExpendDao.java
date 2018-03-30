package com.zsf.accountbook.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zsf.accountbook.model.ExpendTable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zsf
 * 2017/9/20
 * describe:对支出信息进行管理,包括支出信息的添加、修改、删除、查询及获取最大编号，总记录数等
 */

public class ExpendDao {

    public static final String INSERT_EXPEND_DATA = "insert into expend_table (id,money,time,type,address,remark) values (?,?,?,?,?,?)";
    public static final String UPDATE_EXPEND_DATA = "update expend_table set money = ?,time = ?,type = ?,address = ?,remark = ? where id = ?";
    public static final String SELECT_EXPEND_DATA = "select id,money,time,type,address,remark from expend_table where id = ?";
    private DbOpenHelper mHelper;
    private SQLiteDatabase mDatabase;

    public ExpendDao(Context context) {
        mHelper = new DbOpenHelper(context);
    }

    /**
     * 添加支出信息
     *
     * @param expendTable
     */
    public void add(ExpendTable expendTable) {
        mDatabase = mHelper.getWritableDatabase();
        //执行添加支出信息操作
        mDatabase.execSQL(INSERT_EXPEND_DATA,
                new Object[]{expendTable.getId(), expendTable.getMoney(),
                        expendTable.getTime(), expendTable.getType(), expendTable.getAddress(),
                        expendTable.getRemark()});
    }

    /**
     * 根据指定编号修改支出信息
     *
     * @param expendTable
     */
    public void update(ExpendTable expendTable) {
        mDatabase = mHelper.getWritableDatabase();
        //执行修改支出信息操作
        mDatabase.execSQL(UPDATE_EXPEND_DATA,
                new Object[]{expendTable.getMoney(), expendTable.getTime(), expendTable.getType(),
                        expendTable.getAddress(), expendTable.getRemark(), expendTable.getId()});
    }

    /**
     * 根据指定编号差找支出信息
     *
     * @param id
     * @return
     */
    public ExpendTable find(int id) {
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery(SELECT_EXPEND_DATA, new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            return new ExpendTable(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("remark"))
            );
        }
        return null;
    }

    /**
     * 根据指定的一系列编号删除支出信息
     *
     * @param ids
     */
    public void delete(Integer... ids) {
        if (ids.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < ids.length; i++) {
                sb.append('?').append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            mDatabase = mHelper.getWritableDatabase();
            //执行删除支出信息操作
            mDatabase.execSQL("delete from expend_table where id in (" + sb + ")", (Object[]) ids);
        }
    }

    /**
     * 从支出数据表的指定索引出获取指定数量的支出数据
     *
     * @param start
     * @param count
     * @return
     */
    public List<ExpendTable> getScrollData(int start, int count) {
        List<ExpendTable> expendTableList = new ArrayList<>();
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select * from expend_table limit ?,?",
                new String[]{String.valueOf(start), String.valueOf(count)});
        while (cursor.moveToNext()) {
            expendTableList.add(new ExpendTable(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("remark"))
            ));
        }
        return expendTableList;
    }

    /**
     * 获取支出信息数据表中的总记录数
     *
     * @return
     */
    public long getCount() {
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select count(id) from expend_table", null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        return 0;
    }

    /**
     * 获取支出数据表中的最大编号
     *
     * @return 最大编号
     */
    public int getMaxId() {
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select max(id) from expend_table", null);
        while (cursor.moveToLast()) {
            return cursor.getInt(0);
        }
        return 0;
    }

}
