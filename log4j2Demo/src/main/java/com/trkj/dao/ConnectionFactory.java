package com.trkj.dao;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static interface Singleton{
        final ConnectionFactory INSTANCE = new ConnectionFactory();
    }
    private final DruidDataSource druidDataSource;

    private ConnectionFactory() {
        druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/js1?useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=GMT%2B8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("991221");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    public static Connection getDatabaseConnection() throws SQLException {
        return Singleton.INSTANCE.druidDataSource.getConnection();
    }


}
