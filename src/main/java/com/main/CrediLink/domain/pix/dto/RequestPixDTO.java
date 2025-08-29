package com.main.CrediLink.domain.pix.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestPixDTO (

        @NotBlank
        String accountCode,

        @NotBlank
        String value,

        String obs
){
}
