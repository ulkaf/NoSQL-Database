package com.example.kafka_nbd.services;


import com.example.kafka_nbd.documents.Address;
import com.example.kafka_nbd.documents.Client;
import com.example.kafka_nbd.documents.Product;
import com.example.kafka_nbd.documents.Sale;
import com.example.kafka_nbd.exceptions.NotEnoughtProductException;
import com.example.kafka_nbd.exceptions.saleNotExistById;
import com.example.kafka_nbd.exceptions.saleNotExistbyEmailAndPName;
import com.example.kafka_nbd.kafka.JsonKafkaConsumer;
import com.example.kafka_nbd.repositories.ClientRepository;
import com.example.kafka_nbd.repositories.ProductRepository;
import com.example.kafka_nbd.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class SaleService {

    private final SalesRepository salesRepository;

    private final ProductRepository productRepository;

    private final ClientRepository clientRepository;

    private final ProductService productService;

    public SaleService(SalesRepository salesRepository, ProductRepository productRepository, ClientRepository clientRepository) {
        this.salesRepository = salesRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        productService = new ProductService(productRepository);
    }

    public Sale findSaleByIdIfExist (String id) {
        return salesRepository.findById(id).orElseThrow(() -> new saleNotExistById(id));
    }

    public Client findSaleClient (String id) {
        return findSaleByIdIfExist(id).getClient();
    }

    public Product findSaleProduct (String id) {
        return findSaleByIdIfExist(id).getProduct();
    }

    public void deleteSale(String id) {
        salesRepository.delete(findSaleByIdIfExist(id));
    }

    public void deleteAll() {
        if (!salesRepository.findAll().isEmpty())
            salesRepository.deleteAll();
    }

    public Sale findSaleByClientAndAddressIfExist (Client client, Product product) {
        return salesRepository.findByProductNameAndClientEmail(product.getName(), client.getEmail()).orElseThrow(() -> new saleNotExistbyEmailAndPName(client, product));
    }

    public Sale addSale (Integer sProductCount, Client client, Product product, ProductService productService) {
        if (sProductCount > product.getPCount()) {
            throw new NotEnoughtProductException(product.getName());
        }
        productService.updateProductCount(sProductCount, product);
        return salesRepository.save(Sale.builder()
                .sProductCount(sProductCount)
                .finalCost(sProductCount * product.getPBasePrice())
                .client(client)
                .product(product)
                .sTime(LocalDateTime.now())
                .build());
    }

    public Sale saveSale(Sale sale) {
        return salesRepository.save(sale);
    }

    public Sale addSaleAndClient (Integer sProductCount, String name, String surname, String email, String city, String street, Integer street_number, Product product) {
        if (sProductCount > product.getPCount()) {
            throw new NotEnoughtProductException(product.getName());
        }
        return salesRepository.save(Sale.builder()
                .sProductCount(sProductCount)
                .finalCost(sProductCount * product.getPBasePrice())
                .client(clientRepository.save(Client.builder()
                        .address(Address.builder()
                                        .street_number(street_number)
                                        .street(street)
                                        .city(city)
                                        .build())
                        .cSurname(surname)
                        .email(email)
                        .cName(name)
                        .build()))
                .product(product)
                .sTime(LocalDateTime.now())
                .build());
    }
}
