package com.coin.analys.backend.batch.component.job;

import com.coin.analys.backend.batch.entity.ApiBatch;
import com.coin.analys.backend.batch.repository.ApiBatchRepository;
import lombok.*;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ApiJob{

    private final Logger log = LoggerFactory.getLogger(ApiJob.class);
    private ApiBatch apiBatch;
    private JobDetail job;
    private Trigger trigger;
    private ApiBatchRepository apiBatchRepository;
    public void setJobInformation(ApiBatch apiBatch){

        this.apiBatch = apiBatch;

        this.job = newJob(ApiJob.class)
                .withIdentity(String.valueOf(apiBatch.getBatchId()))
                .usingJobData("APIBATCH" + String.valueOf(apiBatch.getBatchId()))
                .build();

        this.trigger = newTrigger()
                .withIdentity(String.valueOf(apiBatch.getBatchId()))
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(apiBatch.getSchedule())).build();

        log.info("[*] SET BATCH : " + String.valueOf(this.apiBatch.getBatchId()));

        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        }
    }

    public void execute(JobExecutionContext jobExecutionContext) {

        String batchId = jobExecutionContext.getJobDetail().getKey().getName();
        String jobData = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("COMMAND" + batchId);

        ApiBatch currentBatch  = apiBatchRepository.getReferenceById(Long.valueOf(batchId));

        if("GET".equals(currentBatch.getMethod())){

        }else if("POST".equals(currentBatch.getMethod())){

        }
    }
}
