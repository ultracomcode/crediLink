package com.main.CrediLink.domain.integrations.service;


import com.main.CrediLink.domain.integrations.dto.IntegrationRequest;
import com.main.CrediLink.domain.integrations.entity.IntegrationEntity;
import com.main.CrediLink.domain.integrations.enums.IntegrationStatus;
import com.main.CrediLink.domain.integrations.enums.IntegrationsType;
import com.main.CrediLink.domain.integrations.repository.IntegrationRepository;
import com.main.CrediLink.dtos.ResponseDTO;
import com.main.CrediLink.utils.CryptoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IntegrationService {
    private final IntegrationRepository integrationRepository;
    private final CryptoService cryptoService;

    public IntegrationService(IntegrationRepository integrationRepository, CryptoService cryptoService) {
        this.integrationRepository = integrationRepository;
        this.cryptoService = cryptoService;
    }

    public ResponseDTO save(IntegrationRequest dto){

        if (findByTypeAndStatus(IntegrationsType.valueOf(dto.type().toUpperCase())).isPresent()){
            return new ResponseDTO("erro","Já existe uma integração cadastrada para esse tipo");
        };


        var entity = new IntegrationEntity();

        BeanUtils.copyProperties(dto, entity);

        entity.setType(IntegrationsType.valueOf(dto.type().toUpperCase()));

        if(!dto.password().isEmpty()){
            entity.setPassword(cryptoService.encrypt(dto.password()));
        }

        integrationRepository.save(entity);

        return new ResponseDTO("success","Integraçao cadastrada com sucesso");
    }

    public Optional<IntegrationEntity> findByTypeAndStatus(IntegrationsType type) {
        return integrationRepository.findByTypeAndStatus(type, IntegrationStatus.ACTIVE);
    }

}
