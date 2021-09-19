package com.star.ris.deposit.service;

import com.star.ris.common.RisConstants;
import com.star.ris.common.dto.Response;
import com.star.ris.deposit.entity.DepositUser;
import com.star.ris.deposit.repository.DepositUserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class DepositUserService {

    @Autowired
    DepositUserRepository depositUserRepository;

    public String getDepositAll () {

        List<DepositUser> depositUsers = depositUserRepository.getAllDepositUser();
        String message = RisConstants.Status.SUCCESS_MESSAGE;
        String status =  RisConstants.Status.SUCCESS_CODE;
        String data = Strings.EMPTY;

        if(CollectionUtils.isEmpty(depositUsers)){
            message = RisConstants.Error.ERROR_DATA_DEPOSIT_NOT_FOUND;
        }

        data = depositUsers.toString();
        Response response = new Response(status, data, message);

        return response.toString();
    }

    public String getDepositByAccountId (String accountId) {

        DepositUser depositUser = depositUserRepository.getAllDepositUserByUserId(accountId);
        String message = RisConstants.Status.SUCCESS_MESSAGE;
        String status =  RisConstants.Status.SUCCESS_CODE;
        String data = Strings.EMPTY;

        if(Objects.isNull(depositUser) || Strings.isEmpty(depositUser.toString())){
            message = String.format(RisConstants.Error.ERROR_DATA_DEPOSIT_NOT_FOUND_BY_ID, accountId);
        }

        data = depositUser.toString();
        Response response = new Response(status, data, message);

        return response.toString();
    }
}
