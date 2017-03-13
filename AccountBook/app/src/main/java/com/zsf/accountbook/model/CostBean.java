package com.zsf.accountbook.model;

import java.io.Serializable;

/**
 * Created by zsf on 2017/2/28.
 */

public class CostBean implements Serializable{

    public String costCategory;//消费或收入的类别，比如工资还是兼职
    public String costDate;
    public String costMoney;
    public String costType;//记账的类型,收入和支出
    public String costRemark;//备注
}
