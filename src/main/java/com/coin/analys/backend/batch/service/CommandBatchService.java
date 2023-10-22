package com.coin.analys.backend.batch.service;

import com.coin.analys.backend.batch.dto.CommandBatchDto;
import org.quartz.SchedulerException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommandBatchService {
    boolean startBatch(Long batchId);

    List<CommandBatchDto> getBatchList();

    CommandBatchDto registerBatch(CommandBatchDto commandBatchDto);

    boolean deleteBatch(Long batchId);

    CommandBatchDto updateBatch(CommandBatchDto commandBatchDto);

    boolean stopBatch(Long batchId) throws SchedulerException;

    boolean runBatch(Long batchId) throws SchedulerException, ChangeSetPersister.NotFoundException;

    Page<CommandBatchDto> searchBatchList(String type, String target, String schedule, Pageable pageable);
}
