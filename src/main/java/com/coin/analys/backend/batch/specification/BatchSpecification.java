package com.coin.analys.backend.batch.specification;

import com.coin.analys.backend.batch.entity.Batch;
import org.springframework.data.jpa.domain.Specification;

public class BatchSpecification {
    public static Specification<Batch> equalType(String type){
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<Batch> likeTarget(String target){
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("target"), "%" + target + "%");
    }

    public static Specification<Batch> likeSchedule(String schedule){
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("schedule"), "%" + schedule + "%");
    }
}
