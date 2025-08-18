package com.main.CrediLink.domain.pix.webHook;

import com.main.CrediLink.domain.pix.webHook.dto.PixNotificacaoDTO;
import com.main.CrediLink.domain.user.dto.UserDTO;
import com.main.CrediLink.domain.pix.PixRepository;
import com.main.CrediLink.sippulse.services.SipPulseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PixWebhookService {
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

                if(entity.getStatus().equals("ATIVO")) {

                    sipPulseService.addCredit(UserDTO.fromEntity(entity));

                    entity.setStatus("CONCLUIDO");

                    pixRepository.save(entity);
                }
            });

        } );
    }
}
