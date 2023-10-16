package com.coin.analys.backend.batch.dto;


import com.coin.analys.backend.batch.entity.BatchHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class BatchHistoryDto {

    private Long historyId;
    private Long batchId;
    private String result;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String msg;

    public static BatchHistory toEntity(BatchHistoryDto dto){
        return BatchHistory.builder()
                .historyId(dto.getHistoryId())
                .batchId(dto.getBatchId())
                .result(dto.getResult())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .msg(dto.getMsg())
                .build();
    }
}
