package com.plantStore.tables;

import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.UUID;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Seed {

    @Setter
    private Integer weight;

}
