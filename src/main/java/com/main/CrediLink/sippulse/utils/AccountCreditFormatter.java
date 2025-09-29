package com.main.CrediLink.sippulse.utils;

import com.main.CrediLink.sippulse.dto.AccountCreditsDTO;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

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
    public static AccountCreditsDTO formatAccountCredits(List<String> rawAccounts) {
        List<AccountCreditsDTO.AccountCreditDTO> accounts = rawAccounts.stream()
                .map(entry -> {
                    String[] parts = entry.split(",");
                    String accountCode = parts[0].trim();
                    BigDecimal credito = new BigDecimal(parts[1].trim());
                    String creditoFormatado = decimalFormat.format(credito);
                    return new AccountCreditsDTO.AccountCreditDTO(accountCode, creditoFormatado);
                })
                .toList();

        return new AccountCreditsDTO(accounts);
    }
}
