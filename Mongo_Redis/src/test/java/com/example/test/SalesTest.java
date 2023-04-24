package com.example.test;


import com.example.test.exceptions.NotEnoughtProductException;
import com.example.test.repositories.ClientRepository;
import com.example.test.repositories.ProductRepository;
import com.example.test.repositories.SalesRepository;
import com.example.test.services.ClientService;
import com.example.test.services.ProductService;
import com.example.test.services.SaleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SalesTest {
    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    private SaleService saleService;

    @Autowired
    private ClientService clientService;
    private ProductService productService;

    String email = "145@poczta";
    String name= "Marchewka90";

    String cName = "Tymek";

    String surname = "Bolek";

    String city = "Zgierz";

    String street = "Aleksandrowska";

    Integer streetNum = 102;

    Double sPrice = 7.8;

    Integer count = 500;

    Integer weight = 70;

    @Test
    public void FullTest(){
        saleService = new SaleService(salesRepository, productRepository, clientRepository);
        productService = new ProductService(productRepository);
        saleService.deleteAll();
        if (!clientService.clientExists(email)) {
            clientService.addClientAndAddress(cName, surname, email, city, street, streetNum);
        }
        if (!productService.productExists(name)) {
            productService.addSeed(sPrice, count, name, weight);
        }
        else {
            productService.addProductCount(count - productService.findProductByNameIfExist(name).getPCount(), productService.findProductByNameIfExist(name));
        }
        saleService.addSale(200 , clientService.findClientByEmailIfExist(email), productService.findProductByNameIfExist(name), productService);

        assertThrows(NotEnoughtProductException.class, () -> {
            saleService.addSale(1000, clientService.findClientByEmailIfExist(email), productService.findProductByNameIfExist(name), productService);
        });

        assertTrue(productService.findProductByNameIfExist(name).getPCount() == 300);

        productService.addProductCount(500, productService.findProductByNameIfExist(name));

        assertTrue(productService.findProductByNameIfExist(name).getPCount() == 800);

        saleService.deleteSale(saleService.findSaleByClientAndAddressIfExist(clientService.findClientByEmailIfExist(email), productService.findProductByNameIfExist(name)).getId());
    }
}
