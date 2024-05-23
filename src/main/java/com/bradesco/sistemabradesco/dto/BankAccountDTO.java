package com.bradesco.sistemabradesco.dto;

import org.springframework.beans.BeanUtils;
import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.models.BankAccount;


public class BankAccountDTO{
    private int code;
    private int agency;
    private int accountNumber;
    private String accountStatus;
    private Client client;
    
    public BankAccountDTO() {
    }
    
    public BankAccountDTO(BankAccount bankAccount) {
      BeanUtils.copyProperties(bankAccount, this);
    }

    public int getCode() {
        return code;
      }
    
      public void setCode(int code) {
        this.code = code;
      }
    
      public int getAgency() {
        return agency;
      }
    
      public void setAgency(int agency) {
        this.agency = agency;
      }
    
      public int getAccountNumber() {
        return accountNumber;
      }
    
      public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
      }
    
      public String getAccountStatus() {
        return accountStatus;
      }
    
      public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
      }
    
      public Client getClient() {
        return client;
      }
    
      public void setClient(Client client) {
        this.client = client;
      }
    
}