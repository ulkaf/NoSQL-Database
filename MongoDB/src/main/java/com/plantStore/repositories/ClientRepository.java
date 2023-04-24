package com.plantStore.repositories;

import com.plantStore.documents.Address;
import com.plantStore.documents.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface ClientRepository extends MongoRepository<Client, String> {

    Optional<Client>findClientByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByAddress(Address address);

}
