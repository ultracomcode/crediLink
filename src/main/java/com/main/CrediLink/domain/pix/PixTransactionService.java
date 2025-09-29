package com.main.CrediLink.domain.pix;

import com.main.CrediLink.domain.bancos.dtos.PixPaymentRequest;
import com.main.CrediLink.domain.bancos.dtos.PixPaymentResponse;
import com.main.CrediLink.domain.bancos.itau.service.ItauService;
import com.main.CrediLink.domain.pix.dto.RequestPixDTO;
import com.main.CrediLink.domain.pix.dto.ResponsePixDto;
import com.main.CrediLink.domain.pix.dto.ResponsePixSave;
import com.main.CrediLink.domain.pix.exceptions.PixException;
import com.main.CrediLink.domain.utils.CurrentUserService;
import com.main.CrediLink.dtos.ResponseDTO;
import com.main.CrediLink.enuns.PixStatus;
import com.main.CrediLink.utils.ValidadeValueUtils;
import feign.FeignException;
import jakarta.transaction.Transactional;
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

    @Transactional
    public ResponsePixSave generatePixRequest(RequestPixDTO requestPixDTO) {
        validarUsuario();

        try {
            var pixPaymentRequest = buildPixRequest(requestPixDTO.value());

            var pixResponse = itauService.createCharge(pixPaymentRequest);

            return save(pixResponse, requestPixDTO);
        } catch (FeignException ex) {
            throw new PixException("Erro na integração com o Itaú", ex);
        } catch (Exception ex) {
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
                new PixPaymentRequest.Calendario(60),
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
        entity.setCriacaoFromIsoZ(dto.calendario().criacao());
        entity.setExpiracao(dto.calendario().expiracao());
        entity.setAccountcode(userDTO.accountCode());
        entity.setUser(currentUserService.getCurrentUser());
        entity.setObservacao(userDTO.obs());
        entity.setChave(chavePix);

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

    private void validarUsuario() {
        if (currentUserService.getCurrentUser().isAdmin()) {
            throw new PixException("Administrador não pode gerar pix");
        }
    }

}
