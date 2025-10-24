package com.main.CrediLink.application.config;

import com.main.CrediLink.application.pix.job.PixExpirationJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail pixExpirationJobDetail() {
        return JobBuilder.newJob(PixExpirationJob.class)
                .withIdentity("pixExpirationJob")
                .storeDurably()
                .build();
    }


    @Bean
    public Trigger pixExpirationTrigger(JobDetail pixExpirationJobDetail) {
        SimpleScheduleBuilder schedule = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1)
                .repeatForever()
                .withMisfireHandlingInstructionNextWithRemainingCount();

        return TriggerBuilder.newTrigger()
                .forJob(pixExpirationJobDetail)
                .withIdentity("pixExpirationTrigger")
                .withSchedule(schedule)
                .build();
    }

}
