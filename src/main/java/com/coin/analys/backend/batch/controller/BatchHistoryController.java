package com.coin.analys.backend.batch.controller;

import com.coin.analys.backend.batch.dto.BatchHistoryDto;
import com.coin.analys.backend.batch.service.BatchHistoryService;
import com.coin.analys.backend.util.ApiResult;
import com.coin.analys.backend.util.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api/v1/history")
public class BatchHistoryController {

    private static final Logger logger = LoggerFactory.getLogger(BatchHistoryController.class);

    private final BatchHistoryService batchHistoryService;

    @Autowired
    public BatchHistoryController(BatchHistoryService batchHistoryService){
        this.batchHistoryService = batchHistoryService;
    }

    @GetMapping("list")
    public ApiResult<Page<BatchHistoryDto>> getBatchHistoryList(Pageable pageable){
        return ApiUtils.success(batchHistoryService.getBatchHistoryList(pageable));
    }
}
