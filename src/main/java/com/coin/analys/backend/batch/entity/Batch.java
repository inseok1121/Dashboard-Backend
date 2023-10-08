package com.coin.analys.backend.batch.entity;

import com.coin.analys.backend.batch.dto.BatchDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

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
