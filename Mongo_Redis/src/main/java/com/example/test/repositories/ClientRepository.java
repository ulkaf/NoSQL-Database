package com.example.test.repositories;


import com.example.test.documents.Address;
import com.example.test.documents.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    Optional<Client>findClientByEmail(String email);

    boolean existsByEmail(String email);


}
