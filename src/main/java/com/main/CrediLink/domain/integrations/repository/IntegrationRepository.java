package com.main.CrediLink.domain.integrations.repository;

import com.main.CrediLink.domain.integrations.entity.Integration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationRepository extends JpaRepository<Integration, Long> {
}
