package com.bradesco.sistemabradesco.models;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.dto.SituationProtocolDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "situacaoProtocolo")
public class SituationProtocol implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_incremento
  @Column(name = "codigo")
  private int code;

  @ManyToOne
  @JoinColumn(name = "funcionario_codigo", referencedColumnName = "codigo")
  private Employee employee;

  @ManyToOne
  @JoinColumn(name = "protocolo_codigo", referencedColumnName = "codigo")
  private Protocol protocol;

  @Column(name = "resposta_protocolo", length = 2500)
  private String protocolResponse;

  @Column(name = "data_recebimento", nullable = false)
  private LocalDate receiptDate;

  @Column(name = "data_ultima_acao", nullable = false)
  private LocalDate lastActionDate;


  
  public SituationProtocol() {
  }

  public SituationProtocol(SituationProtocolDTO situacaoProtocoloDTO){
    BeanUtils.copyProperties(situacaoProtocoloDTO, this);
  }

  
  //GETTERS AND SETTERS
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
  

  //METODOS HASHCODE E EQUALS
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + code;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SituationProtocol other = (SituationProtocol) obj;
    if (code != other.code)
      return false;
    return true;
  }

  
  
}//class
