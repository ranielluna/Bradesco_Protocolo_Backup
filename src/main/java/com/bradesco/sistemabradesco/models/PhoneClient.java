package com.bradesco.sistemabradesco.models;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.dto.PhoneClientDTO;

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
public class PhoneClient implements Serializable{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_incremento
  @Column(name="codigo")
  private int code;

  @Column(name = "ddd", length = 3, nullable = false)
  private int ddd;

  @Column(name = "numero", nullable = false)
  private int number;

  @OneToOne
  @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
  private Client client;

  @OneToOne
  @JoinColumn(name = "tipo_telefone_codigo", referencedColumnName = "codigo")
  private PhoneType phoneType;

  public PhoneClient() {
  }
  public PhoneClient(PhoneClientDTO telefoneClienteDTO) {
    BeanUtils.copyProperties(telefoneClienteDTO,this);
  }

  
  //GETTERS AND SETTERS
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
    PhoneClient other = (PhoneClient) obj;
    if (code != other.code)
      return false;
    return true;
  }



}//class