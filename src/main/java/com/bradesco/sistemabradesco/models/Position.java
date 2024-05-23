package com.bradesco.sistemabradesco.models;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.dto.PositionDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargo")
public class Position implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_incremento
  @Column(name = "codigo")
  private int code;

  @Column(name = "cargo", length = 20, nullable = false)
  private String position;
  
  public Position() {
  }

  public Position(PositionDTO positionDTO){
    BeanUtils.copyProperties(positionDTO, this);
  }
  
  //GETTERS AND SETTERS
  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
  
  //METODOS HASHCODE E EQUALS
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + code;
    return result;
  }// Este método é responsável por calcular o código de hash do objeto.

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Position other = (Position) obj;
    if (code != other.code)
      return false;
    return true;
  }// Este método verifica se dois objetos são iguais

    
  

}//class
