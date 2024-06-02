package com.bradesco.sistemabradesco.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.models.BankAccount;

@Schema(description = "Dados para criar uma conta")
public class BankAccountDTO{
    private int code;
    
    @Schema(description = "Agência", example = "1245")
    private int agency;

    @Schema(description = "Número da conta", example = "7896542")
    private int accountNumber;

    @Schema(description = "Status da conta", example = "Ativa")
    private String accountStatus;

    @Schema(description = "Cliente", example = "{objeto Cliente}")
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