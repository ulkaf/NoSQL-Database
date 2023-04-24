package com.example.kafka_nbd.repositories;


import com.example.kafka_nbd.documents.Address;
import com.example.kafka_nbd.documents.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ClientRepository extends MongoRepository<Client, String> {

    Optional<Client>findClientByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByAddress(Address address);

}
