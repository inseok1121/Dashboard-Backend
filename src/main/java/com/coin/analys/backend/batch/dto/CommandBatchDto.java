package com.coin.analys.backend.batch.dto;

import com.coin.analys.backend.batch.entity.CommandBatch;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommandBatchDto{

    private Long batchId;
    private String command;

    private String schedule;
    private LocalDateTime lastStartTime;
    private LocalDateTime lastEndTime;

    public static CommandBatch toEntity(CommandBatchDto dto){
        return CommandBatch.builder()
                .batchId(dto.getBatchId())
                .command(dto.getCommand())
                .schedule(dto.getSchedule())
                .lastStartTime(dto.getLastStartTime())
                .lastEndTime(dto.getLastEndTime())
                .build();
    }
}
