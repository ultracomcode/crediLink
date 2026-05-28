package com.main.CrediLink.application.pix.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class PixRequestExpiresService {
    private static final Logger log = LoggerFactory.getLogger(PixRequestExpiresService.class);

    private final Scheduler scheduler;

    public PixRequestExpiresService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void scheduleExpirationPix(String txid, Instant expiracao){
        try {

            Date startAt = Date.from(expiracao);

            JobDetail jobDetail = JobBuilder.newJob(PixExpirationJob.class)
                    .withIdentity("pix-exp-" + txid, "pix")
                    .usingJobData("txid", txid)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger-exp-" + txid, "pix")
                    .startAt(startAt)
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);

        }catch (SchedulerException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void cancelPixExpirationJob(String txid) {
        try {
            JobKey jobKey = new JobKey("pix-exp-" + txid, "pix");
            if (scheduler.checkExists(jobKey)) {
                scheduler.deleteJob(jobKey);
                log.info("Job de expiração do PIX {} removido com sucesso.", txid);
            } else {
                log.info("Nenhum job encontrado para o txid: {}", txid);
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
