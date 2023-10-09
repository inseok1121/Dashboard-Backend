package com.coin.analys.backend.batch.component.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class CommonJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String jobData = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("JOB_DATA");

        System.out.println("[*] EXECUTED [*] ====> " + jobData);
    }
}
