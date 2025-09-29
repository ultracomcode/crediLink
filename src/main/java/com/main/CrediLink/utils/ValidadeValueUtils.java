package com.main.CrediLink.utils;

import java.math.BigDecimal;
import java.util.Locale;

public class ValidadeValueUtils {

    public static String validateAndFormatAmount(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("Valor não pode ser nulo ou vazio");
        }

        BigDecimal parsed;

        try {
            parsed = new BigDecimal(valor.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor inválido: não é um número");
        }

        if (parsed.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }

        return String.format(Locale.US, "%.2f", parsed);
    }
}
