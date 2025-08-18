package com.main.CrediLink.domain.pix;

import com.main.CrediLink.domain.pix.dto.PixQrCodeResponseDto;
import com.main.CrediLink.domain.user.dto.UserDTO;
import com.main.CrediLink.sippulse.services.SipPulseService;
import com.main.CrediLink.sippulse.services.SubscriberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PixController {

    private final PixService pixService;

    public PixController(SipPulseService creditService, SubscriberService subscriberService, PixService pixService) {

        this.pixService = pixService;
    }

    @PostMapping("/pix/create")
    public PixQrCodeResponseDto createPixPayment(@RequestBody UserDTO dto) {
        return pixService.createPixPayment(dto);
    }
}
