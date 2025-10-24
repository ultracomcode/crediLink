package com.main.CrediLink.application.pix.job;

import com.main.CrediLink.application.pix.repository.PixTransactionRepository;
import com.main.CrediLink.application.pix.service.PixTransactionService;
import com.main.CrediLink.shared.enuns.PixStatus;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.Instant;

public class PixExpirationJob implements Job {

    private final PixTransactionService pixTransactionService;
    private final PixTransactionRepository pixTransactionRepository;

    public PixExpirationJob(PixTransactionService pixTransactionService, PixTransactionRepository pixTransactionRepository) {
        this.pixTransactionService = pixTransactionService;
        this.pixTransactionRepository = pixTransactionRepository;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        var list = pixTransactionRepository.findByStatus(PixStatus.AT);

        Instant now = Instant.now();

        for (var pixTransaction : list) {
            Instant expiracao = pixTransaction.getDataExpiracao();

            if (expiracao != null && (now.equals(expiracao) || now.isAfter(expiracao))) {
                pixTransactionService.expired(pixTransaction.getTxid());
            }
        }

    }
}
