package com.main.CrediLink.domain.pix.webHook;

import com.main.CrediLink.domain.pix.webHook.dto.PixNotificacaoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/webhook/notify")
public class PixWebhookController {

    private final PixWebhookService pixWebhookService;

    public PixWebhookController(PixWebhookService pixWebhookService) {
        this.pixWebhookService = pixWebhookService;
    }

    @PostMapping("/pix")
    public ResponseEntity<Void> receivePixNotification(@RequestBody Map<String, List<PixNotificacaoDTO>> payload) {

        log.info("Pix Webhook Received");
        log.info(payload.toString());
        log.info("---------------------------");
        List<PixNotificacaoDTO> pixList = payload.get("pix");

        pixWebhookService.CheckWebhookNotifications(pixList);

        return ResponseEntity.ok().build();
    }

}
