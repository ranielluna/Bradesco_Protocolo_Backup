package com.bradesco.sistemabradesco.services;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.EmployeeDTO;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.models.Employee;
import com.bradesco.sistemabradesco.repository.EmployeeRepository;
import com.bradesco.sistemabradesco.repository.SituationProtocolRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
  // @SuppressWarnings("unused")
  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private SituationProtocolRepository situationProtocolRepository;



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


    
    public List<Employee> listEmployeeByDepartment(Department department){
      return employeeRepository.findByDepartment(department);
    }




    //retorna o funcionario com menor demanda da tabela situação protocolo
  public Employee filterEmployeesCount(List<Employee> employees) {
    Map<Employee, Long> employeeRecordCounts = new HashMap<>();

        // Itera sobre a lista de funcionários e conta os registros na tabela SituationProtocol com Protocol = "Novo"
    for (Employee employee : employees) {
      long count = situationProtocolRepository.countByEmployee(employee);
      employeeRecordCounts.put(employee, count);
    }

        // Filtra a lista de funcionários com base no número mínimo de registros
    List<Employee> filteredEmployees = employees.stream()
      .filter(employee -> employeeRecordCounts.getOrDefault(employee, 0L) >= 0)
      .collect(Collectors.toList());

           
    return filteredEmployees.get(0);
}

}
