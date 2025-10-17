package com.main.CrediLink.application.pix.service;

import com.main.CrediLink.application.pix.dto.RequestPixDTO;
import com.main.CrediLink.application.pix.dto.ResponsePixDto;
import com.main.CrediLink.application.pix.dto.ResponsePixSave;
import com.main.CrediLink.application.pix.entity.PixTransactionEntity;
import com.main.CrediLink.application.pix.exceptions.PixException;
import com.main.CrediLink.application.pix.repository.PixTransactionRepository;
import com.main.CrediLink.application.utils.CurrentUserService;
import com.main.CrediLink.integration.banco.dto.PixPaymentRequest;
import com.main.CrediLink.integration.banco.dto.PixPaymentResponse;
import com.main.CrediLink.integration.banco.itau.service.ItauService;
import com.main.CrediLink.shared.dtos.ResponseDTO;
import com.main.CrediLink.shared.enuns.PixStatus;
import com.main.CrediLink.shared.utils.ValidadeValueUtils;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
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

    @Transactional
    public ResponsePixSave generatePixRequest(RequestPixDTO requestPixDTO) {
        validarUsuario();

        try {
            var pixPaymentRequest = buildPixRequest(requestPixDTO.value());

            var pixResponse = itauService.createCharge(pixPaymentRequest);

            return save(pixResponse, requestPixDTO);
        } catch (FeignException ex) {
            throw new PixException("Erro na integração com o banco", ex);
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println(ex.getMessage());
            throw new PixException("Erro inesperado ao criar cobrança Pix", ex);
        }
    }


    private ResponsePixSave save(PixPaymentResponse dto, RequestPixDTO requestPixDTO) {

        PixTransactionEntity entity = buildPixTransactionEntity(dto, requestPixDTO);

        var pixTransaction = pixTransactionRepository.save(entity);
        
        return new ResponsePixSave(
                "success",
                "Pix criado com sucesso",
                new ResponsePixSave.Pix(
                        pixTransaction.getDataExpiracao(),
                        pixTransaction.getPixCopiaECola(),
                        pixTransaction.getTxid(),
                        pixTransaction.getStatus(),
                        pixTransaction.getValor()
                )
        );

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
                new PixPaymentRequest.Calendario(600),
                new PixPaymentRequest.Valor((value)),
                chavePix
        );

        return request;
    }

    private PixTransactionEntity buildPixTransactionEntity(PixPaymentResponse dto, RequestPixDTO userDTO) {
        PixTransactionEntity entity = new PixTransactionEntity();

        if (dto.status().equals("ATIVA")) entity.setStatus(PixStatus.AT);

        entity.setTxid(dto.txid());
        entity.setPixCopiaECola(dto.pixCopiaECola());
        entity.setLocation(dto.location());
        entity.setValor(dto.valor().original());

        entity.setCriacao(Instant.now());

        entity.setExpiracao(dto.calendario().expiracao());

        entity.setAccountcode(userDTO.accountCode());
        entity.setUser(currentUserService.getCurrentUser());
        entity.setObservacao(userDTO.obs());
        entity.setChave(chavePix);

        return entity;
    }



    @Transactional
    public ResponseDTO cancelPix(String txid) {

        System.out.println(txid);

        int updated = pixTransactionRepository
                .updateStatusIfCancellable(
                        txid,
                        PixStatus.CA,
                        currentUserService.getCurrentUser());

        if (updated == 0) {
            return new ResponseDTO("error", "Transação não encontrada ou já foi " +
                    "processada e não pode ser cancelada");
        }

        return new ResponseDTO("success", "Pix cancelado com sucesso");
    }

    @Transactional
    public void expired(String txid) {
        int updated = pixTransactionRepository
                .updateStatusIfCancellable(
                        txid,
                        PixStatus.EX,
                        currentUserService.getCurrentUser());

        if (updated == 0) {
            log.warn("Nenhuma transação expirada. txid={} (não encontrada ou status inválido)", txid);
        } else {
            log.info("Transação {} marcada como expirada com sucesso", txid);
        }
    }


    private void validarUsuario() {
        if (currentUserService.getCurrentUser().isAdmin()) {
            throw new PixException("Administrador não pode gerar pix");
        }
    }

}
