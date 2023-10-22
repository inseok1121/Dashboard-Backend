package com.coin.analys.backend.batch.service.impl;

import com.coin.analys.backend.batch.dto.BatchHistoryDto;
import com.coin.analys.backend.batch.entity.BatchHistory;
import com.coin.analys.backend.batch.repository.BatchHistoryRepository;
import com.coin.analys.backend.batch.service.BatchHistoryService;
import com.coin.analys.backend.batch.specification.BatchHistorySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BatchHistoryServiceImplement implements BatchHistoryService {

    private final BatchHistoryRepository batchHistoryRepository;

    public BatchHistoryServiceImplement(BatchHistoryRepository batchHistoryRepository) {
        this.batchHistoryRepository = batchHistoryRepository;
    }

    @Override
    public Page<BatchHistoryDto> getBatchHistoryList(Pageable pageable) {
        return batchHistoryRepository.findAll(pageable).map(BatchHistory::toDto);
    }

    @Override
    public BatchHistoryDto successBatch(BatchDto batchDto, LocalDateTime startTime, LocalDateTime endTime, String msg) {
        BatchHistory batchHistory = new BatchHistory();
        batchHistory.setBatchId(batchDto.getBatchId());
        batchHistory.setResult("SUCCESS");
        batchHistory.setStartTime(startTime);
        batchHistory.setEndTime(endTime);
        batchHistory.setMsg(msg);

        batchHistoryRepository.save(batchHistory);

        return BatchHistory.toDto(batchHistory);
    }

    @Override
    public BatchHistoryDto failBatch(BatchDto batchDto, LocalDateTime startTime, LocalDateTime endTime, String msg) {
        BatchHistory batchHistory = new BatchHistory();
        batchHistory.setBatchId(batchDto.getBatchId());
        batchHistory.setResult("FAIL");
        batchHistory.setStartTime(startTime);
        batchHistory.setEndTime(endTime);
        batchHistory.setMsg(msg);

        batchHistoryRepository.save(batchHistory);

        return BatchHistory.toDto(batchHistory);
    }

    @Override
    public Page<BatchHistoryDto> searchBatchHistoryList(Long batchId, String result, Pageable pageable) {
        Specification<BatchHistory> spec = Specification.where(null);

        if(batchId != null){
            spec = spec.and(BatchHistorySpecification.likeBatchId(batchId));
        }

        if(result != null && !result.isEmpty()){
            spec = spec.and(BatchHistorySpecification.equalResult(result));
        }

        return batchHistoryRepository.findAll(spec, pageable).map(BatchHistory::toDto);
    }
}
