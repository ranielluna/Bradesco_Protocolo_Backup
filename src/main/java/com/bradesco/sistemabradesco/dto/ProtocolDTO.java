package com.bradesco.sistemabradesco.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.models.Channels;
import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.models.ProtocolType;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Dados para criar um protocolo.")
public class ProtocolDTO {

  private int code; // codigo
  private LocalDate openingDate; // dataAbertura
  private LocalDate deadline; // dataPrazo

  @Schema(description = "Descrição do protocolo", example = "Não estou conseguindo acessar meu saldo.")
  private String description; // descricao

  @Schema(description = "Cliente", example = "{objeto Cliente}")
  private Client client; // cliente

  @Schema(description = "Canal", example = "{objeto Canais}")
  private Channels channel; // canal

  @Schema(description = "Tipo de protocolo", example = "{objeto tipoProtocolo}")
  private ProtocolType protocolType; // tipoProtocolo

  @Schema(description = "Data de recebimento", example = "2024/06/01")
  private LocalDate initialReceiptDate; // dataRecebimentoInicial

  @Schema(description = "Departamento", example = "{objeto departamento}")
  private Department department; // departamento

  @Schema(description = "Propensão bacen", example = "true/false")
  private boolean propensaoBacen; // (Se é uma reclamação que há possibilidades de gerar um apontamento BACEN)

  @Schema(description = "Agilizar", example = "true/false")
  private boolean expedite; // (Se por prioridade, é necessário agilizar o protocolo)

  @Schema(description = "Devido:", example = "true/false")
  private boolean due; // (Se a abertura do protocolo tem um motivo plausível)

  @Schema(description = "Justificada", example = "true/false")
  private boolean justified; // (Se a questão que o requerente trouxe é procedente, ou se o Banco possui alguma justificativa)

  @Schema(description = "Número do protocolo", example = "20240010123")
  private Long protocolNumber; // numeroProtocolo


  private String protocolStatus; //status

  //CONSTRUTUOR DE IMPORTAÇÃO DA ENTIDADE PROTOCOLO
  public ProtocolDTO(Protocol protocol){
    BeanUtils.copyProperties(protocol, this);
  }

  //CONSTRUTOR VAZIO
  public ProtocolDTO(){

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
