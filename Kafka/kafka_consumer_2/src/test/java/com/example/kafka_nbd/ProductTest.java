package com.example.kafka_nbd;


import com.example.kafka_nbd.exceptions.NotEnoughtProductException;
import com.example.kafka_nbd.exceptions.productAlreadyExist;
import com.example.kafka_nbd.exceptions.productNotExistByName;
import com.example.kafka_nbd.repositories.ProductRepository;
import com.example.kafka_nbd.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    private ProductService productService;

   Double basePrice = 12.50;
   Integer count = 2;
   String pNameSeed = "Seed1";

   Integer weight = 5;


    @Test
    public void CreateSeedTest(){
        productService = new ProductService(productRepository);
        if (productService.productExists(pNameSeed)) {
            productService.deleteProduct(productService.findProductByNameIfExist(pNameSeed).getId());
        }
        productService.addSeed(basePrice, count, pNameSeed, weight);
        assertThrows(productAlreadyExist.class, () -> {
            productService.addSeed(basePrice, count, pNameSeed, weight);
        });
    }

    @Test
    public void DeleteTest(){
        productService = new ProductService(productRepository);
        if (!productService.productExists(pNameSeed)) {
            productService.addSeed(basePrice, count, pNameSeed, weight);
        }
        productService.deleteProduct(productService.findProductByNameIfExist(pNameSeed).getId());
        assertThrows(productNotExistByName.class, () -> {
            productService.deleteProduct(productService.findProductByNameIfExist(pNameSeed).getId());
        });
    }

    @Test
    public void UpdateTest() {
        productService = new ProductService(productRepository);
        if (!productService.productExists(pNameSeed)) {
            productService.addSeed(basePrice, count, pNameSeed, weight);
        } else {
            productService.addProductCount((count - productService.findProductByNameIfExist(pNameSeed).getPCount()),
                    productService.findProductByNameIfExist(pNameSeed));
        }
        productService.updateProductCount(2, productService.findProductByNameIfExist(pNameSeed));

        assertTrue(productService.findProductByNameIfExist(pNameSeed).getPCount() == count - 2);

        assertThrows(NotEnoughtProductException.class, () -> {
        productService.updateProductCount(10, productService.findProductByNameIfExist(pNameSeed));
        });

    }

    @Test
    public void ReadTest() {
        productService = new ProductService(productRepository);

        if (!productService.productExists(pNameSeed)) {
            productService.addSeed(basePrice, count, pNameSeed, weight);
        }
        assertTrue(productService.findProductByNameIfExist(pNameSeed) != null);

        productService.deleteProduct(productService.findProductByNameIfExist(pNameSeed).getId());

        assertThrows(productNotExistByName.class, () -> {
            productService.deleteProduct(productService.findProductByNameIfExist(pNameSeed).getId());
        });
    }

}

