package com.plantStore.repositories;

import com.plantStore.documents.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SalesRepository extends MongoRepository<Sale,String> {

    Optional<Sale> findByProductNameAndClientEmail (String pName, String email);


}
