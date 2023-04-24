package com.plantStore.tables;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(value = "client")
public class Client {

    @PrimaryKey
    private UUID id;

    @Setter
    @Column(value = "cName")
    private String cName;

    @Setter
    @Column(value = "cSurname")
    private String cSurname;

    @Setter
    @Column(value = "email")
    private String email;

    @Frozen
    @Column(value = "address")
    private Address address;

}
