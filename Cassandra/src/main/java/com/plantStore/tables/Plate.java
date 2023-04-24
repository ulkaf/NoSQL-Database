package com.plantStore.tables;

import lombok.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.UUID;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Plate{
    @Setter
    private Integer height;

    @Setter
    private String period;

}
