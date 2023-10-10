package com.coin.analys.backend.batch.service;

import com.coin.analys.backend.batch.dto.BatchDto;
import org.quartz.SchedulerException;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;


public interface BatchService {

    boolean startBatch(Long batchId);

    List<BatchDto> getBatchList();

    BatchDto registerBatch(BatchDto batchDto);

    boolean deleteBatch(Long batchId);

    BatchDto updateBatch(BatchDto batchDto);

    boolean stopBatch(Long batchId) throws SchedulerException;

    boolean runBatch(Long batchId) throws SchedulerException, ChangeSetPersister.NotFoundException;
}
