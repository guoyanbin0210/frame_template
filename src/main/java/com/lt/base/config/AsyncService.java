package com.lt.base.config;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    @Async
    public void generateReport() {
        // 模拟异步生成报表代码，这里设置为打印
        System.out.println("报表线程名称：【" + Thread.currentThread().getName() + "】");
    }
}
