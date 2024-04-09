package com.example.PruebaTelefonica.adapters.controllers.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {

    public String id;
    public String name;
}
