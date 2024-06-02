package com.bradesco.sistemabradesco.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.models.Channels;
import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.models.ProtocolType;

public class ProtocolDTO {

  private int code; // codigo
  private LocalDate openingDate; // dataAbertura
  private LocalDate deadline; // dataPrazo
  private String description; // descricao
  private Client client; // cliente
  private Channels channel; // canal
  private ProtocolType protocolType; // tipoProtocolo
  private LocalDate initialReceiptDate; // dataRecebimentoInicial
  private Department department; // departamento
  private boolean propensaoBacen; // (Se é uma reclamação que há possibilidades de gerar um apontamento BACEN)
  private boolean expedite; // (Se por prioridade, é necessário agilizar o protocolo)
  private boolean due; // (Se a abertura do protocolo tem um motivo plausível)
  private boolean justified; // (Se a questão que o requerente trouxe é procedente, ou se o Banco possui alguma justificativa)
  private Long protocolNumber; // numeroProtocolo
  private String protocolStatus; // status

  // CONSTRUTUOR DE IMPORTAÇÃO DA ENTIDADE PROTOCOLO
  public ProtocolDTO(Protocol protocol) {
    BeanUtils.copyProperties(protocol, this);
  }

  // CONSTRUTOR VAZIO
  public ProtocolDTO() {

  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public LocalDate getOpeningDate() {
    return openingDate;
  }

  public void setOpeningDate(LocalDate openingDate) {
    this.openingDate = openingDate;
  }

  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) {
    this.deadline = deadline;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Channels getChannel() {
    return channel;
  }

  public void setChannel(Channels channel) {
    this.channel = channel;
  }

  public ProtocolType getProtocolType() {
    return protocolType;
  }

  public void setProtocolType(ProtocolType protocolType) {
    this.protocolType = protocolType;
  }

  public LocalDate getInitialReceiptDate() {
    return initialReceiptDate;
  }

  public void setInitialReceiptDate(LocalDate initialReceiptDate) {
    this.initialReceiptDate = initialReceiptDate;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public boolean isPropensaoBacen() {
    return propensaoBacen;
  }

  public void setPropensaoBacen(boolean propensaoBacen) {
    this.propensaoBacen = propensaoBacen;
  }

  public boolean isExpedite() {
    return expedite;
  }

  public void setExpedite(boolean expedite) {
    this.expedite = expedite;
  }

  public boolean isDue() {
    return due;
  }

  public void setDue(boolean due) {
    this.due = due;
  }

  public boolean isJustified() {
    return justified;
  }

  public void setJustified(boolean justified) {
    this.justified = justified;
  }

  public Long getProtocolNumber() {
    return protocolNumber;
  }

  public void setProtocolNumber(Long protocolNumber) {
    this.protocolNumber = protocolNumber;
  }

  public String getProtocolStatus() {
    return protocolStatus;
  }

  public void setProtocolStatus(String protocolStatus) {
    this.protocolStatus = protocolStatus;
  }
}
