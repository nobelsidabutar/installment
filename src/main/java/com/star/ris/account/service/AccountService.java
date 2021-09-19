package com.star.ris.account.service;

import com.star.ris.account.entity.Account;
import com.star.ris.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account getAccountById (String accountId){
        return accountRepository.findByAccountId(accountId);
    }
}
