package com.example.PruebaTelefonica.adapters.controllers.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageResponse {

    public String code;
    public String message;
}
