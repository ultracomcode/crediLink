package com.main.CrediLink.sippulse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class AccountCreditsDTO {
    private List<AccountCreditDTO> accounts;

}
