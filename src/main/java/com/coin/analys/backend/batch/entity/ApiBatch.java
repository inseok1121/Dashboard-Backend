package com.coin.analys.backend.batch.entity;

import com.coin.analys.backend.batch.dto.ApiBatchDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class ApiBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;
    private String method;
    private String header;
    private String url;
    private String param;
    private String body;

    private String schedule;
    private LocalDateTime lastStartTime;
    private LocalDateTime lastEndTime;

    public static ApiBatchDto toDto(ApiBatch entity){
        return ApiBatchDto.builder()
                .batchId(entity.getBatchId())
                .method(entity.getMethod())
                .header(entity.getHeader())
                .url(entity.getUrl())
                .param(entity.getParam())
                .body(entity.getBody())
                .schedule(entity.getSchedule())
                .lastStartTime(entity.getLastStartTime())
                .lastEndTime(entity.getLastEndTime())
                .build();
    }

}
