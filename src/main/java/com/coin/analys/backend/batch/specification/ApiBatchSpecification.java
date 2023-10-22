package com.coin.analys.backend.batch.specification;

import com.coin.analys.backend.batch.entity.ApiBatch;
import org.springframework.data.jpa.domain.Specification;

public class ApiBatchSpecification {
    public static Specification<ApiBatch> equalType(String type){
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<ApiBatch> likeTarget(String target){
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("target"), "%" + target + "%");
    }

    public static Specification<ApiBatch> likeSchedule(String schedule){
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("schedule"), "%" + schedule + "%");
    }
}
