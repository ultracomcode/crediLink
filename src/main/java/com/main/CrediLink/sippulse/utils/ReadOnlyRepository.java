package com.main.CrediLink.sippulse.utils;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {
    Optional<T> findByAccountCode(ID accountCode);
}
