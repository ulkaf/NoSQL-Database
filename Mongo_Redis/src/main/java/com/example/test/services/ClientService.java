package com.example.test.services;

import com.example.test.documents.Address;
import com.example.test.documents.Client;
import com.example.test.exceptions.clientAlreadyExist;
import com.example.test.exceptions.clientNotExistbyEmail;
import com.example.test.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @CacheEvict(value= "clients", key="#email")
    public void deleteClient(String email) {
            clientRepository.delete(findClientByEmailIfExist(email));
    }

    @Cacheable(value= "clients", key="#email")
    public Client findClientByEmailIfExist (String email) {
        return clientRepository.findClientByEmail(email).orElseThrow(() -> new clientNotExistbyEmail(email));
    }

    public boolean clientExists(String email) {
        return clientRepository.existsByEmail(email);
    }


    @CacheEvict(value= "clients", key="#client.email")
    public void emailUpdate (String newEmail, Client client) {
        updateClient(client.getId(), client.getCName(), client.getCSurname(), newEmail, client.getAddress(), client.getEmail());
    }

    @CacheEvict(value = "clients", allEntries = true)
    public void deleteAll() {
        if (!clientRepository.findAll().isEmpty())
            clientRepository.deleteAll();
    }

    @CacheEvict(value = "clients", allEntries = true)
    public void clearCache() {
        log.info("Cache cleared!");
    }

    @CachePut(value="clients", key="#email")
    public Client addClientAndAddress (String name, String surname, String email, String city, String street, Integer street_number) {

        if (clientRepository.existsByEmail(email))
            throw new clientAlreadyExist(email);

        return clientRepository.save(Client.builder()
                .cName(name)
                .cSurname(surname)
                .email(email)
                .address(Address.builder()
                        .city(city)
                        .street(street)
                        .street_number(street_number)
                        .build())
                .build());
    }

    @CachePut(value= "clients", key="#email")
    public Client updateClient(String id, String name, String surname, String email, Address address, String oldEmail) {

        if (!clientRepository.existsByEmail(oldEmail)) {
            throw new clientNotExistbyEmail(oldEmail);
        } else if (clientRepository.existsByEmail(email)) {
            throw new clientAlreadyExist(email);
        } else {
            return clientRepository.save(Client.builder()
                    .id(id)
                    .cName(name)
                    .cSurname(surname)
                    .email(email)
                    .address(address)
                    .build());
        }
    }
}
