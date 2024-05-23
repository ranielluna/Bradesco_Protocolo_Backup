package com.bradesco.sistemabradesco.dto;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.models.Position;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.models.Employee;

public class EmployeeDTO {
  private String codigo;
  private String nome;
  private String email;
  private String statusFuncionario;
  private Position cargo;
  private Department departamento;
  private String senha;
  
  public EmployeeDTO(Employee employee) {
    BeanUtils.copyProperties(employee, this);
  }

  public EmployeeDTO() {
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStatusFuncionario() {
    return statusFuncionario;
  }

  public void setStatusFuncionario(String statusFuncionario) {
    this.statusFuncionario = statusFuncionario;
  }

  public Position getCargo() {
    return cargo;
  }

  public void setCargo(Position cargo) {
    this.cargo = cargo;
  }

  public Department getDepartamento() {
    return departamento;
  }

  public void setDepartamento(Department departamento) {
    this.departamento = departamento;
  }
  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  
}
