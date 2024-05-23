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
  private int codigo;

  @Column(name = "data_abertura",nullable = false)
  private LocalDate dataAbertura; // melhor manipulação de data. LocalDate fornece muitas funções q podem ajudar

  @Column(name = "data_prazo", nullable = false)
  private LocalDate dataPrazo;

  @Column(name = "descricao", length = 1000, nullable = false)
  private String descricao;

  @ManyToOne
  @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
  private Client cliente; //aceita nulo e chave estrageira

  @ManyToOne
  @JoinColumn(name = "canais_codigo", referencedColumnName = "codigo")
  private Channels canal; //chave estrageira

  @ManyToOne
  @JoinColumn(name = "tipo_protocolo_codigo", referencedColumnName = "codigo")
  private TipoProtocolo tipoProtocolo;// chave estrangeira

  @Column(name = "data_recebimento_inicial")
  private LocalDate dataRecebimentoInicial;

  @ManyToOne
  @JoinColumn(name = "departamento_responsavel", referencedColumnName = "codigo")
  private Department departamento; //chave estrageira

  @Column(name = "propensao_bacen")
  private Boolean propensaoBacen ;

  @Column(name = "agilizar")
  private Boolean agilizar;

  @Column(name = "devido")
  private Boolean devido;

  @Column(name = "procedente")
  private Boolean procedente;

  @Column(name = "numero_protocolo",nullable = false)
  private Long numeroProtocolo;


  //CONSTRUTOR VAZIO
  public Protocol() {
  }

  public Protocol(ProtocolDTO protocol){
    BeanUtils.copyProperties(protocol, this);
  }

// Vamos precisar de construtores na aplicação?

  // GETTERS E SETTERS
  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public LocalDate getDataAbertura() {
    return dataAbertura;
  }

  public void setDataAbertura(LocalDate dataAbertura) {
    this.dataAbertura = dataAbertura;
  }

  public LocalDate getDataPrazo() {
    return dataPrazo;
  }

  public void setData_prazo(LocalDate dataPrazo) {
    this.dataPrazo = dataPrazo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Client getCliente() {
    return cliente;
  }

  public void setCliente(Client cliente) {
    this.cliente = cliente;
  }

  public Channels getCanal() {
    return canal;
  }

  public void setCanal(Channels canal) {
    this.canal = canal;
  }

  public TipoProtocolo getTipoProtocolo() {
    return tipoProtocolo;
  }

  public void setTipoProtocolo(TipoProtocolo tipoProtocolo) {
    this.tipoProtocolo = tipoProtocolo;
  }

  public LocalDate getDataRecebimentoInicial() {
    return dataRecebimentoInicial;
  }

  public void setDataRecebimentoInicial(LocalDate dataRecebimentoInicial) {
    this.dataRecebimentoInicial = dataRecebimentoInicial;
  }

  public Department getDepartamento() {
    return departamento;
  }

  public void setDepartamento(Department departamento) {
    this.departamento = departamento;
  }

  public boolean isPropensaoBacen() {
    return propensaoBacen != null && propensaoBacen;
  }

  public void setPropensaoBacen(boolean propensaoBacen) {
    this.propensaoBacen = propensaoBacen;
  }

  public boolean isAgilizar() {
    return agilizar != null && agilizar;
  }

  public void setAgilizar(boolean agilizar) {
    this.agilizar = agilizar;
  }

  public boolean isDevido() {
    return devido != null && devido;
  }

  public void setDevido(boolean devido) {
    this.devido = devido;
  }

  public boolean isProcedente() {
    return procedente != null && procedente;
  }

  public void setProcedente(boolean procedente) {
    this.procedente = procedente;
  }

  public Long getNumeroProtocolo() {
    return numeroProtocolo;
  }

  public void setNumeroProtocolo(Long numeroProtocolo) {
    this.numeroProtocolo = numeroProtocolo;
  }

  //METODOS HASHCODE E EQUALS
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + codigo;
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
    if (codigo != other.codigo)
      return false;
    return true;
  }


} //class
