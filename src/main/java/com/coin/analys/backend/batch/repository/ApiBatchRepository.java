package com.coin.analys.backend.batch.repository;

import com.coin.analys.backend.batch.entity.ApiBatch;
import com.coin.analys.backend.batch.entity.BatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApiBatchRepository extends JpaRepository<ApiBatch, Long>, JpaSpecificationExecutor<ApiBatch> {
}
