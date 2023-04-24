package com.example.test;


import com.example.test.exceptions.clientAlreadyExist;
import com.example.test.exceptions.clientNotExistbyEmail;
import com.example.test.services.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientTest {


    @Autowired
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

        if (!clientService.clientExists(email)) {
            clientService.addClientAndAddress(name, surname, email, city, street, street_num);
        }
        assertTrue(clientService.findClientByEmailIfExist(email) != null);

        clientService.deleteClient(email);

        assertThrows(clientNotExistbyEmail.class, () -> {
            clientService.findClientByEmailIfExist(email);
        });
    }

    @Test
    void clearCacheTest() {
        if (clientService.clientExists(email)) {
            clientService.deleteClient(email);
        }
        clientService.addClientAndAddress(name, surname, email, city, street, street_num);

        assertEquals(clientService.findClientByEmailIfExist(email).getCName(), name);

        clientService.clearCache();

        assertEquals(clientService.findClientByEmailIfExist(email).getCName(), name);

        clientService.deleteAll();

        assertThrows(clientNotExistbyEmail.class, () -> {
            clientService.findClientByEmailIfExist(email);
        });
    }
}
