package com.main.CrediLink.integration.sippulse.services;

import com.main.CrediLink.shared.exceptions.AccountCodeNotFoundException;
import com.main.CrediLink.integration.sippulse.dto.AccountCreditsDTO;
import com.main.CrediLink.integration.sippulse.repository.SubscriberRepository;
import com.main.CrediLink.integration.sippulse.utils.AccountCreditFormatter;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public AccountCreditsDTO findAllAccountcode(String contract_number) {
        List<String> accounts = subscriberRepository.findAllAccountcode(contract_number);
        return AccountCreditFormatter.formatAccountCredits(accounts);
    }


}
