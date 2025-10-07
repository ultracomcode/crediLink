package com.main.CrediLink.integration.sippulse.dto;

import java.util.List;

public record AccountCreditsDTO (
        List<AccountCreditDTO> accounts
){
    public record AccountCreditDTO( String accountCode, String credito){};
}
