package com.main.CrediLink.domain.integrations.controller;


import com.main.CrediLink.domain.integrations.dto.IntegrationRequest;
import com.main.CrediLink.domain.integrations.dto.IntegrationResponse;
import com.main.CrediLink.domain.integrations.entity.IntegrationEntity;
import com.main.CrediLink.domain.integrations.service.IntegrationService;
import com.main.CrediLink.domain.user.dto.ResponseUserDTO;
import com.main.CrediLink.dtos.ResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
