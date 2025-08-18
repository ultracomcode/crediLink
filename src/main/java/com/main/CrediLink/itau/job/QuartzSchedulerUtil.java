package com.main.CrediLink.itau.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class QuartzSchedulerUtil {

    private static Scheduler scheduler;

    @Autowired
    public QuartzSchedulerUtil(Scheduler scheduler) {
        QuartzSchedulerUtil.scheduler = scheduler;
    }

    public static void scheduleRefreshJob(Instant expirationTime) {
        Instant executionTime = expirationTime.minusSeconds(60); // 1 min antes

        JobKey jobKey = JobKey.jobKey("tokenRefreshJob");
        TriggerKey triggerKey = TriggerKey.triggerKey("tokenRefreshTrigger");

        try {
            JobDetail jobDetail;

            if (!scheduler.checkExists(jobKey)) {
                jobDetail = JobBuilder.newJob(ItauTokenRefreshJob.class)
                        .withIdentity(jobKey)
                        .storeDurably()
                        .build();

                scheduler.addJob(jobDetail, false);
            } else {
                jobDetail = scheduler.getJobDetail(jobKey);
            }

            if (scheduler.checkExists(triggerKey)) {
                scheduler.unscheduleJob(triggerKey);
            }

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .forJob(jobDetail)
                    .startAt(Date.from(executionTime))
                    .build();

            scheduler.scheduleJob(trigger);

        } catch (SchedulerException e) {
            System.out.println("Erro ao agendar job: " + e.getMessage());
        }
    }


}

