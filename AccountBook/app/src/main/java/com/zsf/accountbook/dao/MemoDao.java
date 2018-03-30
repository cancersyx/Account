package com.zsf.accountbook.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zsf.accountbook.model.MemoTable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zsf
 * 2017/9/20
 * describe:对便签信息进行管理
 */

public class MemoDao {
    private DbOpenHelper mHelper;
    private SQLiteDatabase mDatabase;

    public MemoDao(Context context) {
        mHelper = new DbOpenHelper(context);
    }

    /**
     * 添加新的便签
     *
     * @param memoTable
     */
    public void add(MemoTable memoTable) {
        mDatabase = mHelper.getWritableDatabase();
        //执行添加便签操作
        mDatabase.execSQL("insert into memo_table (id,memoContent,time) values (?,?,?)",
                new Object[]{memoTable.getId(), memoTable.getMemoContent(), memoTable.getTime()});
    }

    public void update(MemoTable memoTable) {
        mDatabase = mHelper.getWritableDatabase();
        //执行修改便签信息
        mDatabase.execSQL("update memo_table set memoContent = ?,time = ? where id = ?",
                new Object[]{memoTable.getMemoContent(), memoTable.getId()});
    }

    /**
     * 根据指定的编号查找便签信息
     *
     * @param id 要找的便签编号
     * @return
     */
    public MemoTable find(int id) {
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select id,memoContent,time from memo_table where id = ?", new String[]{String.valueOf(id)});
        //遍历查找到的收入信息
        if (cursor.moveToNext()) {
            return new MemoTable(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("memoContent")),
                    cursor.getString(cursor.getColumnIndex("time"))
            );
        }

        return null;
    }

    public void delete(Integer... ids) {
        if (ids.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < ids.length; i++) {
                sb.append('?').append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            mDatabase = mHelper.getWritableDatabase();
            //执行删除便签操作
            mDatabase.execSQL("delete from memo_table where id in (" + sb + ")", ids);
        }
    }

    public List<MemoTable> getScrollData(int start, int count) {
        List<MemoTable> memoTableList = new ArrayList<>();
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select * from memo_table limit ?,?",
                new String[]{String.valueOf(start), String.valueOf(count)});
        while (cursor.moveToNext()) {
            memoTableList.add(new MemoTable(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("memoContent")),
                    cursor.getString(cursor.getColumnIndex("time"))
            ));
        }
        return memoTableList;
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount() {
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select count(id) from memo_table", null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        return 0;
    }

    /**
     * 获取收入最大编号
     *
     * @return
     */
    public int getMaxId() {
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select max(id) from memo_table", null);
        while (cursor.moveToLast()) {
            return cursor.getInt(0);
        }
        return 0;
    }


}
