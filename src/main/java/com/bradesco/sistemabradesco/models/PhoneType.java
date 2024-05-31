package com.bradesco.sistemabradesco.models;


import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.dto.PhoneTypeDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipoTelefone")
public class PhoneType implements Serializable{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_incremento
  @Column(name="codigo")
  private int code;

  @Column(name = "tipo", length=20, nullable = false)
  private String type;


  
  public PhoneType() {
  }
  public PhoneType(PhoneTypeDTO tipoTelefoneDTO) {
    BeanUtils.copyProperties(tipoTelefoneDTO, this);
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  
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
    PhoneType other = (PhoneType) obj;
    if (code != other.code)
      return false;
    return true;
  }





}//class
