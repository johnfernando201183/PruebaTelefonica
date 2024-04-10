package com.example.PruebaTelefonica.adapters.controllers.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    public String name;
    public Double price;
    public CategoryRequest category;

}
