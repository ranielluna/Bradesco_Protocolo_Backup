package com.bradesco.sistemabradesco.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  // Criar funcionario
  @Transactional
  public Employee addEmployee(EmployeeDTO employeeDTO) {
    Employee newEmployee = new Employee();
    BeanUtils.copyProperties(employeeDTO, newEmployee);
    return employeeRepository.save(newEmployee);

  }

  // Listagem funcionario por departamento
  public List<Employee> listEmployeeByDepartment(Department department) {
    List<Employee> employees = employeeRepository.findByDepartment(department);
    return employees;
  }

  // filtra apenas os funcionários que não são gerentes
  public List<Employee> listAnalystEmployess(List<Employee> employees) {
    List<Employee> employeeOffGerente = new ArrayList<>();
    // Employee employee = new Employee();
    // Itera sobre a lista em ordem reversa para evitar problemas ao remover
    // elementos
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

  // deletar
  @Transactional
  public void deleteEmployee(String code) {
    employeeRepository.deleteById(code);
  }

  // verificação de cargos para separar funções dos colaboradores
  // Verificar Gerente
  public boolean isManager(Employee employee) {
    // Position position = new Position();
    int positionCode = employee.getPosition().getCode();
    String jobTitle = Position.getJobTitleByCode(positionCode);
    return "Gerente".equalsIgnoreCase(jobTitle);
  }

  // Verificar Analista
  public boolean isAnalyst(Employee employee) {
    String jobTitle = Position.getJobTitleByCode(2);
    return "Analista".equalsIgnoreCase(jobTitle);
  }

  // Verificar Operador
  public boolean isOperator(Employee employee) {
    String jobTitle = Position.getJobTitleByCode(1);
    return "Operator".equalsIgnoreCase(jobTitle);
  }

}
