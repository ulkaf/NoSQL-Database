package com.plantStore.repositories;

import com.plantStore.tables.Address;
import com.plantStore.tables.Client;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends CassandraRepository<Client, UUID> {

    @AllowFiltering
    Optional<Client>findClientByEmail(String email);

    @AllowFiltering
    boolean existsByEmail(String email);

//    boolean existsByAddress(Address address);

}
