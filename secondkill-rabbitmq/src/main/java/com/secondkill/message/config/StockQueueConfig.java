package com.secondkill.message.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 库存队列
 * @author choy
 * @date 2021/03/30
 */
@Configuration
public class StockQueueConfig {

    /**
     * 库存队列
     */
    public final static String STOCK_QUEUE_NAME = "stock_queue";


    /**
     * 创建库存队列
     * @return
     */
    @Bean
    Queue stockQueue(){
        return QueueBuilder.durable(STOCK_QUEUE_NAME)
                .build();
    }

}
