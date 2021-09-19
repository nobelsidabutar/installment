package com.star.ris.deposit.service;

import static org.mockito.Mockito.when;

import com.star.ris.deposit.entity.Deposit;
import com.star.ris.deposit.repository.DepositRepository;
import org.apache.logging.log4j.util.Strings;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class DepositServiceTest {

    @InjectMocks
    DepositService depositService;

    @Mock
    ValidationDepositService validationDepositService;

    @Mock
    DepositRepository depositRepository;

    private static final String TENOR = "12";
    private static final String MONTHLY_DEPOSIT_AMOUNT_VALUE = "2000000.00";
    private static final String FIRST_DEPOSIT_AMOUNT_VALUE = "12000000";
    private static final String PURPOSE_EDUCATION = "Education";
    private static final String ACCOUNT_STAFF = "Admin1";
    private static final String ACCOUNT_BANK = "account1";

    @Test
    public void calculateDeposit_returnStringError_whenAllDataTenorNotValid(){
        String tenorNotValid = "AA";
        String expected = "Error,Field Tenor harus angka";
        when(validationDepositService.validation(tenorNotValid, FIRST_DEPOSIT_AMOUNT_VALUE, MONTHLY_DEPOSIT_AMOUNT_VALUE ))
                .thenReturn(expected);

        String actual = depositService.calculateEstimatedDeposit(tenorNotValid, FIRST_DEPOSIT_AMOUNT_VALUE, MONTHLY_DEPOSIT_AMOUNT_VALUE ,PURPOSE_EDUCATION);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void calculateDeposit_returnStringError_whenAllDataFirstDepositAmountNotValid(){
        String firstDepositAmount = "AA";
        String expected = "Error,Field First Deposit Amount harus angka atau decimal";
        when(validationDepositService.validation(TENOR, firstDepositAmount, MONTHLY_DEPOSIT_AMOUNT_VALUE ))
                .thenReturn(expected);

        String actual = depositService.calculateEstimatedDeposit(TENOR, firstDepositAmount, MONTHLY_DEPOSIT_AMOUNT_VALUE ,PURPOSE_EDUCATION);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void calculateDeposit_returnStringError_whenAllDataMonthlyDepositAmountNotValid(){
        String monthlyDepositAmount = "AA";
        String expected = "Error,Monthly Deposit Amount harus angka atau decimal";
        when(validationDepositService.validation(TENOR, FIRST_DEPOSIT_AMOUNT_VALUE, monthlyDepositAmount ))
                .thenReturn(expected);

        String actual = depositService.calculateEstimatedDeposit(TENOR, FIRST_DEPOSIT_AMOUNT_VALUE, monthlyDepositAmount ,PURPOSE_EDUCATION);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void calculateDeposit_returnTotalBalance_whenAllDataValid(){
        String expected = "36040000.00";
        when(validationDepositService.validation(TENOR, FIRST_DEPOSIT_AMOUNT_VALUE, MONTHLY_DEPOSIT_AMOUNT_VALUE ))
                .thenReturn(Strings.EMPTY);

        String actual = depositService.calculateEstimatedDeposit(TENOR, FIRST_DEPOSIT_AMOUNT_VALUE, MONTHLY_DEPOSIT_AMOUNT_VALUE ,PURPOSE_EDUCATION);

        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void calculateDepositOne_returnTotalBalance_whenAllDataValid(){
        String expected = "25440000.00";

        String actual = depositService.calculateEstimated(TENOR, MONTHLY_DEPOSIT_AMOUNT_VALUE );

        Assertions.assertThat(actual).isEqualTo(expected);

    }


    @Test
    public void save_returnMessageSuccess_whenAllDataValid(){
        String expected = "SUCCESS";
        when(validationDepositService.validationCreate(TENOR, FIRST_DEPOSIT_AMOUNT_VALUE,
                MONTHLY_DEPOSIT_AMOUNT_VALUE,PURPOSE_EDUCATION, ACCOUNT_STAFF, ACCOUNT_BANK)).thenReturn(expected);

        String actual = depositService.createDeposit(TENOR, FIRST_DEPOSIT_AMOUNT_VALUE,
                MONTHLY_DEPOSIT_AMOUNT_VALUE,PURPOSE_EDUCATION, ACCOUNT_STAFF, ACCOUNT_BANK);

        Assertions.assertThat(actual.contains("SUCCESS"));

    }

    @Test
    public void createDeposit_returnMessageSuccess_whenTenorEmpty(){
        String expected = "Error,Field Tenor tidak boleh kosong";
        when(validationDepositService.validationCreate("", FIRST_DEPOSIT_AMOUNT_VALUE,
                MONTHLY_DEPOSIT_AMOUNT_VALUE,PURPOSE_EDUCATION, ACCOUNT_STAFF, ACCOUNT_BANK)).thenReturn(expected);

        String actual = depositService.createDeposit("", FIRST_DEPOSIT_AMOUNT_VALUE,
                MONTHLY_DEPOSIT_AMOUNT_VALUE,PURPOSE_EDUCATION, ACCOUNT_STAFF, ACCOUNT_BANK);

        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void createDeposit_returnMessageSuccess_whenPurposeEmpty(){
        String expected = "Error,Field Purpose tidak boleh kosong";
        when(validationDepositService.validationCreate(TENOR, FIRST_DEPOSIT_AMOUNT_VALUE,
                MONTHLY_DEPOSIT_AMOUNT_VALUE,"", ACCOUNT_STAFF, ACCOUNT_BANK)).thenReturn(expected);

        String actual = depositService.createDeposit(TENOR, FIRST_DEPOSIT_AMOUNT_VALUE,
                MONTHLY_DEPOSIT_AMOUNT_VALUE,"", ACCOUNT_STAFF, ACCOUNT_BANK);

        Assertions.assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void createDeposit_returnMessageSuccess_wheAccountNotFound(){
        String expected = "Error,Akun Admin1 tidak terdaftar";
        when(validationDepositService.validationCreate(TENOR, FIRST_DEPOSIT_AMOUNT_VALUE,
                MONTHLY_DEPOSIT_AMOUNT_VALUE,PURPOSE_EDUCATION, ACCOUNT_STAFF, "z")).thenReturn(expected);

        String actual = depositService.createDeposit(TENOR, FIRST_DEPOSIT_AMOUNT_VALUE,
                MONTHLY_DEPOSIT_AMOUNT_VALUE,PURPOSE_EDUCATION, ACCOUNT_STAFF, "z");

        Assertions.assertThat(actual).isEqualTo(expected);

    }

    private Deposit createdDeposit(){
        BigDecimal monthlyAmount = new BigDecimal(MONTHLY_DEPOSIT_AMOUNT_VALUE);
        BigDecimal firstAmount = new BigDecimal(FIRST_DEPOSIT_AMOUNT_VALUE);

        Deposit deposit = new Deposit();
        deposit.setStatus("1");
        deposit.setPurpose(PURPOSE_EDUCATION);
        deposit.setMonthlyAmount(monthlyAmount);
        deposit.setCreatedBy(ACCOUNT_STAFF);
        deposit.setFirstAmount(firstAmount);
        deposit.setCreatedDate(new Date());
        deposit.setTenor(Integer.parseInt(TENOR));
        deposit.setTenorPaid(11);
        deposit.setAccountId(ACCOUNT_BANK);

        return  deposit;
    }
}
