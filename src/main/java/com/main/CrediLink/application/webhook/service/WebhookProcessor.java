package com.main.CrediLink.application.webhook.service;

import com.main.CrediLink.application.pix.repository.PixTransactionRepository;
import com.main.CrediLink.application.webhook.dto.WebhookNotificationDTO;
import com.main.CrediLink.shared.enuns.PixStatus;
import com.main.CrediLink.integration.ixc.service.InvoiceService;
import com.main.CrediLink.integration.sippulse.dto.AddCreditDTO;
import com.main.CrediLink.integration.sippulse.services.SipPulseService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class WebhookProcessor {
    private static final Logger log = LoggerFactory.getLogger(WebhookProcessor.class);

    private final PixTransactionRepository pixTransactionRepository;
    private final SipPulseService sipPulseService;
    private final InvoiceService invoiceService;

    public WebhookProcessor(PixTransactionRepository repo,
                            SipPulseService sipPulseService,
                            InvoiceService invoiceService) {
        this.pixTransactionRepository = repo;
        this.sipPulseService = sipPulseService;
        this.invoiceService = invoiceService;
    }

    @Async
    @Transactional
    public void processNotification(WebhookNotificationDTO notify) {
        pixTransactionRepository.findByTxid(notify.txid()).ifPresent(entity -> {
            if (entity.getStatus() == PixStatus.AT) {
                try {
                    entity.setPaymentAt(Instant.parse(notify.horario()));

                    sipPulseService.addCredit(AddCreditDTO.fromEntity(entity));

                    invoiceService.receiveInvoice(entity);

                    entity.setStatus(PixStatus.CO);
                    pixTransactionRepository.save(entity);

                    log.info("PIX txid={} processado com sucesso", notify.txid());

                } catch (Exception e) {
                    log.error("Erro ao processar notificação PIX txid={} : {}", notify.txid(), e.getMessage(), e);
                }
            }
        });
    }
}

