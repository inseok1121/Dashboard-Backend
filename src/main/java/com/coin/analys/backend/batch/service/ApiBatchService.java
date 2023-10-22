package com.coin.analys.backend.batch.service;

import com.coin.analys.backend.batch.dto.ApiBatchDto;
import org.quartz.SchedulerException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApiBatchService {
    boolean startBatch(Long batchId);

    List<ApiBatchDto> getBatchList();

    ApiBatchDto registerBatch(ApiBatchDto apiBatchDto);

    boolean deleteBatch(Long batchId);

    ApiBatchDto updateBatch(ApiBatchDto apiBatchDto);

    boolean stopBatch(Long batchId) throws SchedulerException;

    boolean runBatch(Long batchId) throws SchedulerException, ChangeSetPersister.NotFoundException;

    Page<ApiBatchDto> searchBatchList(String type, String target, String schedule, Pageable pageable);
}
