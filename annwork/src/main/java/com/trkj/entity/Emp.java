package com.trkj.entity;

import com.trkj.ann.Colnum;
import com.trkj.ann.MyJDBC;
import com.trkj.ann.Table;

@Table(tableName = "mep",dataBasebName = "MyTest")
public class Emp {

    @Colnum(isKey = true,isIncrement = true,colName = "empno")
    private int id;
    @Colnum(colName = "ename")
    private String empName;
    @Colnum(colName = "mgr")
    private int magrId;
    @Colnum(colName = "sal")
    private double sal;

    @MyJDBC(driver = "com.mysql.cj.jdbc.Driver",url = "jdbc:mysql://localhost:3306/js1?useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=GMT%2B8",username = "root",password = "991221")
    public void select(){

    }
}
