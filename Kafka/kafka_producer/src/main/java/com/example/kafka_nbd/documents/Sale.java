package com.example.kafka_nbd.documents;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "sales")
public class Sale {

    @Id
    private String id;

    @NotNull
    private Integer sProductCount;

    @NotNull
    private Double finalCost;

    @NotNull
    private LocalDateTime sTime;

    @NotNull
    private Client client;

    @NotNull
    private Product product;

}
