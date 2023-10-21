package com.coin.analys.backend.batch.entity;

import com.coin.analys.backend.batch.dto.BatchDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;
    private String type;
    private String header;
    private String method;
    private String target;
    private String schedule;
    private String params;
    private LocalDateTime lastStartTime;
    private LocalDateTime lastEndTime;


    public static BatchDto toDto(Batch entity){
        return BatchDto.builder()
                .batchId(entity.getBatchId())
                .type(entity.getType())
                .header(entity.getHeader())
                .method(entity.getMethod())
                .schedule(entity.getSchedule())
                .target(entity.getTarget())
                .params(entity.getParams())
                .lastStartTime(entity.getLastStartTime())
                .lastEndTime(entity.getLastEndTime())
                .build();
    }
}
