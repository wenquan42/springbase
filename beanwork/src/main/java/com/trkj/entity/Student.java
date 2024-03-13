package com.trkj.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Student {

    private int studentNo;
    private String  name;
    private String sex;
    private int age;

    @Override
    public String toString() {
        return "Student{" +
                "studentNo=" + studentNo +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    public void study(){
        log.debug("学习");
    }

    public void rest(){
        log.debug("休息");
    }

}
