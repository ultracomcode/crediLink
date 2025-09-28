package com.main.CrediLink.domain.pix.webHook;

import com.main.CrediLink.domain.pix.webHook.dto.PixNotificacaoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PixWebhookService {

    private final PixProcessor pixProcessor;

    public PixWebhookService(PixProcessor pixProcessor) {
        this.pixProcessor = pixProcessor;
    }

    public void checkWebhookNotifications(List<PixNotificacaoDTO> pixList) {
        pixList.forEach(pixProcessor::processNotification);
    }
}
