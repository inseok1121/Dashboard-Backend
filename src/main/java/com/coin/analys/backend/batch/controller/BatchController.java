package com.coin.analys.backend.batch.controller;

import com.coin.analys.backend.batch.service.BatchService;
import com.coin.analys.backend.util.ApiResult;
import com.coin.analys.backend.util.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api/v1/batch")
public class BatchController {

    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    private BatchService batchService;

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
}
