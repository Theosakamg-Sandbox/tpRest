package com.tactfactory.iot.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class AmqpConfig {

    public static final String TOPIC_SENSOR = "/sensor";

    public static final String QUEUE_SENSOR = "/sensorQueue";

    @Bean
    public Exchange makeExchange() {
        return new TopicExchange(TOPIC_SENSOR);
    }

    @Bean
    public Queue makeQueue() {
        return new Queue(QUEUE_SENSOR, true);
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("sensor.*")
                .noargs();
    }
}
