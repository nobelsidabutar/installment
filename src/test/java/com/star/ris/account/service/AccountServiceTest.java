package com.star.ris.account.service;

import com.star.ris.account.entity.Account;
import com.star.ris.account.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    @Test
    public void getAccountById (){
        String accountId = "account1";
        Account account = new Account();
        account.setId(accountId);
        Mockito.when(accountRepository.findByAccountId(accountId)).thenReturn(account);

        Account actual = accountService.getAccountById(accountId);

        Assertions.assertThat(actual.getId()).isEqualTo(accountId) ;
    }
}
