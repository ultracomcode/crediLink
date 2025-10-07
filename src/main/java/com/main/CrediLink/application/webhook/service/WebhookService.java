package com.main.CrediLink.application.webhook.service;

import com.main.CrediLink.application.webhook.dto.WebhookNotificationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebhookService {

    private final WebhookProcessor webhookProcessor;

    public WebhookService(WebhookProcessor webhookProcessor) {
        this.webhookProcessor = webhookProcessor;
    }

    public void checkWebhookNotifications(List<WebhookNotificationDTO> pixList) {

        for (WebhookNotificationDTO pix : pixList) {
            webhookProcessor.processNotification(pix);
        }
    }
}
