package com.main.CrediLink.domain.pix.webHook;

import com.main.CrediLink.domain.pix.webHook.dto.PixNotificacaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webhook/pixstatus")
public class PixWebhookController {

    private final PixWebhookService pixWebhookService;

    public PixWebhookController(PixWebhookService pixWebhookService) {
        this.pixWebhookService = pixWebhookService;
    }

    @PostMapping("/pix")
    public ResponseEntity<Void> receivePixNotification(@RequestBody Map<String, List<PixNotificacaoDTO>> payload) {
        List<PixNotificacaoDTO> pixList = payload.get("pix");

        pixWebhookService.CheckWebhookNotifications(pixList);

        return ResponseEntity.ok().build();
    }

}
