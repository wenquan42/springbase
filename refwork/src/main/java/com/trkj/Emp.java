package com.trkj;

import java.sql.SQLOutput;

public class Emp {
//    private int empno;
    private int id;
    private String ename;
    private String job;
    private String mgr;
    public String address;

    public String getInfo(){
        return "Emp{" +
                " ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", address='" + address + '\'' +
                '}';
    }

    public void printInfo(int a, String b){
        System.out.println("a:"+a+" b:"+b);
    }

    @Override
    public String toString() {
        return "Emp{" +
                " ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", address='" + address + '\'' +
                '}';
    }

    public Emp() {
    }

    public Emp(int id, String ename) {
        this.id = id;
        this.ename = ename;
    }
}
