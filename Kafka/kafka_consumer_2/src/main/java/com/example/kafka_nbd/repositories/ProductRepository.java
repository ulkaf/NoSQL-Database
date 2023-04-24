package com.example.kafka_nbd.repositories;


import com.example.kafka_nbd.documents.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findProductByName(String name);

    Boolean existsByName(String name);
}
