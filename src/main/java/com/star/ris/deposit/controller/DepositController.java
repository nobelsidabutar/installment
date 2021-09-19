package com.star.ris.deposit.controller;

import com.star.ris.deposit.service.DepositService;
import com.star.ris.deposit.service.DepositUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    DepositService depositService;

    @Autowired
    DepositUserService depositUserService;

    @GetMapping(value = "/estimated")
    public String calculationDeposit(
            @RequestParam("tenor") String tenor,
            @RequestParam("firstDepositAmount") String firstDepositAmount,
            @RequestParam("monthlyDepositAmount") String monthlyDepositAmount,
            @RequestParam("purpose") String purpose
            ){
        return depositService.calculateEstimatedDeposit(tenor,firstDepositAmount,monthlyDepositAmount,purpose);
    }

    @GetMapping(value = "/calculation")
    public String calculation(
            @RequestParam("tenor") String tenor,
            @RequestParam("monthlyDepositAmount") String monthlyDepositAmount){

        return depositService.calculateEstimated(tenor,monthlyDepositAmount);
    }

    @PostMapping(value = "")
    public String createAccount(
            @RequestParam("tenor") String tenor,
            @RequestParam("firstDepositAmount") String firstDepositAmount,
            @RequestParam("monthlyDepositAmount") String monthlyDepositAmount,
            @RequestParam("purpose") String purpose,
            @RequestParam("requestBy") String requestBy,
            @RequestParam("accountId") String accountId){

        return depositService.createDeposit(tenor,firstDepositAmount,
                monthlyDepositAmount,purpose,requestBy,accountId);
    }

    @GetMapping(value = "")
    public String findAll(){
        return depositUserService.getDepositAll();
    }

    @GetMapping(value = "/{id}")
    public String findByAccountId(@PathVariable("id") String id){
        return depositUserService.getDepositByAccountId(id);
    }

}
