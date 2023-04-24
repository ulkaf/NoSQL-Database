package com.example.test.repositories;


import com.example.test.documents.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findProductByName(String name);

    Boolean existsByName(String name);
}
