package com.main.CrediLink.application.webhook.controller;

import com.main.CrediLink.application.webhook.service.WebhookService;
import com.main.CrediLink.application.webhook.dto.WebhookNotificationDTO;
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

    private final WebhookService webhookService;

    public PixWebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping("/pix")
    public ResponseEntity<Void> receivePixNotification(@RequestBody Map<String, List<WebhookNotificationDTO>> payload) {

        List<WebhookNotificationDTO> pixList = payload.get("pix");

        webhookService.checkWebhookNotifications(pixList);

        return ResponseEntity.ok().build();
    }

}
