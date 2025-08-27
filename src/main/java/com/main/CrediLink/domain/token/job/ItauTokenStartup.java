package com.main.CrediLink.domain.token.job;


import com.main.CrediLink.domain.token.TokenEntityItau;
import com.main.CrediLink.domain.token.TokenItauRepository;
import com.main.CrediLink.itau.dto.FetchTokenResponse;
import com.main.CrediLink.domain.token.ItauTokenService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ItauTokenStartup {

    private final ItauTokenService itauTokenService;

    private final TokenItauRepository tokenRepository;

    public ItauTokenStartup(ItauTokenService itauTokenService, TokenItauRepository tokenRepository) {
        this.itauTokenService = itauTokenService;
        this.tokenRepository = tokenRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        FetchTokenResponse token = itauTokenService.fetchToken();

        var entity = new TokenEntityItau();

        entity.setAccessToken(token.getAccessToken());
        entity.setRefreshToken(token.getRefreshToken());
        entity.setExpirationTime(Instant.now().plusSeconds(token.getExpiresIn()));
        tokenRepository.save(entity);

        QuartzSchedulerUtil.scheduleRefreshJob(entity.getExpirationTime());
    }
}
