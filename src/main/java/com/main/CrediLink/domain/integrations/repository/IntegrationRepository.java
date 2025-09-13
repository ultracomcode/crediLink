package com.main.CrediLink.domain.integrations.repository;

import com.main.CrediLink.domain.integrations.entity.IntegrationEntity;
import com.main.CrediLink.domain.integrations.enums.IntegrationsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntegrationRepository extends JpaRepository<IntegrationEntity, Long> {

    Optional<IntegrationEntity> findByType(IntegrationsType type);
}
