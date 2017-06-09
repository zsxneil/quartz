package com.my.quartz.cluster;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("simpleService")
public class SimpleService {
    
    private static final Logger logger = Logger.getLogger(SimpleService.class);
    
    public void testMethod1(){
        //这里执行定时调度业务
        logger.info("testMethod1.......1");
        System.out.println("2--testMethod1......."+System.currentTimeMillis()/1000);
    }
    
    public void testMethod2(){
        logger.info("testMethod2.......2");    
    }
}
