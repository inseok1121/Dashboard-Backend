package com.coin.analys.backend.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api/v1/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("name")
    public String getAccountName(){
        return "Inseok Song";
    }
}
