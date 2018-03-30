package com.zsf.accountbook.model;

/**
 * Created by zsf
 * 2017/9/20
 * describe:数据模型之便签信息表
 */

public class MemoTable {
    private int id;
    private String memoContent;//便签内容
    private String time;//便签创建时间

    public MemoTable(){
        super();
    }

    public MemoTable(int id, String memoContent, String time) {
        this.id = id;
        this.memoContent = memoContent;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
