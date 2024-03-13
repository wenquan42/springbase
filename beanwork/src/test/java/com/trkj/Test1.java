package com.trkj;

import com.trkj.config.AppConfig;
import com.trkj.config.AppConfig2;
import com.trkj.dao.EmpDao;
import com.trkj.entity.Emp;
import com.trkj.entity.Student;
import com.trkj.serice.HellloService;
import com.trkj.serice.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Test1 {

    @Test
    public void test1(){
        Emp emp = new Emp();
        emp.setEmpno(1001);
        emp.setEnmae("Tom");
        System.out.println(emp.toString());
    }

    @Test
    public void test2(){

        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        Emp emp = context.getBean(Emp.class);
        log.debug("{}",emp);

        HellloService service = (HellloService) context.getBean("hello");
        HellloService service1 = (HellloService) context.getBean("h1");
        service.printInfo("Jack");
        service1.printInfo("Jack");
        log.debug("{}",service==service1);
        //比较两个对象的值
        Assert.assertEquals(service,service1);
        //比较两个对象的地址
        //Assert.assertSame(service,service1);

    }

    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        EmpDao dao = context.getBean(EmpDao.class);
        Emp emp = context.getBean(Emp.class);
        dao.addEmp(emp);
    }

    @Test
    public void test4(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig2.class);
        EmpDao dao = (EmpDao) context.getBean(EmpDao.class);
        Emp emp = context.getBean(Emp.class);
        dao.addEmp(emp);
    }

    @Test
    public void test5(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationConext.xml");
        Student student = (Student) context.getBean("student");
        student.study();
    }
    @Test
    public void test6(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Student student = context.getBean(Student.class);
        student.study();
        student.rest();
    }

    @Test
    public void test7(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        Order order = context.getBean(Order.class);
        order.processOrder();
        order.delivery();
    }

    @Test
    public void test8(){
        log.debug("提交测试");
    }

}
