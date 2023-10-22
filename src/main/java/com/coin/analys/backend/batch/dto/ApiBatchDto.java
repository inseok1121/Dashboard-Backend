package com.coin.analys.backend.batch.dto;

import com.coin.analys.backend.batch.entity.ApiBatch;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiBatchDto{

    private Long batchId;
    private String method;
    private String header;
    private String url;
    private String param;
    private String body;

    private String schedule;
    private LocalDateTime lastStartTime;
    private LocalDateTime lastEndTime;

    public static ApiBatch toDto(ApiBatchDto dto){
        return ApiBatch.builder()
                .batchId(dto.getBatchId())
                .method(dto.getMethod())
                .header(dto.getHeader())
                .url(dto.getUrl())
                .param(dto.getParam())
                .body(dto.getBody())
                .schedule(dto.getSchedule())
                .lastStartTime(dto.getLastStartTime())
                .lastEndTime(dto.getLastEndTime())
                .build();
    }
}
