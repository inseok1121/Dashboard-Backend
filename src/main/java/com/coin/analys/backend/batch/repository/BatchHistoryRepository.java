package com.coin.analys.backend.batch.repository;

import com.coin.analys.backend.batch.entity.Batch;
import com.coin.analys.backend.batch.entity.BatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchHistoryRepository extends JpaRepository<BatchHistory, Long>, JpaSpecificationExecutor<BatchHistory> {
}
