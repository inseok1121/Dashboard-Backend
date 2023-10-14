package com.coin.analys.backend.batch.dto;

import com.coin.analys.backend.batch.entity.Batch;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BatchDto {
    private Long batchId;
    private String type;
    private String target;
    private String schedule;
    private String params;

    public static Batch toEntity(BatchDto dto){
        return Batch.builder()
                .batchId(dto.getBatchId())
                .type(dto.getType())
                .schedule(dto.getSchedule())
                .target(dto.getTarget())
                .params(dto.getParams())
                .build();
    }
}
