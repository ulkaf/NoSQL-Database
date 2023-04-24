package com.example.kafka_nbd;

import com.example.kafka_nbd.documents.Address;
import com.example.kafka_nbd.documents.Client;
import com.example.kafka_nbd.documents.Sale;
import com.example.kafka_nbd.documents.Seed;
import com.example.kafka_nbd.kafka.JsonKafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class KafkaNbdApplicationTests {

    @Autowired
    private JsonKafkaProducer jsonKafkaProducer;

    @Test
    void contextLoads() {
    }

    String email = "145@poczta";

    String name= "Marchewka90";

    String cName = "Tymek";

    String surname = "Bolek";

    String city = "Zgierz";

    String street = "Aleksandrowska";

    Integer streetNum = 102;

    Double sPrice = 7.8;

    Integer count = 500;

    Integer weight = 70;

    @Test
    void sendTest(){
        Sale sale = Sale.builder()
                .id("1")
                .client(Client.builder()
                        .id("1")
                        .cName(cName)
                        .cSurname(surname)
                        .address(Address.builder()
                                .city(city)
                                .street(street)
                                .street_number(streetNum)
                                .build())
                        .email(email)
                        .build())
                .finalCost(sPrice * 200)
                .sTime(LocalDateTime.now())
                .sProductCount(200)
                .product(Seed.builder()
                        .id("1")
                        .pBasePrice(sPrice)
                        .pName(name)
                        .weight(weight)
                        .pCount(count-200)
                        .build())
                .build();
        jsonKafkaProducer.sendMessage(sale);
    }
}
