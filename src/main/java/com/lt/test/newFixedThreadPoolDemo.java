package com.lt.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class newFixedThreadPoolDemo {
    public static void main(String[] args) {
        //快捷键 ctrl+2 +F 来创建变量名
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 10; i++) {
            int temp = i;
            newFixedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    //Thread.currentThread().setName(Integer.toString(temp)+"__name");
                    System.out.println("ThreadName:"+Thread.currentThread().getName()+" i:"+temp);
                }
            });
        }
    }
}
