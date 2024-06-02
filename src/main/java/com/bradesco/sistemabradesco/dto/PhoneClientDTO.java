package com.bradesco.sistemabradesco.dto;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.models.PhoneClient;
import com.bradesco.sistemabradesco.models.PhoneType;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para criar um telefone para um cliente")
public class PhoneClientDTO {

  private int code;

  @Schema(description = "DDD fo telefone", example = "081")
  private int ddd;

  @Schema(description = "Número do telefone", example = "988743183")
  private int number;

  @Schema(description = "Cliente", example = "{objeto Cliente}")
  private Client client;

  @Schema(description = "Tipo do telefone", example = "{objeto tipoTelefone}")
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