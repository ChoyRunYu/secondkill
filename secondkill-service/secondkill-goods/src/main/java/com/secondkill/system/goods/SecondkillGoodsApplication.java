package com.secondkill.system.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.secondkill.api.*")
@MapperScan("com.secondkill.system.goods.mapper")
@ComponentScan(basePackages={"com.secondkill"})
public class SecondkillGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondkillGoodsApplication.class, args);
    }

}
