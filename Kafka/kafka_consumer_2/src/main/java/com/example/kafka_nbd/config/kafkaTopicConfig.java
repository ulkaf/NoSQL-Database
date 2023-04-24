package com.example.kafka_nbd.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaTopicConfig {
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${spring.kafka.topic-json.name}")
    private String topicJsonName;

    @Bean
    public NewTopic nbdTopic(){
        return TopicBuilder.name(topicName)
                .partitions(3)
                .build();
    }

    @Bean
    public NewTopic nbdJsonTopic(){
        return TopicBuilder.name(topicJsonName)
                .partitions(3)
                .build();
    }
}
