package com.example.kafka_nbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories()
public class KafkaNbdApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaNbdApplication.class, args);
    }

}
