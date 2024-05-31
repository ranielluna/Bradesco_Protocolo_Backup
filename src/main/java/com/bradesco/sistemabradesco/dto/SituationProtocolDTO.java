package com.bradesco.sistemabradesco.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.models.Employee;
import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.models.SituationProtocol;


public class SituationProtocolDTO {

  private int code; // codigo
  private Employee employee; // funcionario
  private Protocol protocol; // protocolo
  private String protocolResponse; // respostaProtocolo
  private LocalDate receiptDate; // dataRecebimento
  private LocalDate lastActionDate; // dataUltimaAcao
  

  public SituationProtocolDTO(SituationProtocol situationProtocol){
    BeanUtils.copyProperties(situationProtocol, this);
  }

  public SituationProtocolDTO(){
  }
  public SituationProtocolDTO(int code, String protocolResponse){
    this.code = code;
    this.protocolResponse = protocolResponse;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Protocol getProtocol() {
    return protocol;
  }

  public void setProtocol(Protocol protocol) {
    this.protocol = protocol;
  }

  public String getProtocolResponse() {
    return protocolResponse;
  }

  public void setProtocolResponse(String protocolResponse) {
    this.protocolResponse = protocolResponse;
  }

  public LocalDate getReceiptDate() {
    return receiptDate;
  }

  public void setReceiptDate(LocalDate receiptDate) {
    this.receiptDate = receiptDate;
  }

  public LocalDate getLastActionDate() {
    return lastActionDate;
  }

  public void setLastActionDate(LocalDate lastActionDate) {
    this.lastActionDate = lastActionDate;
  }


  
}