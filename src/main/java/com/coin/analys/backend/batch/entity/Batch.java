package com.coin.analys.backend.batch.entity;

import com.coin.analys.backend.batch.dto.BatchDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;
    private String schedule;
    private String url;
    private String params;

    public BatchDto toDto(){
        BatchDto batchDto = new BatchDto();
        batchDto.setBatchId(this.batchId);
        batchDto.setSchedule(this.schedule);
        batchDto.setUrl(this.url);
        batchDto.setParams(this.params);

        return batchDto;
    }
}
