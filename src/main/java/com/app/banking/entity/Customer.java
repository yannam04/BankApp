package com.app.banking.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer", schema = "bank_app")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    @Column(name="gender")
    private String gender;
    @Column(name="age")
    private int age;
    @Column(name="dob")
    private Date dob;
    @Column(name="email")
    private String email;

    @Column(name = "ph_no")
    private String phoneNumber;

    @Column(name = "created_date")
    private java.sql.Date createdDate;

    /*@OneToOne(targetEntity=Account.class,cascade=CascadeType.ALL)
    @JoinColumn(name="account_no")
    private Account account;*/


    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "cust_id")
    @PrimaryKeyJoinColumn()
    private Account account;

}