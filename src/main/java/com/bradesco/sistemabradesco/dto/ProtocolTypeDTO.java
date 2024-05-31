package com.bradesco.sistemabradesco.dto;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.models.ProtocolType;

public class ProtocolTypeDTO {
  
  private int code; // codigo
  private String type; // tipo
  private int treatmentDeadline; // prazoTratativa (assuming prazoTratativa refers to treatment)
  private boolean businessDays; // diasUteis

  public ProtocolTypeDTO(ProtocolType tipoProtocolo){
    BeanUtils.copyProperties(tipoProtocolo, this);
  }

  public ProtocolTypeDTO() {
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

  
  
}
