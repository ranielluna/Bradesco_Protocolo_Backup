package com.bradesco.sistemabradesco.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.EmployeeDTO;
import com.bradesco.sistemabradesco.exception.EmployeeNotFoundException;
import com.bradesco.sistemabradesco.exception.NotAuthorizedException;
import com.bradesco.sistemabradesco.models.Employee;
import com.bradesco.sistemabradesco.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class ManagerService {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    // update status
    @Transactional
    public Employee updateEmployeeStatus(String code, EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findByCode(code);
        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException("Funcionário não encontrado.");
        }

        Employee currentUser = optionalEmployee.get();
        if (!employeeService.isManager(currentUser)) {
            throw new NotAuthorizedException("Apenas gerentes podem mudar o atatus de um funcionário.");
        }

        Employee employeeStatus = employeeRepository.findByCode(code).get();
        employeeStatus.setEmployeeStatus(employeeDTO.getEmployeeStatus());
        return employeeRepository.save(employeeStatus);
    }

    // update cargo
    @Transactional
    public Employee updateEmployeePosition(String code, EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findByCode(code);
        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException("Funcionário não encontrado.");
        }

        Employee currentUser = optionalEmployee.get();

        if (!employeeService.isManager(currentUser)) {
            throw new NotAuthorizedException("Apenas gerentes podem mudar o cargo de um funcionário.");
        }

        Employee employeePosition = employeeRepository.findByCode(code).get();
        employeePosition.setPosition(employeeDTO.getPosition());
        return employeeRepository.save(employeePosition);
    }

    // Atualizar departamento
    @Transactional
    public Employee updateEmployeeDepartment(String code, EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findByCode(code);
        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException("Funcionário não encontrado.");
        }

        Employee currentUser = optionalEmployee.get();
        if (!employeeService.isManager(currentUser)) {
            throw new NotAuthorizedException("Apenas gerentes podem mudar o departamento de um funcionário.");
        }

        Employee employeeDepartment = employeeRepository.findByCode(code).get();
        employeeDepartment.setDepartment(employeeDTO.getDepartment());
        return employeeRepository.save(employeeDepartment);
    }

    // update email
    @Transactional
    public Employee updateEmployeeEmail(String code, EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findByCode(code);
        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException("Funcionário não encontrado.");
        }

        Employee currentUser = optionalEmployee.get();

        if (!employeeService.isManager(currentUser)) {
            throw new NotAuthorizedException("Apenas gerentes podem mudar o email de um funcionário.");
        }

        Employee employeeEmail = employeeRepository.findByCode(code).get();
        employeeEmail.setEmail(employeeDTO.getEmail());
        return employeeRepository.save(employeeEmail);
    }

    // update password
    @Transactional
    public Employee updateEmployeePassaword(String code, EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findByCode(code);
        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException("Funcionário não encontrado.");
        }

        Employee currentUser = optionalEmployee.get();

        if (!employeeService.isManager(currentUser)) {
            throw new NotAuthorizedException("Apenas gerentes podem mudar a senha de um funcionário.");
        }

        Employee employeePassword = employeeRepository.findByCode(code).get();
        employeePassword.setPassword(employeeDTO.getPassword());
        return employeeRepository.save(employeePassword);
    }
}