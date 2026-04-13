package org.example.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication(scanBasePackages = {"org.example.factory"})
@ComponentScan({"org.example.factory.client" , "org.example.factory.controller" , "org.example.factory.service" , "org.example.factory.repository"})
@EnableJpaRepositories("org.example.factory.repository")
@EnableFeignClients("org.example.factory.client")
public class SBExampleApplicationRide {
    public static void main(String[] args) {
        SpringApplication.run(SBExampleApplicationRide.class, args);
    }
}
