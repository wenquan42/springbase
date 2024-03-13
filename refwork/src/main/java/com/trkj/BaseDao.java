package com.trkj;

import java.sql.*;

public class BaseDao {
    private Connection con=null;
    private PreparedStatement ps=null;
    /**
     * 获取连接对象
     */
    public Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/js1?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai","root","991221");
        return con;
    }
    /**
     * 关闭连接对象
     */
    public void close(ResultSet rs) throws SQLException {
        if(rs!=null){
            rs.close();
        }
        if(ps!=null){
            ps.close();
        }
        if(con!=null){
            con.close();
        }
    }
    /**
     * 执行insert,update,delete命令
     */
    public int dataUpdate(String sql,Object...obj) throws ClassNotFoundException, SQLException {
        //创建连接
        this.getConn();
        //创建执行对象
        ps=con.prepareStatement(sql);
        //绑定参数
        if (obj!=null) {
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1, obj[i]);
            }
        }
        //执行sql语句
        int row=ps.executeUpdate();
        //关闭连接
        this.close(null);
        //返回结果
        return row;
    }
    /**
     * 执行select命令
     */
    public ResultSet dataQuery(String sql,Object...obj) throws ClassNotFoundException, SQLException {
        //创建连接
        this.getConn();
        //创建执行对象
        ps=con.prepareStatement(sql);
        //绑定参数
        if (obj!=null) {
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1, obj[i]);
            }
        }
        //System.out.println(ps);
        //执行sql语句
        ResultSet rs=ps.executeQuery();
        //返回结果集
        return rs;
    }


}
