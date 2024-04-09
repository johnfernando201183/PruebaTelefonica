package com.example.PruebaTelefonica.adapters.controllers.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class ProductResponse {

    public String id;
    public String name;
    public Double price;
    public Date createDate;
    public Date modifyDate;
    public CategoryResponse category;
    public String image;

}
