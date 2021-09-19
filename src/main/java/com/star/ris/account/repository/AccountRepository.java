package com.star.ris.account.repository;

import com.star.ris.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(nativeQuery = true, value = "Select * from account where ACCOUNT_ID = :id")
    Account findByAccountId (@Param("id") String id);
}
