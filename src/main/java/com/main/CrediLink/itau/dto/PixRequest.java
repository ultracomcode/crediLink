package com.main.CrediLink.itau.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PixRequest {
    private Valor valor;
    private String chave;

    @Getter
    @Setter
    public static class Valor {
        private String original;
    }
}

