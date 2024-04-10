package com.example.PruebaTelefonica.core.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Enums {

    PRODUCT_UPDATED_SUCCESSFUL("200", "Product modified successfully id: "),
    PRODUCT_NOT_FOUND("404", "Product is not found id: "),
    PRODUCT_DELETED("200", "Product removed successfully");

    private final String code;
    private final String description;
}
