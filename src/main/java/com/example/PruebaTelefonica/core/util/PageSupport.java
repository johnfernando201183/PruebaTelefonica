package com.example.PruebaTelefonica.core.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class PageSupport<T> {

    public static final String FIRST_PAGE_NUM = "0";
    public static final String DEFAULT_PAGE_SIZE = "5";

    List<T> content;
    int pageNumber;
    int pageSize;
    Long totalElements;

    @JsonProperty
    public Long totalPages() {
        return pageSize > 0 ? (totalElements - 1) / pageSize + 1 : 0;
    }

    @JsonProperty
    public String nextPage() {
        return (pageNumber + 1) * pageSize >= totalElements ? null : Constants.URI_GET_PRODUCT
                .concat("?")
                .concat(Constants.PAGE)
                .concat("=") + (pageNumber + 1) +
                "&".concat(Constants.SIZE).concat("=") + pageSize;
    }

    @JsonProperty
    public String previewPage() {
        return pageNumber == Integer.parseInt(FIRST_PAGE_NUM) ? null : Constants.URI_GET_PRODUCT
                .concat("?")
                .concat(Constants.PAGE)
                .concat("=") + (pageNumber - 1) +
                "&".concat(Constants.SIZE).concat("=") + pageSize;
    }

}
