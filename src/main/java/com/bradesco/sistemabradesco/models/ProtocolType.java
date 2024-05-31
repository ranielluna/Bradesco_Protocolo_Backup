package com.bradesco.sistemabradesco.models;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.dto.ProtocolTypeDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipoProtocolo")
public class ProtocolType implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_incremento
  @Column(name= "codigo")
  private int code;

  @Column(name = "tipo", length = 20, nullable = false)
  private String type;

  @Column(name = "prazo_tratativa")
  private int treatmentDeadline;

  @Column(name = "dias_uteis", nullable = false)
  private boolean businessDays;
  
  
  
  public ProtocolType() {
  }
  public ProtocolType(ProtocolTypeDTO tipoProtocoloDTO) {
    BeanUtils.copyProperties(tipoProtocoloDTO, this);
  }


  //GETTERS AND SETTERS
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
  public int getTreatmentDeadline() {
    return treatmentDeadline;
  }
  public void setTreatmentDeadline(int treatmentDeadline) {
    this.treatmentDeadline = treatmentDeadline;
  }
  public boolean isBusinessDays() {
    return businessDays;
  }
  public void setBusinessDays(boolean businessDays) {
    this.businessDays = businessDays;
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
   ProtocolType other = (ProtocolType) obj;
   if (code != other.code)
     return false;
   return true;
 }


  

  
}//class
