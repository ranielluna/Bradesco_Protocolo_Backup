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

import com.bradesco.sistemabradesco.dto.DepartarmentoDTO;
import com.bradesco.sistemabradesco.models.Departamento;
import com.bradesco.sistemabradesco.services.DepartamentoService;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    // public DepartamentoController(DepartamentoService departamentoService){
    //     this.departamentoService=departamentoService;
    // }



    /* criando Departamento */
    @PostMapping("/criar")
    public Departamento criarDepartamento(@RequestBody DepartarmentoDTO departarmentoDTO){
        return departamentoService.criarDepartamento(departarmentoDTO);
    }

    /* deletando cliente */
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarDepartamento(@PathVariable int codigo){
        departamentoService.deletarDepartamento(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Cliente deletado com sucesso");
        return ResponseEntity.ok(message);
    }

    /* Listar Departamentos */
    @GetMapping("/listar")
    public List<Departamento> listaDepartamentos(){
        return departamentoService.listarDepartamentos();
    }
            
}

