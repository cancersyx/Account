package com.zsf.accountbook.model;

/**
 * Created by zsf
 * 2017/9/20
 * describe:数据模型之密码信息数据表模型类
 */

public class PasswordTable {
    private String password;

    public PasswordTable(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
