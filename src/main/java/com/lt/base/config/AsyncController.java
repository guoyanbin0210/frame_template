package com.lt.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {

    private final AsyncService asyncService;

    @Autowired
    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/page")
    public String asyncPage() {
      //  System.out.println("当前请求线程名称为：【" + Thread.currentThread().getName() + "】");
        // 异步调用
        asyncService.generateReport();
        // 返回结果
        return "async";
    }

}