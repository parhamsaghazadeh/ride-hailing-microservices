package org.example.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Slf4j
@SpringBootApplication(scanBasePackages = "org.example.factory")
@Configuration
public class SBExampleApplicationUser {
    public static void main(String[] args) {
        SpringApplication.run(SBExampleApplicationUser.class, args);
    }
}
