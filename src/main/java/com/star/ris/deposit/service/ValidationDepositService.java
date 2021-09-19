package com.star.ris.deposit.service;

import com.star.ris.account.entity.Account;
import com.star.ris.account.service.AccountService;
import com.star.ris.common.RisConstants;
import com.star.ris.common.dto.Response;
import com.star.ris.util.ValidatorUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

import static com.star.ris.common.RisConstants.Field.*;

@Service
public class ValidationDepositService {

    @Autowired
    AccountService accountService;

    public  String validation(String tenorValue, String firstDepositAmountValue,
                              String monthlyDepositAmountValue){
        String isTenorValid =  ValidatorUtils.checkNumber(tenorValue, TENOR);
        String isFirstDepositAmountValid = ValidatorUtils.checkDecimal(firstDepositAmountValue, FIRST_DEPOSIT_AMOUNT);
        String isMonthlyDepositAmountValid = ValidatorUtils.checkDecimal(monthlyDepositAmountValue, MONTHLY_DEPOSIT_AMOUNT);
        Response response = new Response();
        response.setData(Strings.EMPTY);
        response.setStatus(RisConstants.Status.ERROR_MESSAGE);

        if(!isTenorValid.isEmpty()){
            response.setMessage(isTenorValid);
            return response.toString();
        }

        if(!isFirstDepositAmountValid.isEmpty()){
            response.setMessage(isFirstDepositAmountValid);
            return response.toString();
        }

        if(!isMonthlyDepositAmountValid.isEmpty()){
            response.setMessage(isMonthlyDepositAmountValid);
            return response.toString();
        }

        return Strings.EMPTY;
    }

    public  String validationCreate(String tenorValue, String firstDepositAmountValue,
                              String monthlyDepositAmountValue, String purpose, String requestBy, String accountId){
        String isTenorValid =  ValidatorUtils.checkNumber(tenorValue, TENOR);
        String isFirstDepositAmountValid = ValidatorUtils.checkDecimal(firstDepositAmountValue, FIRST_DEPOSIT_AMOUNT);
        String isMonthlyDepositAmountValid = ValidatorUtils.checkDecimal(monthlyDepositAmountValue, MONTHLY_DEPOSIT_AMOUNT);
        String isPurposeValid =  ValidatorUtils.checkString(purpose, PURPOSE);
        String isRequestByValid =  ValidatorUtils.checkString(requestBy, REQUEST_BY);

        Response response = new Response();
        response.setData(Strings.EMPTY);
        response.setStatus(RisConstants.Status.ERROR_MESSAGE);

        if(!isTenorValid.isEmpty()){
            response.setMessage(isTenorValid);
            return response.toString();
        }

        if(!isFirstDepositAmountValid.isEmpty()){
            response.setMessage(isFirstDepositAmountValid);
            return response.toString();
        }

        if(!isMonthlyDepositAmountValid.isEmpty()){
            response.setMessage(isMonthlyDepositAmountValid);
            return response.toString();
        }

        if(!isPurposeValid.isEmpty()){
            response.setMessage(isPurposeValid);
            return response.toString();
        }

        if(!isRequestByValid.isEmpty()){
            response.setMessage(isRequestByValid);
            return response.toString();
        }

        return checkAccountId( accountId);
    }

    private String checkAccountId(String accountId){
        String isAccountValid =  ValidatorUtils.checkString(accountId, ACCOUNT_ID);

        if(!isAccountValid.isEmpty()){
            return isAccountValid;
        }

        Account account = this.accountService.getAccountById(accountId);

        if(Objects.isNull(account)){
            return String.format(RisConstants.Error.ERROR_ACCOUNT_NOT_EXIST,accountId);
        }

        return Strings.EMPTY;
    }
}
