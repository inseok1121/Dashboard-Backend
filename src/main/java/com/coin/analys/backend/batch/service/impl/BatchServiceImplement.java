package com.coin.analys.backend.batch.service.impl;

import com.coin.analys.backend.batch.dto.BatchDto;
import com.coin.analys.backend.batch.entity.Batch;
import com.coin.analys.backend.batch.repository.BatchRepository;
import com.coin.analys.backend.batch.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchServiceImplement implements BatchService {

    private final BatchRepository batchRepository;

    @Autowired
    public BatchServiceImplement(BatchRepository batchRepository){
        this.batchRepository = batchRepository;
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
}