package com.star.ris.deposit.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "DepositUser")
public class DepositUser {

    @Id
    @Column(name = "deposit_id")
    private String id;

    private String name;

    private String accountId;

    private int tenor;

    private int tenorPaid;

    private BigDecimal firstAmount;

    private BigDecimal monthlyAmount;

    private String purpose;

    private String status;

    private String createdBy;

    private Date createdDate;

    private String modifiedBy;

    private Date modifiedDate;
}
