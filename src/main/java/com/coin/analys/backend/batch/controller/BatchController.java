package com.coin.analys.backend.batch.controller;

import com.coin.analys.backend.batch.dto.BatchDto;
import com.coin.analys.backend.batch.service.BatchService;
import com.coin.analys.backend.util.ApiResult;
import com.coin.analys.backend.util.ApiUtils;
import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/api/v1/batch")
public class BatchController {

    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);


    private final BatchService batchService;

    @Autowired
    public BatchController(BatchService batchService){
        this.batchService = batchService;
    }


    @GetMapping("start")
    public ApiResult<?> startBatch(@RequestParam("batchId") Long batchId){

        try{
            boolean result = batchService.startBatch(batchId);

            if(result){
                return ApiUtils.success("success");
            }else{
                return ApiUtils.error("fail", 401);
            }
        }catch(Exception e){
            return ApiUtils.error("fail + " + e.getMessage(), 401);
        }
    }
    @GetMapping("list")
    public ApiResult<List<BatchDto>> getBatchList(){
        return ApiUtils.success(batchService.getBatchList());
    }

    @PostMapping("register")
    public ApiResult<BatchDto> registerBatch(@RequestBody BatchDto batchDto){
        return ApiUtils.success(batchService.registerBatch(batchDto));
    }
    @GetMapping("search")
    public ApiResult<Page<BatchDto>> searchBatchList(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "target", required = false) String target,
            @RequestParam(value = "schedule", required = false) String schedule,
            @PageableDefault(size = 20, sort = "batchId", direction = Sort.Direction.ASC) Pageable pageable
            ){
        return ApiUtils.success(batchService.searchBatchList(type, target, schedule, pageable));
    }

    @DeleteMapping("delete")
    public ApiResult<?> deleteBatch(@RequestParam("batchId") Long batchId){
        return ApiUtils.success(batchService.deleteBatch(batchId));
    }

    @PutMapping("update")
    public ApiResult<BatchDto> updateBatch(@RequestBody BatchDto batchDto){
        return ApiUtils.success(batchService.updateBatch(batchDto));
    }

    @GetMapping("stop")
    public ApiResult<?> stopBatch(@RequestParam("batchId") Long batchId){
        try{
            boolean result = batchService.stopBatch(batchId);

            if(result){
                return ApiUtils.success("success");
            }else{
                return ApiUtils.error("fail", 401);
            }
        }catch(Exception e){
            return ApiUtils.error("fail + " + e.getMessage(), 401);
        }
    }

    @GetMapping("run")
    public ApiResult<?> runBatch(@RequestParam("batchId") Long batchId){
        try{
            boolean result = batchService.runBatch(batchId);

            if(result){
                return ApiUtils.success("success");
            }else{
                return ApiUtils.error("fail", 401);
            }
        }catch(Exception e){
            return ApiUtils.error("fail + " + e.getMessage(), 401);
        }
    }
}
