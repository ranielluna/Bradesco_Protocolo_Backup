package com.bradesco.sistemabradesco.models;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.dto.DepartmentDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamento")
public class Department implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_incremento
  @Column(name="codigo")
  private int code;
 
  @Column(name = "departamento", length = 60, nullable = false)
  private String department;
  

  
  public Department() {
  }

  public Department(DepartmentDTO departmentDTO){
    BeanUtils.copyProperties(departmentDTO, this);
  }

  //GETTERS AND SETTERS
  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
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
    Department other = (Department) obj;
    if (code != other.code)
      return false;
    return true;
  }

    
}//class
