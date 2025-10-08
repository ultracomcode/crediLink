package com.main.CrediLink.application.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByUsername(String username);


    Optional<UserEntity> findByIdentifier(UUID identifier);

}
