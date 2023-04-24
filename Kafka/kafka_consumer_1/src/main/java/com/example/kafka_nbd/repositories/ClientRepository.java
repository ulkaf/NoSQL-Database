package com.example.kafka_nbd.repositories;


import com.example.kafka_nbd.documents.Address;
import com.example.kafka_nbd.documents.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    Optional<Client>findClientByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByAddress(Address address);

}
