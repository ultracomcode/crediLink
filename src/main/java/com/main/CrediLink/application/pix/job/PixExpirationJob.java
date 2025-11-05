package com.main.CrediLink.application.pix.job;

import com.main.CrediLink.application.pix.service.PixTransactionService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class PixExpirationJob implements Job {

    private final PixTransactionService pixTransactionService;

    public PixExpirationJob(PixTransactionService pixTransactionService) {
        this.pixTransactionService = pixTransactionService;
    }

    @Override
    public void execute(JobExecutionContext context) {

        String txid = context.getJobDetail().getJobDataMap().getString("txid");

        pixTransactionService.expired(txid);

    }
}
