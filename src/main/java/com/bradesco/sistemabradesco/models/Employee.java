package com.bradesco.sistemabradesco.models;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.dto.EmployeeDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "funcionario")
public class Employee implements Serializable {
  @Id
  @Column(name = "codigo")
  private String code;

  @Column(name = "nome", length = 60, nullable = false)
  private String name;

  @Column(name = "email", length = 60, nullable = false)
  @Email
  private String email;

  @Column(name = "status_funcionario", length = 10, nullable = false)
  private String employeeStatus;

  @ManyToOne
  @JoinColumn(name = "cargo_codigo", referencedColumnName = "codigo")
  private Position position;

  @ManyToOne
  @JoinColumn(name = "departamento_codigo", referencedColumnName = "codigo")
  private Department department;

  @Column(name = "senha", length = 40, nullable = false)
  private String password;

  public Employee() {
  }

  public Employee(EmployeeDTO employeeDTO) {
    BeanUtils.copyProperties(employeeDTO, this);
  }



  // GETTERS AND SETTERS
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmployeeStatus() {
    return employeeStatus;
  }

  public void setEmployeeStatus(String employeeStatus) {
    this.employeeStatus = employeeStatus;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  // METODOS HASHCODE E EQUALS
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null) ? 0 : code.hashCode());
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
    Employee other = (Employee) obj;
    if (code == null) {
      if (other.code != null)
        return false;
    } else if (!code.equals(other.code))
      return false;
    return true;
  }

}// class
