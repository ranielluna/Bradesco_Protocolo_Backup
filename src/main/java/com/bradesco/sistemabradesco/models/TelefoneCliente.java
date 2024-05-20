package com.bradesco.sistemabradesco.models;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.dto.TelefoneClienteDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "telefoneCliente")
public class TelefoneCliente implements Serializable{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_incremento
  private int codigo;

  @Column(name = "ddd", length = 3, nullable = false)
  private int ddd;

  @Column(name = "numero", nullable = false)
  private int numero;

  @OneToOne
  @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
  private Cliente cliente;

  @OneToOne
  @JoinColumn(name = "tipo_telefone_codigo", referencedColumnName = "codigo")
  private TipoTelefone tipoTelefone;

  public TelefoneCliente() {
  }
  public TelefoneCliente(TelefoneClienteDTO telefoneClienteDTO) {
    BeanUtils.copyProperties(telefoneClienteDTO,this);
  }

  
  //GETTERS AND SETTERS
 public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public int getDdd() {
    return ddd;
  }

  public void setDdd(int ddd) {
    this.ddd = ddd;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public TipoTelefone getTipoTelefone() {
    return tipoTelefone;
  }
  public void setTipoTelefone(TipoTelefone tipoTelefone) {
    this.tipoTelefone = tipoTelefone;
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
    TelefoneCliente other = (TelefoneCliente) obj;
    if (codigo != other.codigo)
      return false;
    return true;
  }



}//class