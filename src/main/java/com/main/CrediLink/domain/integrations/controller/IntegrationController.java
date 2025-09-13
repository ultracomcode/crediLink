package com.main.CrediLink.domain.integrations.controller;


import com.main.CrediLink.domain.integrations.dto.IntegrationRequest;
import com.main.CrediLink.domain.integrations.entity.IntegrationEntity;
import com.main.CrediLink.domain.integrations.service.IntegrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integrations")
public class IntegrationController {

    private final IntegrationService integrationService;

    public IntegrationController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @PostMapping("/create")
    public ResponseEntity<IntegrationEntity> save(@RequestBody @Valid IntegrationRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(integrationService.save(request));
    }
}
