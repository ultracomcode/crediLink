package com.main.CrediLink.sippulse.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AccountCodesDTO {
    private List<String> accountCodes;

    public AccountCodesDTO(List<String> accountCodes) {
        this.accountCodes = accountCodes;
    }

}
