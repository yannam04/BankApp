package com.app.banking.service;

import com.app.banking.dto.CustTxnReportReqDto;
import com.app.banking.dto.CustTxnReportRespDto;
import com.app.banking.dto.CustomerDTO;
import com.app.banking.dto.FundTransferDTO;
import com.app.banking.entity.Account;

import java.util.List;

public interface BankService {
    String registerCustomer(CustomerDTO customerDTO);
    String transferFunds(FundTransferDTO fundTransferDTO);
    List<CustTxnReportRespDto> getTxnReportByAcctNum(CustTxnReportReqDto custTxnReportReqDto,int start,int end);
    Account getAccountDetailsByPhoneNumber(Long phoneNumber);
}
