package com.main.CrediLink.domain.pix;

import com.main.CrediLink.domain.pix.dto.RequestPixDTO;
import com.main.CrediLink.domain.pix.dto.ResponsePixDto;
import com.main.CrediLink.domain.pix.exceptions.PixException;
import com.main.CrediLink.domain.token.ItauTokenService;
import com.main.CrediLink.domain.utils.CurrentUserService;
import com.main.CrediLink.dtos.ResponseDTO;
import com.main.CrediLink.enuns.PixStatus;
import com.main.CrediLink.itau.dto.PixRequest;
import com.main.CrediLink.itau.dto.responsePix.createPixPaymentDTO;
import com.main.CrediLink.itau.feing.ItauPixClient;
import feign.FeignException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.UUID;

@Service
public class PixTransactionService {
    @Value("${itau.chave.pix}")
    private String chavePix;

    private final ItauPixClient itauPixClient;
    private final ItauTokenService itauTokenService;
    private final PixTransactionRepository pixTransactionRepository;
    private final CurrentUserService currentUserService;

    public PixTransactionService(ItauPixClient itauPixClient, ItauTokenService itauTokenService, PixTransactionRepository pixTransactionRepository, CurrentUserService currentUserService) {
        this.itauPixClient = itauPixClient;
        this.itauTokenService = itauTokenService;
        this.pixTransactionRepository = pixTransactionRepository;
        this.currentUserService = currentUserService;
    }

    public ResponseDTO createPixPayment(RequestPixDTO requestPixDTO) {
        try {
            var request = buildPixRequest(requestPixDTO.value());

            createPixPaymentDTO createdPixPayment = itauPixClient.gerarPix(
                    getAuthHeader(),
                    getCorrelationIdHeader(),
                    getFlowIdHeader(),
                    request
            );

            return save(createdPixPayment, requestPixDTO);

        } catch (FeignException e) {
            if (e.status() != 201) {
                throw new PixException(
                        "Falha na criação do Pix. Status: " + e.status() + " - Corpo: " + e.contentUTF8(),
                        e
                );
            }
            throw e;
        }catch (Exception e) {
            throw new PixException("Erro ao criar cobrança Pix", e);
        }
    }

    private ResponseDTO save(createPixPaymentDTO dto, RequestPixDTO userDTO) {

        PixTransactionEntity entity = buildPixTransactionEntity(dto, userDTO);

        pixTransactionRepository.save(entity);

        return new ResponseDTO("success", "Pix criado com sucesso");

    }

    private PixRequest buildPixRequest(String valor) {

        PixRequest request = new PixRequest();

        var value = new PixRequest.Valor();

        value.setOriginal(validateAndFormatAmount(valor));
        request.setValor(value);
        request.setChave(chavePix);
        return request;
    }

    private PixTransactionEntity buildPixTransactionEntity(createPixPaymentDTO dto, RequestPixDTO userDTO) {

        PixTransactionEntity entity = new PixTransactionEntity();
        BeanUtils.copyProperties(dto, entity);

        if (dto.status().equals("ATIVA")) {
            entity.setStatus(PixStatus.AT);
        }

        entity.setCriacao(OffsetDateTime.parse(dto.calendario().criacao()));
        entity.setValor(dto.valor().original());
        entity.setExpiracao(dto.calendario().expiracao());
        entity.calculateExpirationDate();
        entity.setAccountcode(userDTO.accountCode());
        entity.setObservacao(userDTO.obs());
        entity.setUser(currentUserService.getCurrentUser());

        return entity;
    }


    public Page<ResponsePixDto> listAll(Pageable pageable) {

        if (currentUserService.isAdmin()){
            return pixTransactionRepository.findAll(pageable)
                    .map(ResponsePixDto::fromEntity);
        }

        return pixTransactionRepository.findByUserId(currentUserService.getCurrentUser().getId(), pageable)
                .map(ResponsePixDto::fromEntity);
    }

    public ResponseDTO cancelPix(String txid) {
        return pixTransactionRepository.findByTxid(txid)
                .map(entity -> {
                    if (!entity.getStatus().canBeCancelled()) {
                        return new ResponseDTO("error", "Transação já esta cancelada.");
                    }

                    entity.setStatus(PixStatus.CA);
                    pixTransactionRepository.save(entity);
                    return new ResponseDTO("success", "Pix cancelado com sucesso");
                })
                .orElse(new ResponseDTO("error", "Transação não encontrada"));
    }



    private String validateAndFormatAmount(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("Valor não pode ser nulo ou vazio");
        }

        BigDecimal parsed;

        try {
            parsed = new BigDecimal(valor.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor inválido: não é um número");
        }

        if (parsed.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }

        return String.format(Locale.US, "%.2f", parsed);
    }

    private String getAuthHeader() {
        return "Bearer " + itauTokenService.getToken();
    }

    private String getFlowIdHeader() {
        return "1";
    }

    private String getCorrelationIdHeader() {
        return UUID.randomUUID().toString();
    }
}
