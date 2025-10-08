package com.main.CrediLink.application.integration.controller;


import com.main.CrediLink.application.integration.dto.IntegrationRequest;
import com.main.CrediLink.application.integration.dto.IntegrationRequestUpdate;
import com.main.CrediLink.application.integration.dto.IntegrationResponse;
import com.main.CrediLink.application.integration.service.IntegrationService;
import com.main.CrediLink.shared.dtos.ResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/integrations")
public class IntegrationController {

    private final IntegrationService integrationService;

    public IntegrationController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid IntegrationRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(integrationService.save(request));
    }

    @GetMapping()
    public ResponseEntity<Page<IntegrationResponse>> findAll(@PageableDefault(
            size = 5,
            direction = Sort.Direction.ASC
    ) Pageable pageable){

        return ResponseEntity.ok(integrationService.findAll(pageable));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable(value = "id")UUID id, @RequestBody @Valid IntegrationRequestUpdate request){
        return ResponseEntity.status(HttpStatus.CREATED).body(integrationService.update(id,request));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.CREATED).body(integrationService.toggleUserStatus(id));
    }
}
