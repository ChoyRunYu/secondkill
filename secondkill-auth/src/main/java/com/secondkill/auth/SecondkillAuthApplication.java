package com.secondkill.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.secondkill.api.*")
@ComponentScan(basePackages = {"com.secondkill"})
public class SecondkillAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondkillAuthApplication.class, args);
    }


}
