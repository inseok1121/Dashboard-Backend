package com.coin.analys.backend.batch.entity;

import com.coin.analys.backend.batch.dto.CommandBatchDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CommandBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;
    private String command;

    private String schedule;
    private LocalDateTime lastStartTime;
    private LocalDateTime lastEndTime;

    public static CommandBatchDto toDto(CommandBatch entity){
        return CommandBatchDto.builder()
                .batchId(entity.getBatchId())
                .command(entity.getCommand())
                .schedule(entity.getSchedule())
                .lastStartTime(entity.getLastStartTime())
                .lastEndTime(entity.getLastEndTime())
                .build();
    }
}
