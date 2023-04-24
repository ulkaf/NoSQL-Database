package com.plantStore.repositories;

import com.plantStore.tables.Product;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;


import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends CassandraRepository<Product, UUID> {
    @AllowFiltering
    Optional<Product> findProductByName(String name);

    @AllowFiltering
    Boolean existsByName(String name);
}
