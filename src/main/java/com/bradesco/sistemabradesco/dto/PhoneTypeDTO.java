package com.bradesco.sistemabradesco.dto;

import org.springframework.beans.BeanUtils;
import com.bradesco.sistemabradesco.models.PhoneType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para criar um tipo de telefone")
public class PhoneTypeDTO {
  private int code;

  
  @Schema(description = "Tipo do telefone", example = "residencial")
  private String type;
  
  public PhoneTypeDTO(PhoneType tipoTelefone) {
    BeanUtils.copyProperties(tipoTelefone, this);
  }

  public PhoneTypeDTO() {
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


  
}
