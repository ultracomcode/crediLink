package com.main.CrediLink.sippulse.services;

import com.main.CrediLink.exceptions.AccountCodeNotFoundException;
import com.main.CrediLink.sippulse.dto.AccountCreditDTO;
import com.main.CrediLink.sippulse.dto.AccountCreditsDTO;
import com.main.CrediLink.sippulse.repository.SubscriberRepository;
import com.main.CrediLink.sippulse.utils.AccountCreditFormatter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;

    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public boolean checkAccountCode(String username, String domain){
        String accountCode = username + "@" + domain;

        return subscriberRepository.findByAccountCode(accountCode)
                .orElseThrow(() -> new AccountCodeNotFoundException(
                        String.format("AccountCode '%s' não encontrado", accountCode)
                )) != null;
    }

    public AccountCreditsDTO findAllAccountcode(String firstName) {
        List<String> accounts = subscriberRepository.findAllAccountcode(firstName);
        List<AccountCreditDTO> formattedAccounts = AccountCreditFormatter.formatAccountCredits(accounts);
        return new AccountCreditsDTO(formattedAccounts);
    }

}
