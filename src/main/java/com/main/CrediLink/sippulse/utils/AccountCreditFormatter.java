package com.main.CrediLink.sippulse.utils;

import com.main.CrediLink.sippulse.dto.AccountCreditDTO;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AccountCreditFormatter {

    private static final DecimalFormatSymbols symbols;
    private static final DecimalFormat decimalFormat;

    static {
        symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        decimalFormat = new DecimalFormat("#,##0.00", symbols);
    }

    /**
     * Recebe uma lista de strings no formato "accountCode,credito"
     * e retorna lista de AccountCreditDTO com credito formatado.
     */
    public static List<AccountCreditDTO> formatAccountCredits(List<String> rawAccounts) {
        return rawAccounts.stream()
                .map(entry -> {
                    String[] parts = entry.split(",");
                    String accountCode = parts[0];
                    BigDecimal credito = new BigDecimal(parts[1]);
                    String creditoFormatado = decimalFormat.format(credito);
                    return new AccountCreditDTO(accountCode, creditoFormatado);
                })
                .collect(Collectors.toList());
    }
}
