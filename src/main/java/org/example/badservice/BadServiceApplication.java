package org.example.badservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BadServiceApplication.class, args);
    }
}