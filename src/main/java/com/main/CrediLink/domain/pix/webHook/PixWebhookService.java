package com.main.CrediLink.domain.pix.webHook;

import com.main.CrediLink.domain.pix.PixTransactionRepository;
import com.main.CrediLink.domain.pix.webHook.dto.PixNotificacaoDTO;
import com.main.CrediLink.enuns.PixStatus;
import com.main.CrediLink.ixc.service.InvoiceService;
import com.main.CrediLink.sippulse.dto.AddCreditDTO;
import com.main.CrediLink.sippulse.services.SipPulseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PixWebhookService {
    private static final Logger log = LoggerFactory.getLogger(PixWebhookService.class);
    private final PixTransactionRepository pixTransactionRepository;
    private final SipPulseService sipPulseService;
    private final InvoiceService invoiceService;

    public PixWebhookService(PixTransactionRepository pixTransactionRepository, SipPulseService sipPulseService, InvoiceService invoiceService) {
        this.pixTransactionRepository = pixTransactionRepository;
        this.sipPulseService = sipPulseService;
        this.invoiceService = invoiceService;
    }

    public void CheckWebhookNotifications(List<PixNotificacaoDTO> pixList) {
        pixList.forEach(notify ->{

            var txidFound = pixTransactionRepository.findByTxid(notify.txid());

            txidFound.ifPresent(entity -> {

                if(entity.getStatus().equals(PixStatus.AT)) {

                    entity.setPaymentAtFromIsoZ(notify.horario());

                    sipPulseService.addCredit(AddCreditDTO.fromEntity(entity));

                    invoiceService.btnGenerateFinancial(entity);

                    entity.setStatus(PixStatus.CO);

                    pixTransactionRepository.save(entity);
                }
            });

        } );
    }
}
