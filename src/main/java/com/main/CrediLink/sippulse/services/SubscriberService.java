package com.main.CrediLink.sippulse.services;

import com.main.CrediLink.exceptions.AccountCodeNotFoundException;
import com.main.CrediLink.sippulse.repository.SubscriberRepository;
import org.springframework.stereotype.Service;

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

}
