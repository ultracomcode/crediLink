package com.main.CrediLink.domain.services;

import com.main.CrediLink.domain.dto.PixNotificacaoDTO;
import com.main.CrediLink.domain.dto.UserDTO;
import com.main.CrediLink.domain.repository.PixPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PixWebhookService {
    private final PixPaymentRepository pixPaymentRepository;
    private final SipPulseService sipPulseService;

    public PixWebhookService(PixPaymentRepository pixPaymentRepository, SipPulseService sipPulseService) {
        this.pixPaymentRepository = pixPaymentRepository;
        this.sipPulseService = sipPulseService;
    }

    public void CheckWebhookNotifications(List<PixNotificacaoDTO> pixList) {
        pixList.forEach(notify ->{

            var txidFound = pixPaymentRepository.findByTxid(notify.txid());

            txidFound.ifPresent(entity -> {

                if(entity.getStatus().equals("ATIVO")) {

                    sipPulseService.addCredit(UserDTO.fromEntity(entity));

                    entity.setStatus("CONCLUIDO");

                    pixPaymentRepository.save(entity);
                }
            });

        } );
    }
}
