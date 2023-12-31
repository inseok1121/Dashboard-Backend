package com.coin.analys.backend.batch.component.job;

import com.coin.analys.backend.batch.entity.Batch;
import lombok.Getter;
import lombok.Setter;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
@Getter
@Setter
public class CommandJob extends CommonJob{

    private final Logger log = LoggerFactory.getLogger(CommandJob.class);
    private Batch batch;
    private JobDetail job;
    private Trigger trigger;
    public void setJob(Batch batch){

        this.batch = batch;

        this.job = newJob(CommandJob.class)
                .withIdentity(String.valueOf(batch.getBatchId()))
                .usingJobData("COMMAND" + String.valueOf(batch.getBatchId()), batch.getTarget())
                .build();

        this.trigger = newTrigger()
                .withIdentity(String.valueOf(batch.getBatchId()))
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(batch.getSchedule())).build();

        log.info("[*] SET BATCH : " + String.valueOf(this.batch.getBatchId()));

        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        String batchId = jobExecutionContext.getJobDetail().getKey().getName();
        String jobData = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("COMMAND" + batchId);

        run(jobData);

    }

    public void run(String command){
        try{
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;

            while((line = br.readLine()) != null){
                log.info(line);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
