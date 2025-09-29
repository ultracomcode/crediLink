package com.main.CrediLink.domain.integrations.service;


import com.main.CrediLink.domain.integrations.dto.IntegrationRequest;
import com.main.CrediLink.domain.integrations.dto.IntegrationResponse;
import com.main.CrediLink.domain.integrations.entity.IntegrationEntity;
import com.main.CrediLink.domain.integrations.enums.IntegrationStatus;
import com.main.CrediLink.domain.integrations.enums.IntegrationsType;
import com.main.CrediLink.domain.integrations.repository.IntegrationRepository;
import com.main.CrediLink.dtos.ResponseDTO;
import com.main.CrediLink.utils.CryptoService;
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
