package com.coin.analys.backend.batch.controller;

import com.coin.analys.backend.batch.dto.ApiBatchDto;
import com.coin.analys.backend.batch.service.ApiBatchService;
import com.coin.analys.backend.util.ApiResult;
import com.coin.analys.backend.util.ApiUtils;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("/api/v1/batch/api")
public class ApiBatchController {

    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

    private final ApiBatchService apiBatchService;

    @GetMapping("start")
    public ApiResult<?> startBatch(@RequestParam("batchId") Long batchId){

        try{
            boolean result = apiBatchService.startBatch(batchId);

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
    public ApiResult<List<ApiBatchDto>> getBatchList(){
        return ApiUtils.success(apiBatchService.getBatchList());
    }

    @PostMapping("register")
    public ApiResult<ApiBatchDto> registerBatch(@RequestBody ApiBatchDto apiBatchDto){
        return ApiUtils.success(apiBatchService.registerBatch(apiBatchDto));
    }
    @GetMapping("search")
    public ApiResult<Page<ApiBatchDto>> searchBatchList(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "target", required = false) String target,
            @RequestParam(value = "schedule", required = false) String schedule,
            @PageableDefault(size = 20, sort = "batchId", direction = Sort.Direction.ASC) Pageable pageable
    ){
        return ApiUtils.success(apiBatchService.searchBatchList(type, target, schedule, pageable));
    }

    @DeleteMapping("delete")
    public ApiResult<?> deleteBatch(@RequestParam("batchId") Long batchId){
        return ApiUtils.success(apiBatchService.deleteBatch(batchId));
    }

    @PutMapping("update")
    public ApiResult<ApiBatchDto> updateBatch(@RequestBody ApiBatchDto apiBatchDto){
        return ApiUtils.success(apiBatchService.updateBatch(apiBatchDto));
    }

    @GetMapping("stop")
    public ApiResult<?> stopBatch(@RequestParam("batchId") Long batchId){
        try{
            boolean result = apiBatchService.stopBatch(batchId);

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
            boolean result = apiBatchService.runBatch(batchId);

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
