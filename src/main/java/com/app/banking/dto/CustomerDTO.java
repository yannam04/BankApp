package com.app.banking.dto;


import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CustomerDTO {
    @NotBlank(message = "FirstName is Mandatory")
    private String firstName;
    private String lastName;
    private String gender;
    @NotNull(message = "Age is Mandatory and must be more than 18 years")
    @Min(value = 18)
    private int age;
    private String dob;
    @NotBlank(message = "email is Mandatory and it must be unique")
    private String email;
    @NotBlank(message = "phone number is Mandatory")
    private String phoneNumber;
    @NotNull(message = "10k deposit amount is required for opening Account")
    @Min(value = 10000)
    private Double depositAmt;
}
