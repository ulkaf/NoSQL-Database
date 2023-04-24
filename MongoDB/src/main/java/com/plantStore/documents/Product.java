package com.plantStore.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
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

    @NotNull
    private Double pBasePrice;

    @NotNull
    private Integer pCount;

    @NotNull
    @Indexed(name = "product_name", unique = true)
    private String name;


}
