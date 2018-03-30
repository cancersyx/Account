package com.zsf.accountbook.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zsf.accountbook.model.PasswordTable;


/**
 * Created by zsf
 * 2017/9/20
 * describe:对密码信息进行管理
 */

public class PasswordDao {

    private DbOpenHelper mHelper;
    private SQLiteDatabase mDatabase;

    public PasswordDao(Context context){
        mHelper = new DbOpenHelper(context);
    }

    /**
     * 添加密码
     *
     * @param passwordTable
     */
    public void add(PasswordTable passwordTable) {
        mDatabase = mHelper.getWritableDatabase();
        //执行添加收入操作
        mDatabase.execSQL("insert into password_table (password) values (?)",
                new Object[]{passwordTable.getPassword()});
    }

    /**
     * 更新收入信息
     *
     * @param passwordTable
     */
    public void update(PasswordTable passwordTable) {
        mDatabase = mHelper.getWritableDatabase();
        //执行修改收入操作信息
        mDatabase.execSQL("update password_table set password = ?",
                new Object[]{passwordTable.getPassword()});
    }

    public PasswordTable find(){
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select password from password_table where password = ?",
                new String[0]);
        if (cursor.moveToNext()){
            return new PasswordTable(cursor.getString(cursor.getColumnIndex("password")));
        }
        return null;
    }

    public long getCount(){
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select count(password) from password_table",null);
        if (cursor.moveToNext()){
            return cursor.getLong(0);
        }
        return 0;
    }

}
