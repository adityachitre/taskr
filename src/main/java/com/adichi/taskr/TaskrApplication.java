package com.adichi.taskr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaskrApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskrApplication.class, args);
    }
}
