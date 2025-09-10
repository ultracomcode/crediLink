package com.main.CrediLink.domain.integrations.controller;


import com.main.CrediLink.domain.integrations.service.IntegrationService;
import org.springframework.stereotype.Controller;

@Controller
public class IntegrationController {

    private final IntegrationService integrationService;

    public IntegrationController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }
}
