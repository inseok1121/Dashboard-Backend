package com.coin.analys.backend.batch.entity;

import com.coin.analys.backend.batch.dto.BatchHistoryDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;
    private Long batchId;
    private String result;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String msg;

    public static BatchHistoryDto toDto(BatchHistory entity){
        return BatchHistoryDto.builder()
                .historyId(entity.getHistoryId())
                .batchId(entity.getBatchId())
                .result(entity.getResult())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .msg(entity.getMsg())
                .build();
    }
}
