package com.lt;

import com.lt.body.user.JwtAuthenticationFilter;
import com.lt.body.user.service.UserMainService;
import com.lt.body.websocket.NettyServer;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan("com.lt.*")
public class SpringbootBaseApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootBaseApplication.class, args);
        try {
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Resource
    public UserMainService userMainService;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(userMainService);
        registrationBean.setFilter(filter);
        Map<String, String> m = new HashMap<>();
        m.put("targetBeanName", "jwtFilter");
        m.put("targetFilterLifecycle", "true");
        registrationBean.setInitParameters(m);
        return registrationBean;
    }

    @Override
    //为了打包springboot项目
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

}

