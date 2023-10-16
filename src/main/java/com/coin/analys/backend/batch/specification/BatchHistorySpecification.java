package com.coin.analys.backend.batch.specification;

import com.coin.analys.backend.batch.entity.Batch;
import com.coin.analys.backend.batch.entity.BatchHistory;
import org.springframework.data.jpa.domain.Specification;

public class BatchHistorySpecification {
    public static Specification<BatchHistory> equalResult(String result){
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("result"), result);
    }

    public static Specification<BatchHistory> likeBatchId(Long batchId){
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("batchId"), "%" + batchId.toString() + "%");
    }
}
