package com.main.CrediLink.sippulse.repository;

import com.main.CrediLink.sippulse.dto.AccountCodesDTO;
import com.main.CrediLink.sippulse.entity.Subscriber;
import com.main.CrediLink.sippulse.utils.ReadOnlyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriberRepository extends ReadOnlyRepository<Subscriber, String> {

    @Query(value = "SELECT s.account_code,s.credit " +
            "FROM subscriber s " +
            "WHERE SUBSTRING_INDEX(s.first_name, ' - ', 1) = :firstName " +
            "AND s.active_outgoing_calls = 1",
            nativeQuery = true)
    List<String> findAllAccountcode(@Param("firstName") String firstName);
}
