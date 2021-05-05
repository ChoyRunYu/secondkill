package com.secondkill.message.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbitMQ中间件配置类
 * @author choy
 * @date 2021/03/29
 */
@Configuration
public class DelayQueueConfig {

    /**
     * ttl过期队列
     */
    public final static String DELAY_QUEUE_PER_QUEUE_TTL_NAME = "delay_queue_per_queue_ttl";

    /**
     * 过期后处理消息的队列
     */
    public final static String DELAY_PROCESS_QUEUE_NAME = "delay_process_queue";

    /**
     * 交换机
     */
    public final static String DELAY_EXCHANGE_NAME = "delay_exchange";

    /**
     * 过期时间,1分钟
     */
    public final static int QUEUE_EXPIRATION = 60000;

    /**
     * 死信队列
     * @return
     */
    @Bean
    Queue delayQueuePreQueueTTL(){
//        通过x-dead-letter-exchange设置队列的死信路由，那么出现dead letter之后将dead letter重新发送到指定exchange；
//        通过x-dead-letter-routing-key设置路由键：出现dead letter之后将dead letter重新按照指定的routing-key发送；
//        通过x-message-ttl设置队列的过期时间；
        return QueueBuilder.durable(DELAY_QUEUE_PER_QUEUE_TTL_NAME)
                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME)
                .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME)
                .withArgument("x-message-ttl", QUEUE_EXPIRATION)
                .build();
    }

    /**
     * 死信接受队列
     * @return
     */
    @Bean
    Queue delayProcessQueue(){
        return QueueBuilder.durable(DELAY_PROCESS_QUEUE_NAME).build();
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    DirectExchange delayExchange(){
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    /**
     * 给死信队列绑定交换机
     * @param delayProcessQueue
     * @param delayExchange
     * @return
     */
    @Bean
    Binding dlxBinding(Queue delayProcessQueue, DirectExchange delayExchange){
        return BindingBuilder.bind(delayProcessQueue)
                .to(delayExchange)
                .with(DELAY_PROCESS_QUEUE_NAME);
    }
}
