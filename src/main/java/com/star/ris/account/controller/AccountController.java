package com.star.ris.account.controller;

import com.star.ris.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = "")
    public String getDataAccount(@RequestParam(name = "accountId") String accountId){
        return accountService.getAccountById(accountId).toString();
    }
}
