package com.bradesco.sistemabradesco.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.models.Employee;
import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.models.SituacaoProtocolo;


public class SituacaoProtocoloDTO {

  private int codigo;
  private Employee funcionario;
  private Protocol protocolo;
  private String respostaProtocolo;
  private LocalDate dataRecebimento;
  private LocalDate dataUltimaAcao;

  public SituacaoProtocoloDTO(SituacaoProtocolo situacaoProtocolo){
    BeanUtils.copyProperties(situacaoProtocolo, this);
  }

  public SituacaoProtocoloDTO(){
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public Employee getFuncionario() {
    return funcionario;
  }

  public void setFuncionario(Employee funcionario) {
    this.funcionario = funcionario;
  }

  public Protocol getProtocolo() {
    return protocolo;
  }

  public void setProtocolo(Protocol protocolo) {
    this.protocolo = protocolo;
  }

  public String getRespostaProtocolo() {
    return respostaProtocolo;
  }

  public void setRespostaProtocolo(String respostaProtocolo) {
    this.respostaProtocolo = respostaProtocolo;
  }

  public LocalDate getDataRecebimento() {
    return dataRecebimento;
  }

  public void setDataRecebimento(LocalDate dataRecebimento) {
    this.dataRecebimento = dataRecebimento;
  }

  public LocalDate getDataUltimaAcao() {
    return dataUltimaAcao;
  }

  public void setDataUltimaAcao(LocalDate dataUltimaAcao) {
    this.dataUltimaAcao = dataUltimaAcao;
  }

  
}