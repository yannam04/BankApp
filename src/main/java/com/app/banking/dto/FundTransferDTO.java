package com.app.banking.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FundTransferDTO {
    @NotBlank(message = "fromAcct(Sender Account) is Mandatory")
    private String fromAcct;
    @NotBlank(message = "toAccount(Recipient Account) is Mandatory")
    private String toAccount;
    private String comments;
    @NotNull(message = "Amount is Mandatory")
    private Double amount;
}

