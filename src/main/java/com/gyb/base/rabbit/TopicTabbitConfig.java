package com.gyb.base.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicTabbitConfig {

    @Bean
    public Queue sendQuees() {

        return new Queue("fanout.A");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }
}
