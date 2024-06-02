package com.bradesco.sistemabradesco.dto;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.models.PhoneClient;
import com.bradesco.sistemabradesco.models.PhoneType;

public class PhoneClientDTO {

  private int code;
  private int ddd;
  private int number;
  private Client client;
  private PhoneType phoneType;

  public PhoneClientDTO(PhoneClient telefoneCliente){
    BeanUtils.copyProperties(telefoneCliente, this);
  }

  public PhoneClientDTO() {
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public int getDdd() {
    return ddd;
  }

  public void setDdd(int ddd) {
    this.ddd = ddd;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public PhoneType getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(PhoneType phoneType) {
    this.phoneType = phoneType;
  }

  
}