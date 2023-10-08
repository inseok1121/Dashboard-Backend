package com.coin.analys.backend.batch.controller;

import com.coin.analys.backend.batch.dto.BatchDto;
import com.coin.analys.backend.batch.service.BatchService;
import com.coin.analys.backend.util.ApiResult;
import com.coin.analys.backend.util.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ApiResult<?> startBatch(@RequestParam("batchId") String batchId){

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
}
