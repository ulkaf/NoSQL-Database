package com.plantStore.tables;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@UserDefinedType("address_type")
public class Address {

    @Column("street")
    private String street;

    @Column("street_number")
    private Integer street_number;

    @Column("city")
    private String city;

}
