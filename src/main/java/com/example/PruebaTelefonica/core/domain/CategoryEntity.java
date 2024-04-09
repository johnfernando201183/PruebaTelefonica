package com.example.PruebaTelefonica.core.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categories")
public class CategoryEntity {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String name;

}
