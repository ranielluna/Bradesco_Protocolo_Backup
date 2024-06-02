package com.bradesco.sistemabradesco.services;

import java.util.ArrayList;
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
import com.bradesco.sistemabradesco.models.Position;
import com.bradesco.sistemabradesco.repository.EmployeeRepository;
import com.bradesco.sistemabradesco.repository.SituationProtocolRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private SituationProtocolRepository situationProtocolRepository;

  public List<EmployeeDTO> listEmployee() {
    List<Employee> employees = employeeRepository.findAll();
    return employees.stream().map(EmployeeDTO::new).toList();
  }

  /* Criar conta */
  public Employee addEmployee(EmployeeDTO employeeDTO) {
    Employee newEmployee = new Employee();
    BeanUtils.copyProperties(employeeDTO, newEmployee);
    return employeeRepository.save(newEmployee);

  }

  // updates
  // Atualizar funcionário
  @Transactional
  public Employee updateEmployeeDepartment(String code, EmployeeDTO employeeDTO) {
    Employee employeeDepartment = employeeRepository.findByCode(code);
    employeeDepartment.setDepartment(employeeDTO.getDepartment());
    return employeeRepository.save(employeeDepartment);
  }

  // update cargo
  @Transactional
  public Employee updateEmployeePosition(String code, EmployeeDTO employeeDTO) {
    Employee employeePosition = employeeRepository.findByCode(code);
    employeePosition.setPosition(employeeDTO.getPosition());
    return employeeRepository.save(employeePosition);
  }

  // update status
  @Transactional
  public Employee updateEmployeeStatus(String code, EmployeeDTO employeeDTO) {
    Employee employeeStatus = employeeRepository.findByCode(code);
    employeeStatus.setEmployeeStatus(employeeDTO.getEmployeeStatus());
    return employeeRepository.save(employeeStatus);
  }

  // update email
  @Transactional
  public Employee updateEmployeeEmail(String code, EmployeeDTO employeeDTO) {
    Employee employeeEmail = employeeRepository.findByCode(code);
    employeeEmail.setEmail(employeeDTO.getEmail());
    return employeeRepository.save(employeeEmail);
  }

  // update password
  @Transactional
  public Employee updateEmployeePassaword(String code, EmployeeDTO employeeDTO) {
    Employee employeePassword = employeeRepository.findByCode(code);
    employeePassword.setPassword(employeeDTO.getPassword());
    return employeeRepository.save(employeePassword);
  }

  // Listagem funcionario por departamento
  public List<Employee> listEmployeeByDepartment(Department department) {
    List<Employee> employees = employeeRepository.findByDepartment(department);
    return employees;
}

public List<Employee> listAnalystEmployess (List<Employee> employees){
  List<Employee> employeeOffGerente = new ArrayList<>();
  //Employee employee = new Employee();
  // Itera sobre a lista em ordem reversa para evitar problemas ao remover elementos
  for (Employee employee : employees) {
    // Verifica se o cargo do funcionário é "analista"
    if (!"Gerente".equals(employee.getPosition().getPosition())) {
        // Remove o funcionário da lista
        employeeOffGerente.add(employee);
    }
  }
  return employeeOffGerente;
}




  // listagem funcionario por cargo
  public List<Employee> listEmployeeByPosition(Position position) {
    return employeeRepository.findByPosition(position);
  }

  // retorna o funcionario com menor demanda da tabela situação protocolo
  public Employee filterEmployeesCount(List<Employee> employees) {
    Map<Employee, Long> employeeRecordCounts = new HashMap<>();

    // Itera sobre a lista de funcionários e conta os registros na tabela
    // SituationProtocol com Protocol = "Novo"
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

  /* deletar */
  @Transactional
  public void deleteEmployee(String code) {
    employeeRepository.deleteById(code);
  }

}
