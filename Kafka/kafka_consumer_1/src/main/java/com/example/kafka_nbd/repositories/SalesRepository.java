package com.example.kafka_nbd.repositories;

import com.example.kafka_nbd.documents.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalesRepository extends MongoRepository<Sale,String> {

    Optional<Sale> findByProductNameAndClientEmail (String pName, String email);


}
