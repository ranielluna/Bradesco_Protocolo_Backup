package com.bradesco.sistemabradesco.models;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.dto.BankAccountDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta")
public class BankAccount implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo")
  private int code;

  @Column(name = "agencia", length = 4, nullable = false)
  private int agency; // 4 digitos

  @Column(name = "numero_conta", nullable = false)
  private int accountNumber;

  @Column(name = "status_conta", length = 10, nullable = false)
  private String accountStatus;

  @ManyToOne
  @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
  private Client client;


  public BankAccount() {
  }

  public BankAccount (BankAccountDTO bankAccountDTO){
     BeanUtils.copyProperties(bankAccountDTO, this);
  }

  //GETTERS AND SETTERS
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


  //METODOS HASHCODE E EQUALS
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + code;
    return result;
  }// Este método é responsável por calcular o código de hash do objeto.

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BankAccount other = (BankAccount) obj;
    if (code != other.code)
      return false;
    return true;
  }// Este método verifica se dois objetos são iguais



  
}//class
