package com.main.CrediLink.domain.integrations.service;


import com.main.CrediLink.domain.integrations.dto.IntegrationRequest;
import com.main.CrediLink.domain.integrations.entity.IntegrationEntity;
import com.main.CrediLink.domain.integrations.enums.IntegrationsType;
import com.main.CrediLink.domain.integrations.repository.IntegrationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IntegrationService {
    private final IntegrationRepository integrationRepository;

    public IntegrationService(IntegrationRepository integrationRepository) {
        this.integrationRepository = integrationRepository;
    }

    public IntegrationEntity save(IntegrationRequest dto){
        var entity = new IntegrationEntity();

        BeanUtils.copyProperties(dto, entity);

        entity.setType(IntegrationsType.valueOf(dto.type().toUpperCase()));

        return integrationRepository.save(entity);
    }

    public Optional<IntegrationEntity> findByType(IntegrationsType type) {
        return integrationRepository.findByType(type);
    }


}
