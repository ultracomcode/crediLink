package com.main.CrediLink.domain.pix;

import com.main.CrediLink.domain.bancos.dtos.PixPaymentRequest;
import com.main.CrediLink.domain.bancos.dtos.PixPaymentResponse;
import com.main.CrediLink.domain.bancos.itau.service.ItauService;
import com.main.CrediLink.domain.pix.dto.RequestPixDTO;
import com.main.CrediLink.domain.pix.dto.ResponsePixDto;
import com.main.CrediLink.domain.pix.exceptions.PixException;
import com.main.CrediLink.domain.utils.CurrentUserService;
import com.main.CrediLink.dtos.ResponseDTO;
import com.main.CrediLink.enuns.PixStatus;
import com.main.CrediLink.utils.ValidadeValueUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PixTransactionService {
    @Value("${itau.chave.pix}")
    private String chavePix;

    private final ItauService itauService;
    private final PixTransactionRepository pixTransactionRepository;
    private final CurrentUserService currentUserService;

    public PixTransactionService(ItauService itauService, PixTransactionRepository pixTransactionRepository, CurrentUserService currentUserService) {
        this.itauService = itauService;
        this.pixTransactionRepository = pixTransactionRepository;
        this.currentUserService = currentUserService;
    }

    public ResponseDTO generatePixRequest(RequestPixDTO requestPixDTO) {
        try {
            var request = buildPixRequest(requestPixDTO.value());

            var createdPixPayment = itauService.createCharge(request);

            return save(createdPixPayment, requestPixDTO);

        } catch (Exception e) {
            throw new PixException("Erro ao criar cobrança Pix", e);
        }
    }

    private ResponseDTO save(PixPaymentResponse dto, RequestPixDTO userDTO) {

        PixTransactionEntity entity = buildPixTransactionEntity(dto, userDTO);

        pixTransactionRepository.save(entity);

        return new ResponseDTO("success", "Pix criado com sucesso");

    }

    public Page<ResponsePixDto> listAll(Pageable pageable) {

        if (currentUserService.isAdmin()){
            return pixTransactionRepository.findAll(pageable)
                    .map(ResponsePixDto::fromEntity);
        }

        return pixTransactionRepository.findByUserId(currentUserService.getCurrentUser().getId(), pageable)
                .map(ResponsePixDto::fromEntity);
    }

    private PixPaymentRequest buildPixRequest(String valor) {

        var value = ValidadeValueUtils.validateAndFormatAmount(valor);

        PixPaymentRequest request = new PixPaymentRequest(
                new PixPaymentRequest.Calendario(60),
                new PixPaymentRequest.Valor((value)),
                chavePix
        );

        return request;
    }

    private PixTransactionEntity buildPixTransactionEntity(PixPaymentResponse dto, RequestPixDTO userDTO) {

        PixTransactionEntity entity = new PixTransactionEntity();
        BeanUtils.copyProperties(dto, entity);

        if (dto.status().equals("ATIVA")) {
            entity.setStatus(PixStatus.AT);
        }

        if (userDTO.obs() == null || userDTO.obs().isEmpty()) {
            entity.setObservacao("Recarga Telefonia Via Api");
        }else {
            entity.setObservacao(userDTO.obs());
        }

        entity.setCriacaoFromIsoZ(dto.calendario().criacao());
        entity.setValor(dto.valor().original());
        entity.setExpiracao(dto.calendario().expiracao());
        entity.calculateExpirationDate();
        entity.setAccountcode(userDTO.accountCode());
        entity.setUser(currentUserService.getCurrentUser());


        return entity;
    }


    public ResponseDTO cancelPix(String txid) {
        return pixTransactionRepository.findByTxid(txid)
                .map(entity -> {
                    if (!entity.getStatus().canBeCancelled()) {
                        return new ResponseDTO("error", "Transação já esta " + entity.getStatus().getDescription());
                    }

                    entity.setStatus(PixStatus.CA);
                    pixTransactionRepository.save(entity);
                    return new ResponseDTO("success", "Pix cancelado com sucesso");
                })
                .orElse(new ResponseDTO("error", "Transação não encontrada"));
    }

}
