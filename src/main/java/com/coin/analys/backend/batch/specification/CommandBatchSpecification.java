package com.coin.analys.backend.batch.specification;

import com.coin.analys.backend.batch.entity.CommandBatch;
import org.springframework.data.jpa.domain.Specification;

public class CommandBatchSpecification {
    public static Specification<CommandBatch> equalType(String type){
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<CommandBatch> likeTarget(String target){
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("target"), "%" + target + "%");
    }

    public static Specification<CommandBatch> likeSchedule(String schedule){
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("schedule"), "%" + schedule + "%");
    }
}
