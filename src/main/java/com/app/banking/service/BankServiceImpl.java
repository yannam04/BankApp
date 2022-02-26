package com.app.banking.service;

import com.app.banking.dto.CustTxnReportReqDto;
import com.app.banking.dto.CustTxnReportRespDto;
import com.app.banking.dto.CustomerDTO;
import com.app.banking.dto.FundTransferDTO;
import com.app.banking.entity.Account;
import com.app.banking.entity.Customer;
import com.app.banking.entity.FundTransfer;
import com.app.banking.repository.AccountRepository;
import com.app.banking.repository.CustomerRepository;
import com.app.banking.repository.FundTransferRepository;
import com.app.banking.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BankServiceImpl implements BankService{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private FundTransferRepository fundTransferRepository;
    //Declare txnType as constant
    public  static  final String TXN_TYPE_DEBIT="Dbt";
    public  static  final String TXN_TYPE_Credit="Crdt";

    @Override
    public String registerCustomer(CustomerDTO customerDTO) {
        //Converting DTO to Entity
        //BeanUtils.copyProperties(user, userResponseDto);
        Customer customer =  new Customer();//buildCustomerEntity(customerDTO);
        //Save operation
        BeanUtils.copyProperties(customerDTO, customer);
        //BeanUtils.copyProperties(customerDTO, customer,customer.setDob(Utils.convertDate(customerDTO.getDob())));
        customer  = customerRepository.save(customer);
         Account account=buildAccountEntity(customer,customerDTO);
         //saving the one-to-one mapping inserting account table based on customer id
        account=accountRepository.save(account);
        return account.getAccountNo();
    }

    @Override
    public String transferFunds(FundTransferDTO fundTransferDTO) {
        //Step1: checking funds available in fromAccount(Sender Account)
        String accNo=fundTransferDTO.getFromAcct();
        Double amount=fundTransferDTO.getAmount();
        //checking the fromAccount exists or not
       Optional<Account> fromAcct= accountRepository.findById(accNo);
       //Checking the fromAccount is exists or not
       if(!fromAcct.isPresent()){
           return "FromAccount is not exist . Please contact with Bank";
       }
        String toAcct=fundTransferDTO.getToAccount();
        // Step2: Checking the toAccount is exists or not
        Optional<Account> toAccount= accountRepository.findById(toAcct);
        if(!toAccount.isPresent()) {
            return "toAccount is not exist . Cannot proceed Fund transfer";
        }
        // Fetching fromAccount info
           Account acct=fromAcct.get();
           //get amount from fromAccount
           Double fromAcctBalance=acct.getAccountBalance();
           // Step3:Balance check logic
           if(amount>fromAcctBalance){
               return "Insufficient funds in fromAccount";
           }
         //Step4: transferring funds
           Double fromAcctUpdatedBalance=fromAcctBalance-amount;
           acct.setAccountBalance(fromAcctUpdatedBalance);
         //updating fromAccount balance after debit
           accountRepository.save(acct);
           //Fetching  toAccount info
          Account toAcctCheck=toAccount.get();
          toAcctCheck.setAccountBalance(toAcctCheck.getAccountBalance()+amount);
           //updating toAccount balance
           accountRepository.save(toAcctCheck);
           //fromAccount update to txn table.
           fundTransferRepository.save(buildFundTransfer(accNo,TXN_TYPE_DEBIT,fundTransferDTO));
           //ToAccount update to txn table
          fundTransferRepository.save(buildFundTransfer(toAcct,TXN_TYPE_Credit,fundTransferDTO));
        return "Fund Transferred successfully";
       }

    @Override
    public List<CustTxnReportRespDto> getTxnReportByAcctNum(CustTxnReportReqDto custTxnReportReqDto, int start, int end) {
        return null;
    }

    @Override
    public Account getAccountDetailsByPhoneNumber(Long phoneNumber) {
        return accountRepository.findByPhoneNumber(phoneNumber);
    }

    private Customer buildCustomerEntity(CustomerDTO dto){

        java.sql.Date now = new java.sql.Date(System.currentTimeMillis());

         Customer customer = new Customer();
         customer.setFirstName(dto.getFirstName());
         customer.setLastName(dto.getLastName());
         customer.setAge(dto.getAge());
         customer.setPhoneNumber(dto.getPhoneNumber());
         //using Beanutilis
         //BeanUtils.copyProperties(user, userResponseDto);
         //setting dob
         String dob = dto.getDob();
         if(StringUtils.hasText(dob)){
             SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy"); // New Pattern E: 01-Feb-2013
             java.util.Date date; // Returns a Date format object with the pattern
             try {
                 date = sdf1.parse(dob);
                 java.sql.Date sqlDOb = new java.sql.Date(date.getTime());
                 customer.setDob(sqlDOb);
             } catch (ParseException e) {
                 System.err.println("Exception while converting dob.."+e.getMessage());
             }

         }
        //customer.setDob();
         customer.setGender(dto.getGender());
         customer.setEmail(dto.getEmail());
         customer.setCreatedDate(now);

        return customer;
     }
     // Generate Account Data
     private Account buildAccountEntity(Customer custObj,CustomerDTO dto){
         java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
        Account account=new Account();
         //creating account number
         String accNo = generateAcno();
         account.setAccountNo(accNo);
         account.setAccountBalance(dto.getDepositAmt());
         account.setCreateDate(now);
         account.setCustId(custObj.getId());
         //account.setCustomer(custObj);
       return account;
     }
    public static String generateAcno() {
        // It will generate 7 digit random Number.
        // from 0 to 9999999
        Random rnd = new Random();
        int number = rnd.nextInt(9999999);
        // this will convert any number sequence into 7 character.
        return String.format("%07d", number);
    }

    private FundTransfer buildFundTransfer(String accNum,String txnType,FundTransferDTO fundTransferDTO){
        java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
        FundTransfer fundTransfer=new FundTransfer();
        fundTransfer.setAmount(fundTransferDTO.getAmount());
        fundTransfer.setAccNo(accNum);
        fundTransfer.setTxnType(txnType);
        fundTransfer.setComments(fundTransferDTO.getComments());
        fundTransfer.setDatetime(now);
        return fundTransfer;
    }
}
