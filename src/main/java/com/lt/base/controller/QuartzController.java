package com.lt.base.controller;

import com.lt.base.config.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class QuartzController {

    private final static String LOCK_ID = "happyJava";

    @Autowired
    DistributedLock distributedLock;

   // @Scheduled(cron = "0/10 * * * * ?")
    public void doSomething() {
        boolean lock = distributedLock.getLock(LOCK_ID, 15 * 1000);
        if (lock) {
            System.out.println("执行任务");
            distributedLock.releaseLock(LOCK_ID);
        } else {
            System.out.println("没有抢到锁");
        }
    }

    //3.添加定时任务

    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        DateTimeFormatter dtf2 =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.err.println("执行静态定时任务时间: " + dtf2.format(LocalDateTime.now()));
    }


    /**
     * 每小时执行一次 把中签的超时未支付的订单修改状态，车位号置为可选状态
     */
    @Scheduled(cron = "0 0 */1 * * ?")//0 0/5 * * * ?
    private void flushDrug() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.err.println("定时任务时间: " + formatter.format(LocalDateTime.now()));

        try {


        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
