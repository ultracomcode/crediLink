package com.main.CrediLink.application.integration.service;


import com.main.CrediLink.application.integration.dto.IntegrationRequest;
import com.main.CrediLink.application.integration.dto.IntegrationResponse;
import com.main.CrediLink.application.integration.entity.IntegrationEntity;
import com.main.CrediLink.application.integration.enums.IntegrationStatus;
import com.main.CrediLink.application.integration.enums.IntegrationsType;
import com.main.CrediLink.application.integration.repository.IntegrationRepository;
import com.main.CrediLink.shared.dtos.ResponseDTO;
import com.main.CrediLink.shared.utils.CryptoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IntegrationService {
    private final IntegrationRepository integrationRepository;
    private final CryptoService cryptoService;

    public IntegrationService(IntegrationRepository integrationRepository, CryptoService cryptoService) {
        this.integrationRepository = integrationRepository;
        this.cryptoService = cryptoService;
    }

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
        return integrationRepository.findByTypeAndStatus(type, IntegrationStatus.ACTIVE)
                .orElse(null);
    }

}
