package com.app.banking.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustTxnReportReqDto {
    @NotBlank(message = " acctNo is Mandatory")
    private String acctNo;
    @NotBlank(message = " month is Mandatory")
    private String month;
    @NotBlank(message = " year is Mandatory")
    private String year;

}
