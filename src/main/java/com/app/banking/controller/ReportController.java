package com.app.banking.controller;

import com.app.banking.dto.CustTxnReportReqDto;
import com.app.banking.dto.CustTxnReportRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ReportController {
    @PostMapping("/statements")
    public ResponseEntity<CustTxnReportRespDto> statements(@Valid @RequestBody CustTxnReportReqDto custTxnReportReqDto, @RequestParam int pageNumber, @RequestParam int pageSize){
        CustTxnReportRespDto custTxnReportRespDto= new CustTxnReportRespDto();
        return new ResponseEntity<CustTxnReportRespDto>(custTxnReportRespDto, HttpStatus.OK);
    }
}
