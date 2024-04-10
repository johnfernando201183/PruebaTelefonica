package com.example.PruebaTelefonica.core.builder;


import com.example.PruebaTelefonica.adapters.controllers.request.ProductRequest;
import com.example.PruebaTelefonica.adapters.controllers.response.ProductResponse;
import com.example.PruebaTelefonica.core.domain.ProductEntity;

import java.util.Date;

public class ProductBuilder {
    private ProductBuilder() {
    }

    public static ProductResponse entityToResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .createDate(productEntity.getCreateDate())
                .category(CategoryBuilder.entityToResponse(productEntity.getCategory()))
                .modifyDate(productEntity.getModifyDate())
                .image(productEntity.getImage())
                .build();
    }

    public static ProductEntity updateProduct(ProductEntity productEntity, ProductRequest productRequest) {
        productEntity.setName(productRequest.getName());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setModifyDate(new Date());
        productEntity.setCategory(CategoryBuilder.categoryEntity(productRequest.getCategory()));
        return productEntity;

    }

    public static ProductEntity createProduct(ProductRequest productRequest) {
        return ProductEntity.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .createDate(new Date())
                .category(CategoryBuilder.categoryEntity(productRequest.getCategory()))
                .build();
    }
}
