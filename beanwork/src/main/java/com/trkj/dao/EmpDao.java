package com.trkj.dao;

import com.trkj.entity.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
//表示这是一个组件
// 可以在xml中或配置类中启动自动扫描
@Component
public class EmpDao {
    public int addEmp(Emp emp){
        log.debug("插入{}",emp);
        return 1;
    }
}
