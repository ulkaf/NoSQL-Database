package com.example.kafka_nbd.documents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
@TypeAlias("seed")
@JsonTypeName("seed")
public class Seed extends Product {


    private Integer weight;

    @Builder
    public Seed( String id, Double pBasePrice, Integer pCount, String pName, Integer weight) {
        super(id, pBasePrice, pCount, pName);
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Seed{" +
                "id='" + super.getId() + '\'' +
                ", pBasePrice=" + super.getPBasePrice() +
                ", pCount=" + super.getPCount() +
                ", name='" + super.getName() + '\'' +
                ", weight=" + weight +
                '}';
    }
}
