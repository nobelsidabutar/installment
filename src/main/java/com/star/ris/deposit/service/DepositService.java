package com.star.ris.deposit.service;

import static com.star.ris.common.RisConstants.Status.*;

import com.star.ris.common.RisConstants;
import com.star.ris.common.dto.Response;
import com.star.ris.deposit.entity.Deposit;
import com.star.ris.deposit.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@Service
public class DepositService {

    @Autowired
    ValidationDepositService validationDepositService;

    @Autowired
    DepositRepository depositRepository;

    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    public String calculateEstimatedDeposit(String tenorValue, String firstDepositAmountValue,
                                            String monthlyDepositAmountValue, String purposeValue) {


        String validationMessage = validationDepositService.validation(tenorValue, firstDepositAmountValue,
                monthlyDepositAmountValue);
        if (!validationMessage.isEmpty()) {
            return validationMessage;
        }

        int tenor = Integer.parseInt(tenorValue);
        BigDecimal firstDepositAmount = new BigDecimal(firstDepositAmountValue);
        BigDecimal monthlyDepositAmount = new BigDecimal(monthlyDepositAmountValue);

        int tenorInYear = tenor / RisConstants.TOTAL_MONTH_ONE_YEAR;
        BigDecimal balanceInYear = BigDecimal.valueOf(tenorInYear * RisConstants.BANK_INTEREST_ONE_YEAR);
        BigDecimal totalDeposit = (monthlyDepositAmount.multiply(BigDecimal.valueOf(tenor - 1))).add(firstDepositAmount);
        BigDecimal interestDeposit = balanceInYear.multiply(totalDeposit);
        BigDecimal totalBalance = interestDeposit.add(totalDeposit);

        return decimalFormat.format(totalBalance);
    }

    public String calculateEstimated(String tenorValue, String monthlyDepositAmountValue) {
        int tenor = Integer.parseInt(tenorValue);
        BigDecimal monthlyDepositAmount = new BigDecimal(monthlyDepositAmountValue);

        int tenorInYear = tenor / RisConstants.TOTAL_MONTH_ONE_YEAR;
        BigDecimal balanceInYear = BigDecimal.valueOf(tenorInYear * RisConstants.BANK_INTEREST_ONE_YEAR);
        BigDecimal totalDeposit = (monthlyDepositAmount.multiply(BigDecimal.valueOf(tenor)));
        BigDecimal interestDeposit = balanceInYear.multiply(totalDeposit);
        BigDecimal totalBalance = interestDeposit.add(totalDeposit);

        return decimalFormat.format(totalBalance);
    }

    public String createDeposit(
            String tenorValue, String firstDepositAmountValue, String monthlyDepositAmountValue,
            String purposeValue, String requestBy, String accountId) {

        String validationMessage = validationDepositService.validationCreate(tenorValue,
                firstDepositAmountValue, monthlyDepositAmountValue, purposeValue, requestBy, accountId);
        if (!validationMessage.isEmpty()) {
            return validationMessage;
        }
        Deposit deposit = getDeposit(tenorValue, firstDepositAmountValue,
                monthlyDepositAmountValue, purposeValue, requestBy, accountId);

        Deposit newDeposit = depositRepository.save(deposit);

        Response response = new Response(SUCCESS_CODE,newDeposit.toString(), SUCCESS_MESSAGE );

        return response.toString();
    }

    private Deposit getDeposit(String tenorValue, String firstDepositAmountValue, String monthlyDepositAmountValue, String purposeValue, String requestBy, String accountId) {
        int tenor = Integer.parseInt(tenorValue);
        BigDecimal firstDepositAmount = new BigDecimal(firstDepositAmountValue);
        BigDecimal monthlyDepositAmount = new BigDecimal(monthlyDepositAmountValue);

        Deposit deposit = new Deposit();
        deposit.setCreatedBy(requestBy);
        deposit.setCreatedDate(new Date());
        deposit.setFirstAmount(firstDepositAmount);
        deposit.setMonthlyAmount(monthlyDepositAmount);
        deposit.setTenor(tenor);
        deposit.setAccountId(accountId);
        deposit.setPurpose(purposeValue);
        deposit.setTenorPaid(1);
        deposit.setStatus(RisConstants.Status.ACTIVE);

        return deposit;
    }
}
