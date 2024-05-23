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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.sistemabradesco.dto.DepartmentDTO;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.services.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    /* criando Departamento */
    @PostMapping("/addDepartment")
    public Department addDepartment(@RequestBody DepartmentDTO departmentDTO){
        return departmentService.addDepartment(departmentDTO);
    }

    /* deletando departamento */
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable int codigo){
        departmentService.deleteDepartment(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Departamento deletado com sucesso");
        return ResponseEntity.ok(message);
    }

    /* Listar Departamentos */
    @GetMapping("/listDepartments")
    public List<Department> listDepartments(){
        return departmentService.listDepartments();
    }
            
}

