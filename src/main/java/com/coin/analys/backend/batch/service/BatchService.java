package com.coin.analys.backend.batch.service;

import com.coin.analys.backend.batch.dto.BatchDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BatchService {

    public boolean startBatch(String batchId);

    List<BatchDto> getBatchList();
}
