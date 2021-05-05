package com.secondkill.system.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.secondkill.api.*")
@MapperScan("com.secondkill.system.user.mapper")
@ComponentScan(basePackages = {"com.secondkill"})
public class SecondkillUserApplication {


    public static void main(String[] args) {
        SpringApplication.run(SecondkillUserApplication.class, args);
    }

}


