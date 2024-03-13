package com.trkj.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Emp {
    private int empno;
    private String enmae;
    private String job;
    private int magr;
}
