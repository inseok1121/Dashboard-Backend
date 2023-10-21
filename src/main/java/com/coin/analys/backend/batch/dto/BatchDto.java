package com.coin.analys.backend.batch.dto;

import com.coin.analys.backend.batch.entity.Batch;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BatchDto {
    private Long batchId;
    private String type;
    private String header;
    private String method;
    private String target;
    private String schedule;
    private String params;
    private LocalDateTime lastStartTime;
    private LocalDateTime lastEndTime;

    public static Batch toEntity(BatchDto dto){
        return Batch.builder()
                .batchId(dto.getBatchId())
                .type(dto.getType())
                .header(dto.getHeader())
                .method(dto.getMethod())
                .schedule(dto.getSchedule())
                .target(dto.getTarget())
                .params(dto.getParams())
                .lastStartTime(dto.getLastStartTime())
                .lastEndTime(dto.getLastEndTime())
                .build();
    }
}
