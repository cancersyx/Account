package com.zsf.accountbook.model;

/**
 * Created by zsf
 * 2017/9/20
 * describe:数据模型之收入信息表
 */

public class IncomeTable {
    //收入编号
    private int id;
    //收入金额
    private double money;
    //收入时间
    private String time;
    //收入类别
    private String type;
    //收入付款方
    private String handler;
    //备注
    private String remark;

    public IncomeTable(){
        super();
    }

    public IncomeTable(int id, double money, String time, String type, String handler, String remark) {
        this.id = id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.handler = handler;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
