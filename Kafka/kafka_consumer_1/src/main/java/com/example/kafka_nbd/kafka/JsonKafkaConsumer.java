package com.example.kafka_nbd.kafka;


import com.example.kafka_nbd.documents.Sale;
import com.example.kafka_nbd.repositories.ClientRepository;
import com.example.kafka_nbd.repositories.ProductRepository;
import com.example.kafka_nbd.repositories.SalesRepository;
import com.example.kafka_nbd.services.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Sale sale){
        LOGGER.info(String.format("Json message recieved -> %s", sale.toString()));
        SaleService saleService = new SaleService(salesRepository, productRepository, clientRepository);
        saleService.saveSale(sale);
    }
}
