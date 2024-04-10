package com.example.PruebaTelefonica.functions;

import com.example.PruebaTelefonica.adapters.controllers.request.CategoryRequest;
import com.example.PruebaTelefonica.adapters.controllers.request.ProductRequest;
import com.example.PruebaTelefonica.adapters.controllers.response.CategoryResponse;
import com.example.PruebaTelefonica.adapters.controllers.response.MessageResponse;
import com.example.PruebaTelefonica.adapters.controllers.response.ProductResponse;
import com.example.PruebaTelefonica.core.util.Enums;

import java.util.Date;

public class ModelResponse {

    public static ProductResponse productResponse() {
        return ProductResponse.builder()
                .id("5f2e3236dcbfb9008c3982cc")
                .modifyDate(new Date())
                .category(categoryResponse())
                .createDate(new Date())
                .name("Lapicero")
                .image(null)
                .price(20.0)
                .build();
    }

    public static CategoryResponse categoryResponse() {
        return CategoryResponse.builder()
                .id("5f2e3236dcbfb9008c3982c8")
                .name("Electrónico")
                .build();
    }

    public static ProductRequest productRequest() {
        return ProductRequest.builder()
                .name("Nuevo nombre")
                .price(12.5)
                .category(categoryRequest())
                .build();
    }

    public static CategoryRequest categoryRequest() {
        return CategoryRequest.builder()
                .id("5f2e3236dcbfb9008c3982c8")
                .name("Electrónico")
                .build();
    }

    public static MessageResponse updateMessageResponse() {
        return MessageResponse.builder()
                .code(Enums.PRODUCT_UPDATED_SUCCESSFUL.getCode())
                .message(Enums.PRODUCT_UPDATED_SUCCESSFUL.getDescription())
                .build();
    }

    public static MessageResponse deleteMessageResponse() {
        return MessageResponse.builder()
                .code(Enums.PRODUCT_DELETED.getCode())
                .message(Enums.PRODUCT_DELETED.getDescription())
                .build();
    }
}
