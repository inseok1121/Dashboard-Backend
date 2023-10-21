package com.coin.analys.backend.batch.service.impl;

import com.coin.analys.backend.batch.component.job.ApiJob;
import com.coin.analys.backend.batch.component.job.CommandJob;
import com.coin.analys.backend.batch.component.job.CommonJob;
import com.coin.analys.backend.batch.dto.BatchDto;
import com.coin.analys.backend.batch.entity.Batch;
import com.coin.analys.backend.batch.repository.BatchRepository;
import com.coin.analys.backend.batch.specification.BatchSpecification;
import com.coin.analys.backend.batch.service.BatchService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.quartz.JobBuilder.newJob;

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
    public boolean stopBatch(Long batchId) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();

        scheduler.deleteJob(new JobKey("CommandJob" + batchId));
        return false;
    }

    @Override
    public boolean runBatch(Long batchId) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();

        Batch batch = batchRepository.findById(batchId).orElseThrow();

        CommonJob job = createJob(batch);

        scheduler.scheduleJob(job.getJob(), job.getTrigger());

        return false;
    }

    private CommonJob createJob(Batch batch) {
        if ("COMMAND".equals(batch.getType())) {
            return createJobWithType(new CommandJob(), batch);
        } else if ("API".equals(batch.getType())) {
            return createJobWithType(new ApiJob(), batch);
        }

        return new CommonJob();
    }
    private CommonJob createJobWithType(CommonJob job, Batch batch) {
        job.setJobInformation(batch);
        return job;
    }

    public Page<BatchDto> searchBatchList(String type, String target, String schedule, Pageable pageable) {
        Specification<Batch> spec = Specification.where(null);

        if (type != null && !type.isEmpty()) {
            spec = spec.and(BatchSpecification.equalType(type));
        }

        if (target != null && !target.isEmpty()) {
            spec = spec.and(BatchSpecification.likeTarget(target));
        }

        if (schedule != null && !schedule.isEmpty()) {
            spec = spec.and(BatchSpecification.likeSchedule(schedule));
        }

        Page<Batch> batches = batchRepository.findAll(spec, pageable);

        // Batch 엔티티를 BatchDto로 변환
        Page<BatchDto> batchDtos = batches.map(Batch::toDto);

        return batchDtos;
    }
}
