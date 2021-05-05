package com.secondkill.system.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.secondkill.api.*")
@ComponentScan(basePackages = {"com.secondkill"})
@MapperScan("com.secondkill.system.order.mapper")
public class SecondkillOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondkillOrderApplication.class, args);
    }

}
