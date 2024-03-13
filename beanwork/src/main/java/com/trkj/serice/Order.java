package com.trkj.serice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Order {

    public void processOrder(){
        log.debug("处理订单");
    }

    public void delivery(){
        log.debug("发货");
    }

}
