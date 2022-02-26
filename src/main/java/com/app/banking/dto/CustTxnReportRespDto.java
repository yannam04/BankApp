package com.app.banking.dto;

import lombok.Data;

import java.util.Date;
@Data
public class CustTxnReportRespDto {
    private String acctNo;
    private Double amount;
    private String txnType;
    private Date txnDate;

}
