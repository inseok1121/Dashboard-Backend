package com.coin.analys.backend.batch.component.job;

import com.coin.analys.backend.batch.entity.Batch;
import lombok.Getter;
import lombok.Setter;
import org.quartz.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CommonJob implements Job {

    protected JobDetail job;
    protected Trigger trigger;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String jobData = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("JOB_DATA");

        System.out.println("[*] EXECUTED [*] ====> " + jobData);
    }

    public void setJobInformation(Batch batch){
    }
}
