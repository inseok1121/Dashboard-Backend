package com.coin.analys.backend.batch.service.impl;

import com.coin.analys.backend.batch.component.job.CommandJob;
import com.coin.analys.backend.batch.dto.BatchDto;
import com.coin.analys.backend.batch.entity.Batch;
import com.coin.analys.backend.batch.repository.BatchRepository;
import com.coin.analys.backend.batch.service.BatchService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
public class BatchServiceImplement implements BatchService {

    private final BatchRepository batchRepository;
    private final SchedulerFactory schedulerFactory;

    @Autowired
    public BatchServiceImplement(BatchRepository batchRepository, SchedulerFactory schedulerFactory){
        this.batchRepository = batchRepository;
        this.schedulerFactory = schedulerFactory;
    }
    @Override
    public boolean startBatch(Long batchId) {
        return false;
    }

    @Override
    public List<BatchDto> getBatchList() {
        List<Batch> batches = batchRepository.findAll();
        return batches.stream()
                .map(Batch::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BatchDto registerBatch(BatchDto batchDto) {
        return Batch.toDto(batchRepository.save(BatchDto.toEntity(batchDto)));
    }

    @Override
    public boolean deleteBatch(Long batchId) {
        batchRepository.deleteById(batchId);
        return true;
    }

    @Override
    public BatchDto updateBatch(BatchDto batchDto) {
        return Batch.toDto(batchRepository.save(BatchDto.toEntity(batchDto)));
    }

    @Override
    public boolean stopBatch(Long batchId) {
        return false;
    }

    @Override
    public boolean runBatch(Long batchId) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = newJob(CommandJob.class)
                .withIdentity("CommandJob")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("CommandJob")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger);

        return false;
    }
}
