package com.trkj.config;

import com.trkj.dao.EmpDao;
import com.trkj.entity.Emp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration//表示该是一个配置类
@ComponentScan(basePackages = "com.trkj")
public class AppConfig2 {

}
