package com.bradesco.sistemabradesco.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.sistemabradesco.dto.DepartmentDTO;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.services.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /* criando Departamento */
    @Operation(description = "Cria um departamento na aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna o departamento com suas informações."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PostMapping("/addDepartment")
    public Department addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.addDepartment(departmentDTO);
    }

    // Atualizar departamento
    @PutMapping("/{code}")
    public ResponseEntity<Department> updateDepartment(@PathVariable int code,
            @RequestBody DepartmentDTO departmentDTO) {
        Department updatedDepartment = departmentService.updateDepartment(code, departmentDTO);
        return ResponseEntity.ok(updatedDepartment);
    }

    /* deletando departamento */
    @Operation(description = "Deleta um departamento da aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Remove um departamento da aplicação e mostra uma mensagem de sucesso!."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable int code) {
        departmentService.deleteDepartment(code);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Departamento deletado com sucesso");
        return ResponseEntity.ok(message);
    }

    /* Listar Departamentos */

    @Operation(description = "Lista todos os departamentos existentes na aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna uma lista com todos os departamentos existentes."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping("/listDepartment")
    public List<Department> listDepartments() {
        return departmentService.listDepartments();

    }

}
