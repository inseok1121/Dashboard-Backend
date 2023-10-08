package com.coin.analys.backend.batch.service.impl;

import com.coin.analys.backend.batch.dto.BatchDto;
import com.coin.analys.backend.batch.entity.Batch;
import com.coin.analys.backend.batch.repository.BatchRepository;
import com.coin.analys.backend.batch.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class BatchServiceImplement implements BatchService {

    private final BatchRepository batchRepository;

    @Autowired
    public BatchServiceImplement(BatchRepository batchRepository){
        this.batchRepository = batchRepository;
    }
    @Override
    public boolean startBatch(String batchId) {
        return false;
    }

    @Override
    public List<BatchDto> getBatchList() {
        List<Batch> batches = batchRepository.findAll();
        return batches.stream()
                .map(BatchDto::toDto)
                .collect(Collectors.toList());
    }
}
