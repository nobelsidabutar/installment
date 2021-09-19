package com.star.ris.deposit.service;

import com.star.ris.deposit.entity.DepositUser;
import com.star.ris.deposit.repository.DepositUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DepositUserServiceTest {

    @InjectMocks
    DepositUserService depositUserService;

    @Mock
    DepositUserRepository depositUserRepository;

    private final String ERROR_DATA_DEPOSIT_NOT_FOUND= "Data deposit tidak ditemukan";
    private final String ERROR_DATA_DEPOSIT_NOT_FOUND_BY_ID= "Data deposit user %s tidak ditemukan";


    @Test
    public void getDepositAll_returnData_whenDataExist () {
        String expected = "SUCCESS";
        DepositUser depositUser = new DepositUser();
        depositUser.setAccountId("A");
        depositUser.setTenor(20);
        List<DepositUser> depositUserList = Collections.singletonList(depositUser);
        Mockito.when(depositUserRepository.getAllDepositUser()).thenReturn(depositUserList);

        String actual = depositUserService.getDepositAll();

        Assertions.assertThat(actual.contains(expected));
    }

    @Test
    public void getDepositAll_returnData_whenAccountIdExist () {
        String expected = "SUCCESS";
        String accountId = "account1";
        DepositUser depositUser = new DepositUser();
        depositUser.setAccountId(accountId);
        depositUser.setTenor(20);
        List<DepositUser> depositUserList = Collections.singletonList(depositUser);
        Mockito.when(depositUserRepository.getAllDepositUserByUserId(accountId)).thenReturn(depositUserList);

        String actual = depositUserService.getDepositByAccountId(accountId);

        Assertions.assertThat(actual.contains(expected));
    }

    @Test
    public void getDepositAll_returnData_whenDataNotFound () {
        DepositUser depositUser = new DepositUser();
        depositUser.setAccountId("A");
        depositUser.setTenor(20);
        Mockito.when(depositUserRepository.getAllDepositUser()).thenReturn(new ArrayList<>());

        String actual = depositUserService.getDepositAll();

        Assertions.assertThat(actual.contains(ERROR_DATA_DEPOSIT_NOT_FOUND));
    }

    @Test
    public void getDepositAll_returnData_whenAccountIdNotExist() {
        String accountId = "account1";
        Mockito.when(depositUserRepository.getAllDepositUserByUserId(accountId)).thenReturn(new ArrayList<>());

        String actual = depositUserService.getDepositByAccountId(accountId);

        Assertions.assertThat(actual.contains(ERROR_DATA_DEPOSIT_NOT_FOUND_BY_ID));
    }

}
