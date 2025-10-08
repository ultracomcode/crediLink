package com.main.CrediLink.application.integration.service;


import com.main.CrediLink.application.integration.dto.IntegrationRequest;
import com.main.CrediLink.application.integration.dto.IntegrationRequestUpdate;
import com.main.CrediLink.application.integration.dto.IntegrationResponse;
import com.main.CrediLink.application.integration.entity.IntegrationEntity;
import com.main.CrediLink.application.integration.enums.IntegrationStatus;
import com.main.CrediLink.application.integration.enums.IntegrationsType;
import com.main.CrediLink.application.integration.repository.IntegrationRepository;
import com.main.CrediLink.application.user.enuns.StatusUser;
import com.main.CrediLink.application.user.exceptions.UserException;
import com.main.CrediLink.shared.dtos.ResponseDTO;
import com.main.CrediLink.shared.utils.CryptoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IntegrationService {
    private final IntegrationRepository integrationRepository;
    private final CryptoService cryptoService;

    public IntegrationService(IntegrationRepository integrationRepository, CryptoService cryptoService) {
        this.integrationRepository = integrationRepository;
        this.cryptoService = cryptoService;
    }

    @Transactional
    public ResponseDTO save(IntegrationRequest dto){

        if (findByTypeAndStatus(IntegrationsType.valueOf(dto.type().toUpperCase())) != null){
            return new ResponseDTO("erro","Já existe uma integração cadastrada para esse tipo");
        };


        var entity = new IntegrationEntity();

        BeanUtils.copyProperties(dto, entity);

        entity.setType(IntegrationsType.valueOf(dto.type().toUpperCase()));

        if(dto.type().equals("SIPPULSE") && !dto.password().isEmpty()  ){
            entity.setPassword(cryptoService.encrypt(dto.password()));
        }

        integrationRepository.save(entity);

        return new ResponseDTO("success","Integraçao cadastrada com sucesso");
    }

    public Page<IntegrationResponse> findAll(Pageable pageable){
        return integrationRepository.findAll(pageable)
                .map(IntegrationResponse::fromEntity);
    }

    public IntegrationEntity findByTypeAndStatus(IntegrationsType type) {
        return integrationRepository.findByTypeAndStatus(type, IntegrationStatus.A)
                .orElse(null);
    }

    @Transactional
    public ResponseDTO update(UUID id, IntegrationRequestUpdate request) {
        IntegrationEntity integration = integrationRepository.findByIdentifier(id)
                .orElseThrow(() -> new UserException("Integração não encontrada"));

        if (integration.getStatus() == IntegrationStatus.I){
            return new ResponseDTO("erro", "Integração Inativa, Não pode ser alterada");
        }

        if (request.password() != null && !request.password().isEmpty()) {
            integration.setPassword(cryptoService.encrypt(request.password()));
        }

        if (request.name() != null) integration.setName(request.name());
        if (request.tokenApi() != null) integration.setTokenApi(request.tokenApi());
        if (request.urlApi() != null) integration.setUrlApi(request.urlApi());
        if (request.username() != null) integration.setUsername(request.username());
        if (request.idContaBanco() != null) integration.setIdContaBanco(request.idContaBanco());
        if (request.idContaContabil() != null) integration.setIdContaContabil(request.idContaContabil());


        integrationRepository.save(integration);

        return new ResponseDTO("succes", "Integração atualizada com sucesso");
    }

    @Transactional
    public ResponseDTO toggleUserStatus(UUID id) {
        return integrationRepository.findByIdentifier(id)
                .map(integration -> {
                    IntegrationStatus newStatus = integration.getStatus() == IntegrationStatus.A ? IntegrationStatus.I : IntegrationStatus.A;
                    integration.setStatus(newStatus);
                    integrationRepository.save(integration);

                    String message = newStatus == IntegrationStatus.A
                            ? "Integração ativada com sucesso"
                            : "Integração desativado com sucesso";

                    return new ResponseDTO("success", message);
                })
                .orElse(new ResponseDTO("error", "Integração não encontrada"));
    }
}
