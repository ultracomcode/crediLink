package com.main.CrediLink.domain.integrations.service;


import com.main.CrediLink.domain.integrations.repository.IntegrationRepository;
import org.springframework.stereotype.Service;

@Service
public class IntegrationService {
    private final IntegrationRepository integrationRepository;

    public IntegrationService(IntegrationRepository integrationRepository) {
        this.integrationRepository = integrationRepository;
    }

    public void save(String integration){

    }

}
