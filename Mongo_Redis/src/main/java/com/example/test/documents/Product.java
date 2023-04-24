package com.example.test.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public abstract class Product {

    @Id
    private String id;

    private Double pBasePrice;

    private Integer pCount;

    @Indexed(name = "product_name", unique = true)
    private String name;


}
