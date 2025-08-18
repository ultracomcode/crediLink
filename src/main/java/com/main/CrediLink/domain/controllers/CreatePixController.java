package com.main.CrediLink.domain.controllers;

import com.main.CrediLink.domain.dto.PixQrCodeResponseDto;
import com.main.CrediLink.domain.dto.UserDTO;
import com.main.CrediLink.domain.services.PixCobrancaService;
import com.main.CrediLink.domain.services.SipPulseService;
import com.main.CrediLink.sippulse.services.SubscriberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatePixController {

    private final PixCobrancaService pixCobrancaService;

    public CreatePixController(SipPulseService creditService, SubscriberService subscriberService, PixCobrancaService pixCobrancaService) {

        this.pixCobrancaService = pixCobrancaService;
    }

    @PostMapping("/pix/create")
    public PixQrCodeResponseDto createPixPayment(@RequestBody UserDTO dto) {
        return pixCobrancaService.createPixPayment(dto);
    }
}
