package com.app.banking.service;

import com.app.banking.dto.CustTxnReportReqDto;
import com.app.banking.dto.CustTxnReportRespDto;
import com.app.banking.repository.TransactionReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionReportService {
    @Autowired
    private TransactionReportRepository transactionReportRepository;
   public  List<CustTxnReportRespDto> getTxnReportByAcctNum(CustTxnReportReqDto custTxnReportReqDto, int start, int end){

       List<CustTxnReportRespDto> listCustTxnReport=new ArrayList<>();

       return listCustTxnReport;
    }
}
