package com.trkj;

import com.trkj.ann.Colnum;
import com.trkj.ann.MyJDBC;
import com.trkj.ann.Table;
import com.trkj.dao.BaseDao;
import com.trkj.entity.Emp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;

public class Test1 {
    public static Logger logger = LogManager.getLogger(Test1.class);
    @Test
    public void test1(){
        Class<?> cls = Emp.class;
        logger.debug(cls.getName());
        logger.debug(cls.getSimpleName());
        Table annotation = cls.getDeclaredAnnotation(Table.class);
        logger.debug(annotation);
        logger.debug(cls.isAnnotationPresent(Table.class));
        logger.debug("{},{}",annotation.tableName(),annotation.dataBasebName());
    }

    @Test
    public void test2(){
        //insert into emp(ename,mgr,sal) values(?,?,?,?)
        StringBuffer sql = new StringBuffer("insert into ");
        Class<Emp> cls = Emp.class;
        Table table = cls.getDeclaredAnnotation(Table.class);
        sql.append(table.tableName());
        sql.append(" (");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            //isAnnotationPresent判断指定注解是否存在
            if(field.isAnnotationPresent(Colnum.class)){
                //getDeclaredAnnotation得到指定的注解
                Colnum col = field.getDeclaredAnnotation(Colnum.class);
                if(col.isIncrement()){
                    continue;
                }
                sql.append(col.colName());
                sql.append(",");
            }
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(") values (");
        for (Field field : fields) {
            //isAnnotationPresent判断指定注解是否存在
            if(field.isAnnotationPresent(Colnum.class)){
                //getDeclaredAnnotation得到指定的注解
                Colnum col = field.getDeclaredAnnotation(Colnum.class);
                if(col.isIncrement()){
                    continue;
                }
                sql.append("?,");
            }
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");

        logger.debug(sql.toString());

    }

    @Test
    public void test3(){
        logger.debug(assemblySelectSql());
        logger.debug(assemblyInsertSql());
        logger.debug(assemblyDeleteSql());
        logger.debug(assemblyUpdateSql());

    }

    @Test
    public void test4() throws NoSuchMethodException {
       /* Class<Emp> cls = Emp.class;
        Method method = cls.getDeclaredMethod("select");
        if (method.isAnnotationPresent(MyJDBC.class)){
            MyJDBC myJDBC = method.getDeclaredAnnotation(MyJDBC.class);
            BaseDao baseDao = new BaseDao();
            Connection connection = baseDao.getConnection(myJDBC.driver(), myJDBC.url(), myJDBC.username(), myJDBC.password());
            logger.debug(connection);
        }*/
        BaseDao baseDao = new BaseDao();
        logger.debug(baseDao.getConnection());
    }

    public String assemblySelectSql(){
        //select empno,ename,mgr,sal form emp
        StringBuffer sql = new StringBuffer("select ");
        Class<Emp> cls = Emp.class;
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(Colnum.class)){
                Colnum col = field.getDeclaredAnnotation(Colnum.class);
                sql.append(col.colName());
                sql.append(",");
            }
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(" from ");
        Table table = cls.getDeclaredAnnotation(Table.class);
        sql.append(table.tableName());
        sql.append(" where ");
        for (Field field : fields) {
            if(field.isAnnotationPresent(Colnum.class)){
                Colnum col = field.getDeclaredAnnotation(Colnum.class);
                if(col.isKey()){
                    sql.append(col.colName());
                    sql.append("=?");
                }
            }
        }
        return sql.toString();
    }

    public String assemblyDeleteSql(){
        //delete from emp where empno=?
        StringBuffer sql = new StringBuffer("delete from ");
        Class<Emp> cls = Emp.class;
        sql.append(cls.getDeclaredAnnotation(Table.class).tableName());
        sql.append(" where ");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(Colnum.class)){
                Colnum col = field.getDeclaredAnnotation(Colnum.class);
                if(col.isKey()){
                    sql.append(col.colName());
                    sql.append("=?");
                }
            }
        }
        return sql.toString();
    }

    public String assemblyInsertSql(){
        //insert into emp(ename,mgr,sal) values(?,?,?,?)
        StringBuffer sql = new StringBuffer("insert into ");
        Class<Emp> cls = Emp.class;
        Table table = cls.getDeclaredAnnotation(Table.class);
        sql.append(table.tableName());
        sql.append(" (");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            //isAnnotationPresent判断指定注解是否存在
            if(field.isAnnotationPresent(Colnum.class)){
                //getDeclaredAnnotation得到指定的注解
                Colnum col = field.getDeclaredAnnotation(Colnum.class);
                if(col.isIncrement()){
                    continue;
                }
                sql.append(col.colName());
                sql.append(",");
            }
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(") values (");
        for (Field field : fields) {
            //isAnnotationPresent判断指定注解是否存在
            if(field.isAnnotationPresent(Colnum.class)){
                //getDeclaredAnnotation得到指定的注解
                Colnum col = field.getDeclaredAnnotation(Colnum.class);
                if(col.isIncrement()){
                    continue;
                }
                sql.append("?,");
            }
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        return sql.toString();
    }

    public String assemblyUpdateSql(){
        //update emp set ename=?,mgr=?,sal=? where empno=?
        StringBuffer sql = new StringBuffer("update ");
        Class<Emp> cls = Emp.class;
        sql.append(cls.getDeclaredAnnotation(Table.class).tableName());
        sql.append(" set ");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Colnum.class)){
                Colnum col = field.getDeclaredAnnotation(Colnum.class);
                if (col.isKey()){
                    continue;
                }
                sql.append(col.colName());
                sql.append("=?,");
            }
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(" where ");
        for (Field field : fields) {
            if (field.isAnnotationPresent(Colnum.class)){
                Colnum col = field.getDeclaredAnnotation(Colnum.class);
                if (col.isKey()){
                    sql.append(col.colName());
                    sql.append("=?");
                }
            }
        }
        return sql.toString();
    }

}
