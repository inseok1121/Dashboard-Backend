package com.coin.analys.backend.batch.service;

import org.springframework.stereotype.Service;

@Service
public interface BatchService {

    public boolean startBatch(String batchId);
}
