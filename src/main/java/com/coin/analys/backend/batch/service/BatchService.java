package com.coin.analys.backend.batch.service;

import com.coin.analys.backend.batch.dto.BatchDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BatchService {

    public boolean startBatch(Long batchId);

    List<BatchDto> getBatchList();

    BatchDto registerBatch(BatchDto batchDto);

    boolean deleteBatch(Long batchId);

    BatchDto updateBatch(BatchDto batchDto);
}
