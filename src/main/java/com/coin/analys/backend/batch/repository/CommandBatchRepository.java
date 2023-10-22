package com.coin.analys.backend.batch.repository;

import com.coin.analys.backend.batch.entity.ApiBatch;
import com.coin.analys.backend.batch.entity.CommandBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommandBatchRepository extends JpaRepository<CommandBatch, Long>, JpaSpecificationExecutor<CommandBatch> {
}
