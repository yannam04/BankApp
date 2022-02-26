package com.app.banking.controller;

import com.app.banking.dto.CustTxnReportReqDto;
import com.app.banking.dto.CustTxnReportRespDto;
import com.app.banking.dto.CustomerDTO;
import com.app.banking.dto.FundTransferDTO;
import com.app.banking.entity.Account;
import com.app.banking.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@Validated
public class BankServicesController {

    @Autowired
    private BankService bankService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        String acctNo=bankService.registerCustomer(customerDTO);
        return new ResponseEntity<String>(acctNo,HttpStatus.OK);
    }
    @PostMapping("/fundTransfer")
    public ResponseEntity<String> fundTransfer(@Valid @RequestBody FundTransferDTO fundTransferDTO){
        String fundTransferRes=bankService.transferFunds(fundTransferDTO);
        return new ResponseEntity<String>(fundTransferRes,HttpStatus.OK);
    }
    @GetMapping("getAccountByPhoneNumber/{phoneNumber}")
    public String getAccountDetailsByPhoneNumber(@PathVariable Long phoneNumber){
        Account account = bankService.getAccountDetailsByPhoneNumber(phoneNumber);
        return account.getAccountNo();
    }

}
