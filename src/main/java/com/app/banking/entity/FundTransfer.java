package com.app.banking.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "acct_txn", schema = "bank_app")
@Data
public class FundTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="txn_id")
    private Integer txnId;

    @Column(name = "acc_no")
    private String accNo;

    @Column(name = "amount")
    private Double amount;

    @Column(name="comments")
    private String comments;

    @Column(name="txn_type")
    private String txnType;

    @Column(name = "txn_date")
    private java.sql.Date datetime;
}
