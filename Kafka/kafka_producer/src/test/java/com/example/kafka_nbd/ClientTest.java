package com.example.kafka_nbd;


import com.example.kafka_nbd.exceptions.clientAlreadyExist;
import com.example.kafka_nbd.exceptions.clientNotExistbyEmail;
import com.example.kafka_nbd.kafka.JsonKafkaProducer;
import com.example.kafka_nbd.repositories.ClientRepository;
import com.example.kafka_nbd.services.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ClientTest {


    @Autowired
    private ClientRepository clientRepository;



    private ClientService clientService;


    String name = "Piotr";
    String surname = "Kowalski";
    String email = "piotrkowal@poczta.pl";
    String newEmail = "newEmail@poczta.pl";
    String city = "Lodz";
    String street = "Piotrkowska";
    Integer street_num = 11;


    @Test
    public void CreateTest(){
        clientService = new ClientService(clientRepository);

        if (clientService.clientExists(email)) {
            clientService.deleteClient(email);
        }
        clientService.addClientAndAddress(name, surname, email, city, street, street_num);
        assertThrows(clientAlreadyExist.class, () -> {
            clientService.addClientAndAddress(name, surname, email, city, "Konikowa", street_num);
        });
    }

    @Test
    public void DeleteTest(){
        clientService = new ClientService(clientRepository);

        if (!clientService.clientExists(email)) {
            clientService.addClientAndAddress(name, surname, email, city, street, street_num);
        }
        clientService.deleteClient(email);
        assertThrows(clientNotExistbyEmail.class, () -> {
            clientService.deleteClient(email);
        });
    }

    @Test
    public void UpdateTest() {
        clientService = new ClientService(clientRepository);

        if (!clientService.clientExists(email)) {
            clientService.addClientAndAddress(name, surname, email, city, street, street_num);
        }
        if (clientService.clientExists(newEmail)) {
            clientService.deleteClient(newEmail);
        }
        clientService.emailUpdate(newEmail, clientService.findClientByEmailIfExist(email));

        assertThrows(clientNotExistbyEmail.class, () -> {
            clientService.findClientByEmailIfExist(email);
        });
    }

    @Test
    public void ReadTest() {
        clientService = new ClientService(clientRepository);

        if (!clientService.clientExists(email)) {
            clientService.addClientAndAddress(name, surname, email, city, street, street_num);
        }
        assertTrue(clientService.findClientByEmailIfExist(email) != null);

        clientService.deleteClient(email);

        assertThrows(clientNotExistbyEmail.class, () -> {
            clientService.findClientByEmailIfExist(email);
        });
    }
}
