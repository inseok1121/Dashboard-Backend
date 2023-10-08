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
    private String schedule;
    private String url;
    private String params;

    public static BatchDto toDto(Batch entity){
        return BatchDto.builder()
                .batchId(entity.getBatchId())
                .schedule(entity.getSchedule())
                .url(entity.getUrl())
                .params(entity.getParams())
                .build();
    }
}
