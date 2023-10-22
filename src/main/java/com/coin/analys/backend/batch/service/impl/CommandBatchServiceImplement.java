package com.coin.analys.backend.batch.service.impl;

import com.coin.analys.backend.batch.component.job.CommandJob;
import com.coin.analys.backend.batch.dto.CommandBatchDto;
import com.coin.analys.backend.batch.entity.CommandBatch;
import com.coin.analys.backend.batch.repository.CommandBatchRepository;
import com.coin.analys.backend.batch.service.CommandBatchService;
import com.coin.analys.backend.batch.specification.CommandBatchSpecification;
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
public class CommandBatchServiceImplement implements CommandBatchService {
    
    private final CommandBatchRepository commandBatchRepository;
    private final SchedulerFactory schedulerFactory;

    @Override
    public boolean startBatch(Long batchId) {
        return false;
    }

    @Override
    public List<CommandBatchDto> getBatchList() {
        List<CommandBatch> batches = commandBatchRepository.findAll();
        return batches.stream()
                .map(CommandBatch::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public CommandBatchDto registerBatch(CommandBatchDto commandBatchDto) {
        return CommandBatch.toDto(commandBatchRepository.save(CommandBatchDto.toEntity(commandBatchDto)));
    }

    @Override
    public boolean deleteBatch(Long batchId) {
        commandBatchRepository.deleteById(batchId);
        return true;
    }

    @Override
    public CommandBatchDto updateBatch(CommandBatchDto commandBatchDto) {
        return CommandBatch.toDto(commandBatchRepository.save(CommandBatchDto.toEntity(commandBatchDto)));
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

        CommandBatch commandBatch = commandBatchRepository.findById(batchId).orElseThrow();

        CommandJob job = new CommandJob();
        job.setJobInformation(commandBatch);

        scheduler.scheduleJob(job.getJob(), job.getTrigger());

        return false;
    }



    public Page<CommandBatchDto> searchBatchList(String type, String target, String schedule, Pageable pageable) {
        Specification<CommandBatch> spec = Specification.where(null);

        if (type != null && !type.isEmpty()) {
            spec = spec.and(CommandBatchSpecification.equalType(type));
        }

        if (target != null && !target.isEmpty()) {
            spec = spec.and(CommandBatchSpecification.likeTarget(target));
        }

        if (schedule != null && !schedule.isEmpty()) {
            spec = spec.and(CommandBatchSpecification.likeSchedule(schedule));
        }

        Page<CommandBatch> batches = commandBatchRepository.findAll(spec, pageable);

        // Batch 엔티티를 CommandBatchDto로 변환
        Page<CommandBatchDto> commandBatchDtos = batches.map(CommandBatch::toDto);

        return commandBatchDtos;
    }
}
