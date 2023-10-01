package com.coin.analys.backend.account.controller;

import com.coin.analys.backend.account.vo.AccountVo;
import com.coin.analys.backend.util.ApiResult;
import com.coin.analys.backend.util.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/api/v1/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("name")
    public String getAccountName(){
        return "Inseok Song";
    }

    @PostMapping("login")
    public ApiResult<AccountVo> login(@RequestBody AccountVo accountVo){
        return ApiUtils.success(accountVo);
    }
}
