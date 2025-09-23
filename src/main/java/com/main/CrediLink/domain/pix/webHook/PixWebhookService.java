package com.main.CrediLink.domain.pix.webHook;

import com.main.CrediLink.domain.pix.PixTransactionRepository;
import com.main.CrediLink.domain.pix.webHook.dto.PixNotificacaoDTO;
import com.main.CrediLink.enuns.PixStatus;
import com.main.CrediLink.sippulse.dto.AddCreditDTO;
import com.main.CrediLink.sippulse.services.SipPulseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class PixWebhookService {
    private static final Logger log = LoggerFactory.getLogger(PixWebhookService.class);
    private final PixTransactionRepository pixTransactionRepository;
    private final SipPulseService sipPulseService;

    public PixWebhookService(PixTransactionRepository pixTransactionRepository, SipPulseService sipPulseService) {
        this.pixTransactionRepository = pixTransactionRepository;
        this.sipPulseService = sipPulseService;
    }

    public void CheckWebhookNotifications(List<PixNotificacaoDTO> pixList) {
        pixList.forEach(notify ->{

            var txidFound = pixTransactionRepository.findByTxid(notify.txid());

            txidFound.ifPresent(entity -> {

                if(entity.getStatus().equals(PixStatus.AT)) {

                    sipPulseService.addCredit(AddCreditDTO.fromEntity(entity));

                    entity.setStatus(PixStatus.CO);
                    entity.setPayment_at(parseHorario(notify.horario()));

                    pixTransactionRepository.save(entity);
                }
            });

        } );
    }

    public OffsetDateTime parseHorario(String horario) {
        return OffsetDateTime.parse(horario);
    }
}
