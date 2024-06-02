package com.bradesco.sistemabradesco.dto;

import org.springframework.beans.BeanUtils;
import com.bradesco.sistemabradesco.models.ProtocolType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para criar um  tipo de protocolo.")
public class ProtocolTypeDTO {
  
  private int code; // codigo

  @Schema(description = "Tipo do protocolo", example = "denúncia")
  private String type; // tipo

  @Schema(description = "Prazo de tratativa", example = "4")
  private int treatmentDeadline; // prazoTratativa (assuming prazoTratativa refers to treatment)

  @Schema(description = "Dias úteis", example = "true/false")
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
