package com.app.banking.repository;

import com.app.banking.entity.TransactionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionReportRepository extends JpaRepository<TransactionReport, Integer> {

    //select * from bank_app.acct_txn  where month(txn_date)=02 and  year(txn_date)=2022;
    @Query(nativeQuery = true,value = "select * from bank_app.acct_txn  where acc_no=:acctNum and month(txn_date)=:month and  year(txn_date)=:year")
    List<TransactionReport> getTxnDetailsByAcctNum(@Param("acctNum") String acctNum , @Param("month") Integer month, @Param("year") Integer year);
}
