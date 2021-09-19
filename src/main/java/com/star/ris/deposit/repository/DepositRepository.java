package com.star.ris.deposit.repository;

import com.star.ris.deposit.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, String> {

}
