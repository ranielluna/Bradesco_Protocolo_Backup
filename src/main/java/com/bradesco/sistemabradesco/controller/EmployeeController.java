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


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    
    // login codigos = i025368 - i054867 - i147857
    //senha = 010203
    @PostMapping("/loginEmployee")
    public ResponseEntity<String> loginEmployee(@RequestBody Employee employee) {
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
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.addEmployee(employeeDTO);
        
    }
    
    //   /* criando conta */
    //   @PostMapping("/criar")
    //   public Conta criarConta(@RequestBody ContaDTO contaDTO){
    //       return contaService.criarConta(contaDTO);
    //   }
     
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable String codigo){
        employeeService.deleteEmployee(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Funcionário deletado com sucesso!");
        return ResponseEntity.ok(message);

    }







}//Class
