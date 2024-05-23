package com.bradesco.sistemabradesco.models;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.dto.ProtocolDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name= "protocolo")
public class Protocol implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_incremento
  @Column(name = "codigo")
  private int code;

  @Column(name = "data_abertura",nullable = false)
  private LocalDate openingDate; // melhor manipulação de data. LocalDate fornece muitas funções q podem ajudar

  @Column(name = "data_prazo", nullable = false)
  private LocalDate deadline;

  @Column(name = "descricao", length = 1000, nullable = false)
  private String description;

  @ManyToOne
  @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
  private Client client; //aceita nulo e chave estrageira

  @ManyToOne
  @JoinColumn(name = "canais_codigo", referencedColumnName = "codigo")
  private Channels channel; //chave estrageira

  @ManyToOne
  @JoinColumn(name = "tipo_protocolo_codigo", referencedColumnName = "codigo")
  private ProtocolType protocolType;// chave estrangeira

  @Column(name = "data_recebimento_inicial")
  private LocalDate initialReceiptDate;

  @ManyToOne
  @JoinColumn(name = "departamento_responsavel", referencedColumnName = "codigo")
  private Department department; //chave estrageira

  @Column(name = "propensao_bacen")
  private Boolean propensaoBacen ;

  @Column(name = "agilizar")
  private Boolean expedite;

  @Column(name = "devido")
  private Boolean due;

  @Column(name = "procedente")
  private Boolean justified;

  @Column(name = "numero_protocolo",nullable = false)
  private Long protocolNumber;


  //CONSTRUTOR VAZIO
  public Protocol() {
  }

  public Protocol(ProtocolDTO protocolDTO){
    BeanUtils.copyProperties(protocolDTO, this);
  }



  // GETTERS E SETTERS
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
  
  public Boolean getPropensaoBacen() {
    return propensaoBacen;
  }
  
  public void setPropensaoBacen(Boolean propensaoBacen) {
    this.propensaoBacen = propensaoBacen;
  }
  
  public Boolean getExpedite() {
    return expedite;
  }

  public void setExpedite(Boolean expedite) {
    this.expedite = expedite;
  }

  public Boolean getDue() {
    return due;
  }

  public void setDue(Boolean due) {
    this.due = due;
  }

  public Boolean getJustified() {
    return justified;
  }

  public void setJustified(Boolean justified) {
    this.justified = justified;
  }

  public Long getProtocolNumber() {
    return protocolNumber;
  }

  public void setProtocolNumber(Long protocolNumber) {
    this.protocolNumber = protocolNumber;
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
    Protocol other = (Protocol) obj;
    if (code != other.code)
      return false;
    return true;
  }


} //class
