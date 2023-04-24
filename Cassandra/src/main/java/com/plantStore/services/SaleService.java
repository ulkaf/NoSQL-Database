package com.plantStore.services;

import com.plantStore.tables.Address;
import com.plantStore.tables.Client;
import com.plantStore.tables.Product;
import com.plantStore.tables.Sale;
import com.plantStore.exceptions.NotEnoughtProductException;
import com.plantStore.exceptions.saleNotExistById;
import com.plantStore.exceptions.saleNotExistbyEmailAndPName;
import com.plantStore.repositories.ClientRepository;
import com.plantStore.repositories.ProductRepository;
import com.plantStore.repositories.SalesRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class SaleService {

    private final SalesRepository salesRepository;

    private final ProductRepository productRepository;

    private final ClientRepository clientRepository;

    public SaleService(SalesRepository salesRepository, ProductRepository productRepository, ClientRepository clientRepository) {
        this.salesRepository = salesRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    public Sale findSaleByIdIfExist (UUID id) {
        return salesRepository.findById(id).orElseThrow(() -> new saleNotExistById(id));
    }

    public String findSaleClient (UUID id) {
        return findSaleByIdIfExist(id).getClientEmail();
    }

    public String findSaleProduct (UUID id) {
        return findSaleByIdIfExist(id).getProductName();
    }

    public void deleteSale(UUID id) {
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
                .id(UUID.randomUUID())
                .sProductCount(sProductCount)
                .finalCost(sProductCount * product.getPBasePrice())
                .clientEmail(client.getEmail())
                .productName(product.getName())
                .sTime(LocalDateTime.now())
                .build());
    }

    public Sale addSaleAndClient (Integer sProductCount, String name, String surname, String email, String city, String street, Integer street_number, Product product) {
        if (sProductCount > product.getPCount()) {
            throw new NotEnoughtProductException(product.getName());
        }
        return salesRepository.save(Sale.builder()
                .id(UUID.randomUUID())
                .sProductCount(sProductCount)
                .finalCost(sProductCount * product.getPBasePrice())
                .clientEmail(clientRepository.save(Client.builder()
                        .address(Address.builder()
                                        .street_number(street_number)
                                        .street(street)
                                        .city(city)
                                        .build())
                        .cSurname(surname)
                        .email(email)
                        .cName(name)
                        .build()).getEmail())
                .productName(product.getName())
                .sTime(LocalDateTime.now())
                .build());
    }
}
