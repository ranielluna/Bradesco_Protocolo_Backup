package com.bradesco.sistemabradesco.controller;

import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.sistemabradesco.dto.EmployeeDTO;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.models.Employee;
import com.bradesco.sistemabradesco.repository.EmployeeRepository;
import com.bradesco.sistemabradesco.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    // login codigos = i025368 - i054867 - i147857
    // senha = 010203

    @Operation(description = "Permite que um funcionário faça o login no sistema.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Acesso Liberado."),
            @ApiResponse(responseCode = "404", description = "Acesso negado. Dados inválidos."),
            @ApiResponse(responseCode = "405", description = "Acesso negado. Usuário não encontrado.")
    })
    @PostMapping("/login")
    public ResponseEntity<String> loginEmployee(@RequestBody Employee employee) {
        String code = employee.getCode();
        String password = employee.getPassword();

        employee = employeeRepository.findByCode(code);
        if (employee != null) {
            if (employee.getPassword().equals(password)) {
                return new ResponseEntity<>("Acesso liberado", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Acesso negado - Dados inválidos", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Acesso negado - Usuário não encontrado", HttpStatus.UNAUTHORIZED);
        }

    }

    /* criando funcionario */

    @Operation(description = "Cria um funcionário na aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna o funcionário com suas informações."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.addEmployee(employeeDTO);

    }

    @Operation(description = "Deleta um funcionário da aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Remove um funcionário e mostra uma mensagem de sucesso."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable String code) {
        employeeService.deleteEmployee(code);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Funcionário deletado com sucesso!");
        return ResponseEntity.ok(message);

    }

    @Operation(description = "Lista todos os departamentos existentes na aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna uma lista com todos os departamentos existentes."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping("/listEmployees")
    public List<EmployeeDTO> listEmployees() {
        return employeeService.listEmployee();

    }

    // list por departamento
    @PostMapping("/dep")
    public List<Employee> listEmployeesByDepartment(@RequestBody Department department) {
        return employeeService.listEmployeeByDepartment(department);
    }

    // UPDATES
    // Atualizar departamento do funcionário
    @PutMapping("/{code}/department")
    public ResponseEntity<Employee> updateEmployeeDepartment(@PathVariable String code,
            @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployeeDepartment(code, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Atualizar cargo do funcionário
    @PutMapping("/{code}/position")
    public ResponseEntity<Employee> updateEmployeePosition(@PathVariable String code,
            @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployeePosition(code, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Atualizar status do funcionário
    @PutMapping("/{code}/status")
    public ResponseEntity<Employee> updateEmployeeStatus(@PathVariable String code,
            @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployeeStatus(code, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Atualizar email do funcionário
    @PutMapping("/{code}/email")
    public ResponseEntity<Employee> updateEmployeeEmail(@PathVariable String code,
            @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployeeEmail(code, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Atualizar senha do funcionário
    @PutMapping("/{code}/password")
    public ResponseEntity<Employee> updateEmployeePassword(@PathVariable String code,
            @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployeePassaword(code, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

}// Class
