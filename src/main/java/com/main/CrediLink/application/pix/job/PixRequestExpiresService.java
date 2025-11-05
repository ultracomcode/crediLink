package com.main.CrediLink.application.pix.job;

import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class PixRequestExpiresService {

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
            e.printStackTrace();
        }
    }
}
