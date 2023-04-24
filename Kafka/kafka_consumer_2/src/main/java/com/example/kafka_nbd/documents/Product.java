package com.example.kafka_nbd.documents;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "products")
@JsonTypeInfo(use = NAME, include = PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Seed.class, name = "seed"),
        @JsonSubTypes.Type(value = Plate.class, name= "plate")
})
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

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", pBasePrice=" + pBasePrice +
                ", pCount=" + pCount +
                ", name='" + name + '\'' +
                '}';
    }
}
