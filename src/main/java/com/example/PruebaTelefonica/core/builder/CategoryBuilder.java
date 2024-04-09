package com.example.PruebaTelefonica.core.builder;


import com.example.PruebaTelefonica.adapters.controllers.request.CategoryRequest;
import com.example.PruebaTelefonica.adapters.controllers.response.CategoryResponse;
import com.example.PruebaTelefonica.core.domain.CategoryEntity;

public class CategoryBuilder {
    private CategoryBuilder() {
    }

    public static CategoryResponse entityToResponse(CategoryEntity categoryEntity) {
        return CategoryResponse.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();
    }

    public static CategoryEntity categoryEntity(CategoryRequest categoryRequest) {
        return CategoryEntity.builder()
                .id(categoryRequest.getId())
                .name(categoryRequest.getName())
                .build();
    }
}
