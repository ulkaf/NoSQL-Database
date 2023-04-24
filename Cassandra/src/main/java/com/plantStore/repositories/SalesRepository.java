package com.plantStore.repositories;

import com.plantStore.tables.Sale;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;


import java.util.Optional;
import java.util.UUID;

public interface SalesRepository extends CassandraRepository<Sale, UUID> {

    @AllowFiltering
    Optional<Sale> findByProductNameAndClientEmail (String pName, String email);


}
