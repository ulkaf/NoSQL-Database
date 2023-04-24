package com.plantStore.tables;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import org.springframework.data.cassandra.repository.AllowFiltering;

import java.util.UUID;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(value = "product")
public class Product {

    @PrimaryKey
    private UUID id;

    @Column(value = "pBasePrice")
    private Double pBasePrice;

    @Column(value = "pCount")
    private Integer pCount;

    @Column(value = "name")
    private String name;


}
