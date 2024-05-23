package com.bradesco.sistemabradesco.controller;

import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bradesco.sistemabradesco.dto.EmployeeDTO;
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
    //senha = 010203

    @Operation(description = "Permite que um funcionário faça o login no sistema.")
      @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Acesso Liberado."),
        @ApiResponse(responseCode = "404", description = "Acesso negado. Dados inválidos."),
        @ApiResponse(responseCode="405", description="Acesso negado. Usuário não encontrado." )
     }
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Employee employee) {
        String codigo = employee.getCodigo();
        String senha = employee.getSenha();
        
        employee = employeeRepository.findByCodigo(codigo);
        if (employee != null) {
            if (employee.getSenha().equals(senha)) {
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
     }
    )
    @PostMapping("/criar")
    public Employee addEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.addEmployee(employeeDTO);
        
    }
    
    
    @Operation(description = "Deleta um funcionário da aplicação.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Remove um funcionário e mostra uma mensagem de sucesso."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarFuncionario(@PathVariable String codigo){
        employeeService.deleteEmployee(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Funcionário deletado com sucesso!");
        return ResponseEntity.ok(message);

    }







}//Class
