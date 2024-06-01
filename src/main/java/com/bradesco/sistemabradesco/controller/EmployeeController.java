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
import com.bradesco.sistemabradesco.exception.EmployeeNotFoundException;
import com.bradesco.sistemabradesco.exception.NotAuthorizedException;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.models.Employee;
import com.bradesco.sistemabradesco.repository.EmployeeRepository;
import com.bradesco.sistemabradesco.services.EmployeeService;
import com.bradesco.sistemabradesco.services.ManagerService;

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

    @Autowired
    private ManagerService managerService;

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

        employee = employeeRepository.findByCode(code).get();
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

    // Criando funcionario

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

    // Listar por departamento
    @PostMapping("/dep")
    public List<Employee> listEmployeesByDepartment(@RequestBody Department department) {
        return employeeService.listEmployeeByDepartment(department);
    }

    // UPDATES restritos ao gerente
    // atualizar o status de um funcionário
    @PutMapping("/{managerCode}/status")
    public ResponseEntity<?> updateEmployeeStatus(@PathVariable String managerCode,
            @RequestBody EmployeeDTO employeeDTO) {
        try {
            Employee updatedEmployee = managerService.updateEmployeeStatus(managerCode, employeeDTO);
            return ResponseEntity.ok(updatedEmployee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NotAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // atualizar cargo do funcionário
    @PutMapping("/{managerCode}/position")
    public ResponseEntity<?> updateEmployeePosition(@PathVariable String managerCode,
            @RequestBody EmployeeDTO employeeDTO) {
        try {
            Employee updatedEmployee = managerService.updateEmployeePosition(managerCode, employeeDTO);
            return ResponseEntity.ok(updatedEmployee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NotAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // atualizar departamento do funcionário
    @PutMapping("/{managerCode}/department")
    public ResponseEntity<?> updateEmployeeDepartment(@PathVariable String managerCode,
            @RequestBody EmployeeDTO employeeDTO) {
        try {
            Employee updatedEmployee = managerService.updateEmployeeDepartment(managerCode, employeeDTO);
            return ResponseEntity.ok(updatedEmployee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NotAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // atualizar email do funcionário
    @PutMapping("/{managerCode}/email")
    public ResponseEntity<?> updateEmployeeEmail(@PathVariable String managerCode,
            @RequestBody EmployeeDTO employeeDTO) {
        try {
            Employee updatedEmployee = managerService.updateEmployeeEmail(managerCode, employeeDTO);
            return ResponseEntity.ok(updatedEmployee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NotAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // atualizar senha do funcionário
    @PutMapping("/{managerCode}/password")
    public ResponseEntity<?> updateEmployeePassword(@PathVariable String managerCode,
            @RequestBody EmployeeDTO employeeDTO) {
        try {
            Employee updatedEmployee = managerService.updateEmployeePassaword(managerCode, employeeDTO);
            return ResponseEntity.ok(updatedEmployee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NotAuthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }// UPDATES restritos ao gerente

}// Class
