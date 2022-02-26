package com.app.banking.repository;

import com.app.banking.entity.Account;
import com.app.banking.entity.FundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundTransferRepository extends JpaRepository<FundTransfer, Integer> {
}
