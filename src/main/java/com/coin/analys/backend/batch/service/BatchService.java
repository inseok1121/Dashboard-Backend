package com.coin.analys.backend.batch.service;

import com.coin.analys.backend.batch.dto.BatchDto;
import com.coin.analys.backend.batch.entity.Batch;
import org.quartz.SchedulerException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BatchService {

    boolean startBatch(Long batchId);

    List<BatchDto> getBatchList();

    BatchDto registerBatch(BatchDto batchDto);

    boolean deleteBatch(Long batchId);

    BatchDto updateBatch(BatchDto batchDto);

    boolean stopBatch(Long batchId) throws SchedulerException;

    boolean runBatch(Long batchId) throws SchedulerException, ChangeSetPersister.NotFoundException;

    Page<BatchDto> searchBatchList(String type, String target, String schedule, Pageable pageable);
}
