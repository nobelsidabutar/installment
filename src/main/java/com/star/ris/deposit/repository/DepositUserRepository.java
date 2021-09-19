package com.star.ris.deposit.repository;

import com.star.ris.deposit.entity.DepositUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface DepositUserRepository extends JpaRepository<DepositUser, String> {

    @Query(nativeQuery = true, value = "SELECT a.name, d.deposit_id,  d.account_id,  d.tenor," +
            "    d.first_amount,  d.monthly_amount,  d.purpose,  d.tenor_paid," +
            "    d.created_by,  d.created_date, d.modified_by,  d.modified_date," +
            " case when(d.status = '1') then 'Active' " +
            "  when (d.status = '2') then 'Inactive' else 'Done' end as status "+
            " from deposit d " +
            " left join account a " +
            "on a.account_id = d.account_id" )
    List<DepositUser> getAllDepositUser();

    @Query(nativeQuery = true, value = "SELECT a.name, d.deposit_id,  d.account_id, d.tenor," +
            "    d.first_amount,  d.monthly_amount,  d.purpose, d.status,  d.tenor_paid," +
            "    d.created_by,  d.created_date, d.modified_by,  d.modified_date," +
            " case when(d.status = '1') then 'Active' " +
            "  when (d.status = '2') then 'Inactive' else 'Done' end as status "+
            " from deposit d " +
            " left join account a " +
            " on a.account_id = d.account_id" +
            " where d.account_id = :accountId " )
    DepositUser getAllDepositUserByUserId(@RequestParam(name = "accountId") String accountId);

}
