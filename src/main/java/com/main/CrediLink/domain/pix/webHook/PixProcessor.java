package com.main.CrediLink.domain.pix.webHook;

import com.main.CrediLink.domain.pix.PixTransactionRepository;
import com.main.CrediLink.domain.pix.webHook.dto.PixNotificacaoDTO;
import com.main.CrediLink.enuns.PixStatus;
import com.main.CrediLink.ixc.service.InvoiceService;
import com.main.CrediLink.sippulse.dto.AddCreditDTO;
import com.main.CrediLink.sippulse.services.SipPulseService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PixProcessor {
    private static final Logger log = LoggerFactory.getLogger(PixProcessor.class);

    private final PixTransactionRepository pixTransactionRepository;
    private final SipPulseService sipPulseService;
    private final InvoiceService invoiceService;

    public PixProcessor(PixTransactionRepository repo,
                        SipPulseService sipPulseService,
                        InvoiceService invoiceService) {
        this.pixTransactionRepository = repo;
        this.sipPulseService = sipPulseService;
        this.invoiceService = invoiceService;
    }

    @Async
    @Transactional
    public void processNotification(PixNotificacaoDTO notify) {
        pixTransactionRepository.findByTxid(notify.txid()).ifPresent(entity -> {
            if (entity.getStatus() == PixStatus.AT) {
                try {
                    entity.setPaymentAtFromIsoZ(notify.horario());

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

