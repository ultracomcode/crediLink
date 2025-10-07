package com.main.CrediLink.integration.sippulse.repository;

import com.main.CrediLink.integration.sippulse.entity.Subscriber;
import com.main.CrediLink.integration.sippulse.utils.ReadOnlyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriberRepository extends ReadOnlyRepository<Subscriber, String> {

    @Query(value = """
    SELECT s.account_code, s.credit
    FROM subscriber s
    WHERE 
        s.contract_number = :contract_number
      AND 
        s.active_outgoing_calls = 1
    ORDER BY s.credit DESC
    """, nativeQuery = true)
    List<String> findAllAccountcode(@Param("contract_number") String contract_number);

}
