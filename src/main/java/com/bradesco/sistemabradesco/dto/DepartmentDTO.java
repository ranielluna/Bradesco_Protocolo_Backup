package com.bradesco.sistemabradesco.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import com.bradesco.sistemabradesco.models.Department;

@Schema(description = "Dados para criar um departamento")
public class DepartmentDTO{
    private int code;

    @Schema(description = "Nome do departamento", example = "T.i")
    private String department;


   public DepartmentDTO(Department department) {
    BeanUtils.copyProperties(department, this);
  }

  public DepartmentDTO() {
  }

     //GETTERS AND SETTERS
  public int getCode() {
    return code;
  }

  public void setCodigo(int code) {
    this.code = code;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}