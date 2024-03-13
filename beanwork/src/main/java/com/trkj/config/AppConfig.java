package com.trkj.config;

import com.trkj.dao.EmpDao;
import com.trkj.entity.Emp;
import com.trkj.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//表示该是一个配置类
public class AppConfig {

    @Bean
    public Emp emp(){
        return new Emp();
    }

    @Bean
    public EmpDao empDao(){
        return new EmpDao();
    }

    @Bean
    public Student student(){
        return new Student();
    }
}
