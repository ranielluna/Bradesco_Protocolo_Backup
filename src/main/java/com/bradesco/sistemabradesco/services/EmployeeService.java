package com.bradesco.sistemabradesco.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.EmployeeDTO;
import com.bradesco.sistemabradesco.models.Employee;
import com.bradesco.sistemabradesco.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
  // @SuppressWarnings("unused")
  @Autowired
  private EmployeeRepository employeeRepository;


  public List<EmployeeDTO> listEmployee(){
    List<Employee> employees = employeeRepository.findAll();
    return employees.stream().map(EmployeeDTO::new).toList();
  }

     /* Criar conta */
    public Employee addEmployee(EmployeeDTO employeeDTO){
        Employee newEmployee = new Employee();
        BeanUtils.copyProperties(employeeDTO, newEmployee);
        return employeeRepository.save(newEmployee);

    }


  /* deletar */
     @Transactional
    public void deleteEmployee(String code){
      employeeRepository.deleteById(code);
    }


}
