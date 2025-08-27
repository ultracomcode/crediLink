package com.main.CrediLink.domain.pix.webHook;

import com.main.CrediLink.domain.pix.PixRepository;
import com.main.CrediLink.domain.pix.webHook.dto.PixNotificacaoDTO;
import com.main.CrediLink.sippulse.dto.AddCreditDTO;
import com.main.CrediLink.sippulse.services.SipPulseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PixWebhookService {
    private static final Logger log = LoggerFactory.getLogger(PixWebhookService.class);
    private final PixRepository pixRepository;
    private final SipPulseService sipPulseService;

    public PixWebhookService(PixRepository pixRepository, SipPulseService sipPulseService) {
        this.pixRepository = pixRepository;
        this.sipPulseService = sipPulseService;
    }

    public void CheckWebhookNotifications(List<PixNotificacaoDTO> pixList) {
        pixList.forEach(notify ->{

            var txidFound = pixRepository.findByTxid(notify.txid());

            txidFound.ifPresent(entity -> {

                if(entity.getStatus().equals("ATIVA")) {

                    sipPulseService.addCredit(AddCreditDTO.fromEntity(entity));

                    entity.setStatus("CONCLUIDO");

                    pixRepository.save(entity);
                }
            });

        } );
    }
}
