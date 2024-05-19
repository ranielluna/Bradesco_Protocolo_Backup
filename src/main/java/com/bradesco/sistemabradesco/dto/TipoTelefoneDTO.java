package com.bradesco.sistemabradesco.dto;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.models.TipoTelefone;

public class TipoTelefoneDTO {
  private int codigo;
  private String tipo;
  
  public TipoTelefoneDTO(TipoTelefone tipoTelefone) {
    BeanUtils.copyProperties(tipoTelefone, this);
  }

  public TipoTelefoneDTO() {
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
 
}
