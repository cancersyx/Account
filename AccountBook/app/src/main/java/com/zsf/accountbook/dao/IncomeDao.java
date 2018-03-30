package com.zsf.accountbook.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zsf.accountbook.model.IncomeTable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zsf
 * 2017/9/20
 * describe:对收入信息进行管理，包括收入信息的添加、删除、修改、查询和获取最大编号、总记录数等功能
 */

public class IncomeDao {
    public static final String INSERT_DATA = "insert into income_table (id,money,time,type,handler,remark) values (?,?,?,?,?,?)";
    public static final String UPDATE_DATA = "update income_table set money = ?,time = ?,type = ?,handler = ?,remark = ? where id = ?";
    public static final String SELECT_DATA = "select id,money,time,type,handler,remark from income_table where id = ?";
    private DbOpenHelper mHelper;
    private SQLiteDatabase mDatabase;

    public IncomeDao(Context context) {
        mHelper = new DbOpenHelper(context);
    }

    /**
     * 添加收入信息
     *
     * @param incomeTable
     */
    public void add(IncomeTable incomeTable) {
        mDatabase = mHelper.getWritableDatabase();
        //执行添加收入操作
        mDatabase.execSQL(INSERT_DATA,
                new Object[]{incomeTable.getId(), incomeTable.getMoney(), incomeTable.getTime(),
                        incomeTable.getType(), incomeTable.getHandler(), incomeTable.getRemark()});
    }

    /**
     * 更新收入信息
     *
     * @param incomeTable
     */
    public void update(IncomeTable incomeTable) {
        mDatabase = mHelper.getWritableDatabase();
        //执行修改收入操作信息
        mDatabase.execSQL(UPDATE_DATA,
                new Object[]{incomeTable.getMoney(), incomeTable.getTime(), incomeTable.getType(),
                        incomeTable.getHandler(), incomeTable.getRemark(), incomeTable.getId()});
    }

    /**
     * 根据指定的编号查找收入信息
     *
     * @param id 要找的收入编号
     * @return IncomeTable对象
     */
    public IncomeTable find(int id) {
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery(SELECT_DATA, new String[]{String.valueOf(id)});
        //遍历查找到的收入信息
        if (cursor.moveToNext()) {
            return new IncomeTable(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),
                    cursor.getString(cursor.getColumnIndex("remark"))
            );
        }

        return null;
    }

    /**
     * 删除收入信息
     *
     * @param ids
     */
    public void delete(Integer... ids) {
        if (ids.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < ids.length; i++) {
                sb.append('?').append(',');
            }
            sb.deleteCharAt(sb.length() - 1);//去掉最后一个“,”字符
            mDatabase = mHelper.getWritableDatabase();
            //执行删除收入操作
            mDatabase.execSQL("delete from income_table where id in (" + sb + ")", (Object[]) ids);
        }
    }

    /**
     * 获取收入信息集合
     *
     * @param start 其实位置
     * @param count 每页显示数量
     * @return
     */
    public List<IncomeTable> getScrollData(int start, int count) {
        List<IncomeTable> incomeTableList = new ArrayList<>();
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select * from income_table limit ?,?",
                new String[]{String.valueOf(start), String.valueOf(count)});
        while (cursor.moveToNext()) {
            incomeTableList.add(new IncomeTable(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),
                    cursor.getString(cursor.getColumnIndex("remark"))
            ));
        }
        return incomeTableList;
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount() {
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select count(id) from income_table", null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        return 0;
    }

    /**
     * 获取收入最大编号
     * @return
     */
    public int getMaxId(){
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select max(id) from income_table",null);
        while (cursor.moveToLast()){
            return cursor.getInt(0);
        }
        return 0;
    }

}
