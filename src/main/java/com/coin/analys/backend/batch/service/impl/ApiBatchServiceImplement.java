package com.coin.analys.backend.batch.service.impl;

import com.coin.analys.backend.batch.component.job.ApiJob;
import com.coin.analys.backend.batch.dto.ApiBatchDto;
import com.coin.analys.backend.batch.entity.ApiBatch;
import com.coin.analys.backend.batch.repository.ApiBatchRepository;
import com.coin.analys.backend.batch.service.ApiBatchService;
import com.coin.analys.backend.batch.specification.ApiBatchSpecification;
import lombok.AllArgsConstructor;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApiBatchServiceImplement implements ApiBatchService {
    private final ApiBatchRepository apiBatchRepository;
    private final SchedulerFactory schedulerFactory;
    
    @Override
    public boolean startBatch(Long batchId) {
        return false;
    }

    @Override
    public List<ApiBatchDto> getBatchList() {
        List<ApiBatch> batches = apiBatchRepository.findAll();
        return batches.stream()
                .map(ApiBatch::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public ApiBatchDto registerBatch(ApiBatchDto apiBatchDto) {
        return ApiBatch.toDto(apiBatchRepository.save(ApiBatchDto.toEntity(apiBatchDto)));
    }

    @Override
    public boolean deleteBatch(Long batchId) {
        apiBatchRepository.deleteById(batchId);
        return true;
    }

    @Override
    public ApiBatchDto updateBatch(ApiBatchDto apiBatchDto) {
        return ApiBatch.toDto(apiBatchRepository.save(ApiBatchDto.toEntity(apiBatchDto)));
    }

    @Override
    public boolean stopBatch(Long batchId) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();

        scheduler.deleteJob(new JobKey("APIBATCH" + batchId));
        return false;
    }

    @Override
    public boolean runBatch(Long batchId) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();

        ApiBatch apiBatch = apiBatchRepository.findById(batchId).orElseThrow();

        ApiJob job = new ApiJob();
        job.setJobInformation(apiBatch);
        scheduler.scheduleJob(job.getJob(), job.getTrigger());

        return false;
    }
    
    public Page<ApiBatchDto> searchBatchList(String type, String target, String schedule, Pageable pageable) {
        Specification<ApiBatch> spec = Specification.where(null);

        if (type != null && !type.isEmpty()) {
            spec = spec.and(ApiBatchSpecification.equalType(type));
        }

        if (target != null && !target.isEmpty()) {
            spec = spec.and(ApiBatchSpecification.likeTarget(target));
        }

        if (schedule != null && !schedule.isEmpty()) {
            spec = spec.and(ApiBatchSpecification.likeSchedule(schedule));
        }

        Page<ApiBatch> batches = apiBatchRepository.findAll(spec, pageable);

        // Batch 엔티티를 ApiBatchDto로 변환
        Page<ApiBatchDto> apiBatchDtos = batches.map(ApiBatch::toDto);

        return apiBatchDtos;
    }
}
