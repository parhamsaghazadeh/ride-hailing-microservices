package org.example.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Slf4j
@SpringBootApplication
@Configuration
public class SBExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SBExampleApplication.class, args);
    }
}
