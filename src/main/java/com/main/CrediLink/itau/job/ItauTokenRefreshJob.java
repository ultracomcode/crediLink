package com.main.CrediLink.itau.job;

import com.main.CrediLink.domain.entity.TokenEntityItau;
import com.main.CrediLink.domain.repository.TokenItauRepository;
import com.main.CrediLink.itau.dto.FetchTokenResponse;
import com.main.CrediLink.itau.service.ItauTokenService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ItauTokenRefreshJob implements Job {

    private final TokenItauRepository tokenRepository;

    private final ItauTokenService itauTokenService;

    public ItauTokenRefreshJob(TokenItauRepository tokenRepository, ItauTokenService itauTokenService) {
        this.tokenRepository = tokenRepository;
        this.itauTokenService = itauTokenService;
    }

    @Override
    public void execute(JobExecutionContext context) {
        TokenEntityItau token = tokenRepository.findTopByOrderByIdDesc().orElseThrow();

        try {
            FetchTokenResponse refreshed = itauTokenService.refreshToken(token.getRefreshToken());

            token.setAccessToken(refreshed.getAccessToken());
            token.setRefreshToken(refreshed.getRefreshToken());
            token.setExpirationTime(Instant.now().plusSeconds(refreshed.getExpiresIn()));
            tokenRepository.save(token);

            QuartzSchedulerUtil.scheduleRefreshJob(token.getExpirationTime());

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}

