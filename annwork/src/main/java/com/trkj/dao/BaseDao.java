package com.trkj.dao;

import com.trkj.ann.MyJDBC;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

    @MyJDBC(driver = "com.mysql.cj.jdbc.Driver",url = "jdbc:mysql://localhost:3306/js1?useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=GMT%2B8",username = "root",password = "991221")
    public Connection getConnection(){
        Class<BaseDao> cls = BaseDao.class;

        try {
            Method method = cls.getDeclaredMethod("getConnection");
            if(method.isAnnotationPresent(MyJDBC.class)){
            MyJDBC myJDBC = method.getDeclaredAnnotation(MyJDBC.class);
                Class.forName(myJDBC.driver());
                return DriverManager.getConnection(myJDBC.url(),myJDBC.username(),myJDBC.password());
            }
        } catch (NoSuchMethodException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
   /* public Connection getConnection(String driver, String url,String username,String password){
        try {
                Class.forName(driver);
                return DriverManager.getConnection(url,username,password);
            } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/
}
