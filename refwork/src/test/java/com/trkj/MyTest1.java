package com.trkj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class MyTest1{
    private static Logger logger = LogManager.getLogger(MyTest1.class);

   //获取类的方法和成员属性,并设置成员属性
    @Test
    public void test1() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        logger.debug("测试");
        Class<?> clazz = Emp.class;
        Emp emp = (Emp) clazz.newInstance();
        Field[] fields = clazz.getFields();
        logger.debug(emp);
        for(Field field : fields){
            logger.debug(field.getName());
        }
        logger.debug("-------------");
        Field[] fields1 = clazz.getDeclaredFields();
        for(Field field : fields1){
            logger.debug(field.getName());
        }
        logger.debug("-------------");
        Field f = clazz.getDeclaredField("ename");
        logger.debug(f.getName());
        f.setAccessible(true);
        f.set(emp,"小王");
        logger.debug(emp.toString());
        logger.debug(f.get(emp));
    }
    @Test
    public void test2(){
        String sql = assemblyInsertSql(Emp.class);
        logger.debug(sql);
    }
    public static String  assemblyInsertSql(Class<?> cls){
        StringBuffer sql = new StringBuffer("insert into ");
        sql.append(cls.getSimpleName());
        sql.append("(");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            sql.append(field.getName());
            sql.append(",");
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(") values (");
        for (Field field : fields) {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        return sql.toString();
    }

    //获取类的方法
    @Test
    public void test3() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> cls = Emp.class;
        Emp emp = (Emp) cls.newInstance();
        logger.debug("---获取类所有的方法---");
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            logger.debug(method.getName());
        }
        logger.debug("---获取getInfo无参方法---");
        Method m1 = cls.getDeclaredMethod("getInfo");//获取指定方法
        Parameter[] ps = m1.getParameters();//获取方法参数
        logger.debug(ps.length);
        logger.debug(m1.getReturnType());//获取方法返回类型
        logger.debug("---获取printInfo有参方法---");
        Method m2 = cls.getDeclaredMethod("printInfo", int.class, String.class);
        Parameter[] ps2 = m2.getParameters();
        logger.debug(ps2.length);
        logger.debug(m2.getReturnType());
        for (Parameter p : ps2) {
            logger.debug(p);
        }
        m2.invoke(emp,10,"aaa");//调动方法
    }

    //获取构造方法
    @Test
    public void test4() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> cls = Emp.class;
        Constructor<?>[] con = cls.getDeclaredConstructors();
        for (Constructor<?> constructor : con) {
            logger.debug(constructor.getParameters().length);
        }
        Constructor<?> con2 = cls.getDeclaredConstructor(int.class,String.class);
        Emp emp = (Emp) con2.newInstance(20,"Tom");
        logger.debug(emp.toString());
    }

    public static String  assemblySelectSql(Class<?> cls){
        //select id,ename,job,mgr,address from emp where id=?
       StringBuffer sql = new StringBuffer("select ");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            sql.append( field.getName());
            sql.append(",");
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(" from ");
        sql.append(cls.getSimpleName());
        sql.append( " where id=?");
        return sql.toString();
    }

    public static String assemblyUpdateSql(Class<?> cls){
        //update emp set ename=?,job=?,mgr=?,address=? where id = ?
        StringBuffer sql = new StringBuffer("update ");
        sql.append(cls.getSimpleName());
        sql.append(" set ");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if(!field.getName().equals("id")){
                sql.append(field.getName());
                sql.append("=?,");
            }
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(" where id=?");
        return sql.toString();
    }

    public static String assemblyDeleteSql(Class<?> cls){
        // delete from emp where id=?
        StringBuffer sql = new StringBuffer("delete from ");
        sql.append(cls.getSimpleName());
        sql.append(" where id=?");
        return sql.toString();
    }

    @Test
    public void test5() throws IllegalAccessException {
        logger.debug(assemblySelectSql(Emp.class));
        logger.debug(assemblyUpdateSql(Emp.class));
        logger.debug(assemblyInsertSql(Emp.class));
        logger.debug(assemblyDeleteSql(Emp.class));
       /* Map<String, String> map = new HashMap<>();
        map.put("name","小王");
        map.put("sex","男");
        map.put("phone","15505047703");
        User user = new User();
        assignmentClass(user,map);
        logger.debug(user.toString());*/
    }

    public static void assignmentClass(Object object, Map<String,String> map) throws IllegalAccessException {
        Class<?> cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(object,map.get(field.getName()));
        }
    }

    @Test
    public void test6(){
        Class<?> cls = User.class;
        logger.debug("{}类所在的包:{}",cls.getSimpleName(),cls.getPackage());
        logger.debug("{}类的父类:{}",cls.getSimpleName(),cls.getSuperclass());
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            logger.debug("{}类实现的接口:{}",cls.getSimpleName(),anInterface.getSimpleName());
        }
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            logger.debug("{}类的成员属性:{}",cls.getSimpleName(),field.getName());
        }
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            logger.debug("{}类的成员方法:{},返回类型:{},参数个数:{}",cls.getSimpleName(),method.getName(),method.getReturnType(),method.getParameters().length);
        }
        Constructor<?>[] cons = cls.getDeclaredConstructors();
        for (Constructor<?> con : cons) {
            logger.debug("{}类的构造方法:{},参数个数:{}",cls.getSimpleName(),con.getName(),con.getParameters().length);
        }

    }


}
