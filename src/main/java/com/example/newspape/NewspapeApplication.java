package com.example.newspape;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRabbit //开启基于注解的RabbitMQ模式
@SpringBootApplication
@EnableScheduling
public class NewspapeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewspapeApplication.class, args);
    }

}
