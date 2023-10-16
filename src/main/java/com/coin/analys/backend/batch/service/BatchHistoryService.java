package com.coin.analys.backend.batch.service;

import com.coin.analys.backend.batch.dto.BatchDto;
import com.coin.analys.backend.batch.dto.BatchHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public interface BatchHistoryService {

    Page<BatchHistoryDto> getBatchHistoryList(Pageable pageable);

    BatchHistoryDto successBatch(BatchDto batchDto, LocalDateTime startTime, LocalDateTime endTime, String msg);
    BatchHistoryDto failBatch(BatchDto batchDto, LocalDateTime startTime, LocalDateTime endTime, String msg);

    Page<BatchHistoryDto> searchBatchHistoryList(Long batchId
            , String result, Pageable pageable);
}
