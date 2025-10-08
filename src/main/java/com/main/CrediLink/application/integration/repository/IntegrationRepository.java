package com.main.CrediLink.application.integration.repository;

import com.main.CrediLink.application.integration.entity.IntegrationEntity;
import com.main.CrediLink.application.integration.enums.IntegrationStatus;
import com.main.CrediLink.application.integration.enums.IntegrationsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IntegrationRepository extends JpaRepository<IntegrationEntity, Long> {

    Optional<IntegrationEntity> findByTypeAndStatus(IntegrationsType type, IntegrationStatus status);

    Optional<IntegrationEntity> findByIdentifier(UUID identifier);
}
