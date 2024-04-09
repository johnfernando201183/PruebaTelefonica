package com.example.PruebaTelefonica.core.util;

public class Constants {

    private Constants() {
    }

    public static final String URI_BASE = "http://localhost:8130/api";
    public static final String URI_GET_PRODUCT = URI_BASE + "/products";
    public static final String PAGE = "page";
    public static final String SIZE = "size";


    public static String replaceAll(String text) {
        return text.replace(" ", "")
                .replace(":", "")
                .replace("\\", "");
    }

}
