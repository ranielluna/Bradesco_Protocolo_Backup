package com.bradesco.sistemabradesco.dto;
import org.springframework.beans.BeanUtils;
import com.bradesco.sistemabradesco.models.Departamento;


public class DepartarmentoDTO{
    private int codigo;
    private String departamento;


   public DepartarmentoDTO(Departamento departamento) {
    BeanUtils.copyProperties(departamento, this);
  }

  public DepartarmentoDTO() {
  }

     //GETTERS AND SETTERS
  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getDepartamento() {
    return departamento;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }
}