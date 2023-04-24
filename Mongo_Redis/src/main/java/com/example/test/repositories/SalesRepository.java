package com.example.test.repositories;


import com.example.test.documents.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SalesRepository extends MongoRepository<Sale,String> {

    Optional<Sale> findByProductNameAndClientEmail (String pName, String email);


}
