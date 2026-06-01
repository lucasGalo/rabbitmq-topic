package com.galo.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RabbitMqTopicApplication {

  public static void main(String[] args) {
    SpringApplication.run(RabbitMqTopicApplication.class, args);
  }

}
