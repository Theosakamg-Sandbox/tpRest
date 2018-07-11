package com.tactfactory.iot.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AMQP (RabbitMQ) configuration of :<ul>
 * <li>Topic (Exchange)</li>
 * <li>Queue</li>
 * </ul>
 */
@Configuration
@EnableRabbit
public class AmqpConfig {

    /** Default Topic path. */
    public static final String TOPIC_SENSOR = "/sensor";

    /** Default Queue path. */
    public static final String QUEUE_SENSOR = "/sensorQueue";

    /** Default Routing-key to use. */
    public static final String ROUTING_KEY  = "sensor.*";

    /** @return Bean instance of Topic. */
    @Bean
    public Exchange makeExchange() {
        return new TopicExchange(TOPIC_SENSOR);
    }

    /** @return Bean instance of Queue. */
    @Bean
    public Queue makeQueue() {
        return new Queue(QUEUE_SENSOR, true);
    }

    /**
     * Bind Queue to Topic for specific Routing-key.
     * @param queue Queue to Bind
     * @param exchange Topic to Bind
     * @return Instance of Binding.
     */
    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY)
                .noargs();
    }
}
