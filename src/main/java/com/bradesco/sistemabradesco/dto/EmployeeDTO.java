package com.bradesco.sistemabradesco.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.models.Position;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.models.Employee;

@Schema(description = "Dados para criar um funcionário")
public class EmployeeDTO {

  @Schema(description = "Código do funcionário", example = "i123456")
  private String code;

  @Schema(description = "Nome do funcionário", example = "Amaro Lima")
  private String name;

  @Schema(description = "Email do funcionário", example = "amara.lima@bradesco.com")
  private String email;

  @Schema(description = "Status do funcionário", example = "Ativo")
  private String employeeStatus;

  @Schema(description = "Cargo do funcionário", example = "{objeto Cargo}")
  private Position position;

  @Schema(description = "Departamento do funcionário", example = "{objeto Departamento}")
  private Department department;

  private String password;
  
  public EmployeeDTO(Employee employee) {
    BeanUtils.copyProperties(employee, this);
  }

  public EmployeeDTO() {
  }


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
 
}
