package com.star.ris.deposit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "Deposit")
@Table(name = "deposit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deposit{

    @Id
    @Column(name = "deposit_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name ="system-uuid", strategy = "uuid")
    private String id;

    private String accountId;

    private int tenor;

    private int tenorPaid;

    private BigDecimal firstAmount;

    private BigDecimal monthlyAmount;

    private String purpose;

    private String status;

    @Column(name = "created_by")
    private String createdBy;

    private Date createdDate;

    private String modifiedBy;

    private Date modifiedDate;


}
