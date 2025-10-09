package com.main.CrediLink.shared.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FormatterUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatCpfCnpj(String cpfCnpj) {
        String digits = cpfCnpj.replaceAll("\\D", "");
        if (digits.length() == 11) {
            return digits.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
                    "$1.$2.$3-$4");
        } else if (digits.length() == 14) {
            return digits.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})",
                    "$1.$2.$3/$4-$5");
        }
        return cpfCnpj;
    }

    public static String formatPhone(String phone) {
        String digits = phone.replaceAll("\\D", "");
        if (digits.length() == 11) {
            return digits.replaceFirst("(\\d{2})(\\d{5})(\\d{4})",
                    "($1)$2-$3");
        } else if (digits.length() == 10) {
            return digits.replaceFirst("(\\d{2})(\\d{4})(\\d{4})",
                    "($1)$2-$3");
        }
        return phone;
    }
    public static String formatInstantToDate(Instant instant) {
        if (instant == null) {
            return null;
        }
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        return zdt.format(FORMATTER);
    }
}
