package com.example.kafka_nbd.services;

import com.example.kafka_nbd.documents.Address;
import com.example.kafka_nbd.documents.Client;
import com.example.kafka_nbd.exceptions.clientAlreadyExist;
import com.example.kafka_nbd.exceptions.clientNotExistById;
import com.example.kafka_nbd.exceptions.clientNotExistbyEmail;
import com.example.kafka_nbd.repositories.ClientRepository;



public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findClientByIdIfExist (String id) {
        return clientRepository.findById(id).orElseThrow(() -> new clientNotExistById(id));
    }

    public Address findClientAddress (String id) {
        return findClientByIdIfExist(id).getAddress();
    }

    public boolean checkIsClientExistByAddress (Address address) {
        return clientRepository.existsByAddress(address);
    }

    public void deleteClient(String email) {
            clientRepository.delete(findClientByEmailIfExist(email));
    }

    public Client findClientByEmailIfExist (String email) {
        return clientRepository.findClientByEmail(email).orElseThrow(() -> new clientNotExistbyEmail(email));
    }

    public boolean clientExists(String email) {
        return clientRepository.existsByEmail(email);
    }


    public void surnameUpdate (String newSurname, Client client) {
        updateClient(client.getId(), client.getCName(), newSurname, client.getEmail(), client.getAddress(), client.getEmail());
    }

    public void emailUpdate (String newEmail, Client client) {
        updateClient(client.getId(), client.getCName(), client.getCSurname(), newEmail, client.getAddress(), client.getEmail());
    }

    public void deleteAll() {
        if (!clientRepository.findAll().isEmpty())
            clientRepository.deleteAll();
    }

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
