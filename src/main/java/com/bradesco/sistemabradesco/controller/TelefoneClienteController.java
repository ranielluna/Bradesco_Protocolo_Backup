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

import com.bradesco.sistemabradesco.dto.TelefoneClienteDTO;
import com.bradesco.sistemabradesco.models.TelefoneCliente;
import com.bradesco.sistemabradesco.repository.TelefoneClienteRepository;
import com.bradesco.sistemabradesco.services.TelefoneClienteService;

@RestController
@RequestMapping("api/telefoneCliente")
public class TelefoneClienteController {

  @Autowired
  private TelefoneClienteRepository repository;

  // public TelefoneClienteController(TelefoneClienteRepository repository){
  //   this.repository=repository;
  // }
  
 
  private TelefoneClienteService service;

  public TelefoneClienteController(TelefoneClienteService service){
    this.service=service;
  }

  // listar
  @GetMapping("/lista")
  public List<TelefoneCliente> listarTelefoneCliente(){
    List<TelefoneCliente> telefoneClientes = repository.findAll(); 
    return telefoneClientes;
  }
  // filtrar por cliente??

  // criar
  @PostMapping("/criarTelefoneCliente")
  public TelefoneCliente criarTelefoneCliente(@RequestBody TelefoneClienteDTO telefoneClienteDTO){
    return service.criarTelefoneCliente(telefoneClienteDTO);
  }

   // deletar
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarTelefoneCliente(@PathVariable int codigo){
        service.deletarTelefoneCliente(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "TelefoneCliente deletado com sucesso!");
        return ResponseEntity.ok(message);

    }
}