package com.lt.base.controller;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class SendMessageTest {

        private static final int THREAD_NUM = 200;

        @Autowired
        private AmqpTemplate amqpTemplate;

        @Test
        public void sendMqTest(){
            CountDownLatch  countDownLatch = new CountDownLatch(THREAD_NUM);
            for (int i = 0 ;i< THREAD_NUM;i++){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            countDownLatch.countDown();
                            countDownLatch.await();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        String message = "1";
                        amqpTemplate.convertAndSend("topicExchange","topic.ticket",message);

                    }
                }).start();


            }



        }



}
