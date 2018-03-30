package com.zsf.accountbook.model;

/**
 * Created by zsf
 * 2017/9/20
 * describe:数据模型之支出信息表
 */

public class ExpendTable {
    //支出编号
    private int id;
    //支出金额
    private double money;
    //支出时间
    private String time;
    //支出类别
    private String type;
    //地址
    private String address;
    //备注
    private String remark;

    public ExpendTable() {
        super();
    }

    public ExpendTable(int id, double money, String time, String type, String address, String remark) {
        this.id = id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
