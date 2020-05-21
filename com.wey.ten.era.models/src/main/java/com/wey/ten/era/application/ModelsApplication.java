package com.wey.ten.era.application;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableFeignClients
@SpringBootApplication
public class ModelsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModelsApplication.class, args);
        log.info("===========ModelsApplication服务已经启动===========");
    }

}
