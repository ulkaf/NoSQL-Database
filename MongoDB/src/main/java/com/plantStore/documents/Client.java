package com.plantStore.documents;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clients")
public class Client {

    @Id
    private String id;

    @NotNull
    private String cName;

    @Setter
    @NotNull
    private String cSurname;

    @Setter
    @NotNull
    @Indexed(name = "email_text", unique = true)
    private String email;

    @Setter
    @NonNull
    private Address address;

}
