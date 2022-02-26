package com.app.banking.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="account" ,schema = "bank_app")
@Data
public class Account {

    @Id
    @Column(name="account_no")
    private String accountNo;

    @Column(name="account_balance")
    private Double accountBalance;

    @Column(name="create_date")
    private java.sql.Date createDate;

    @Column(name="cust_id")
    private Integer custId;

  // @OneToOne(mappedBy = "account")
   //private Customer customer;
  @Column(name="phone_number")
  private Long phoneNumber;
}
