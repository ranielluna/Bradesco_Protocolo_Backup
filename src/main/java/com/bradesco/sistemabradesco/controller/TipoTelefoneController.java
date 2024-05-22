package com.bradesco.sistemabradesco.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.sistemabradesco.dto.TipoTelefoneDTO;
import com.bradesco.sistemabradesco.models.TipoTelefone;
import com.bradesco.sistemabradesco.repository.TipoTelefoneRepository;
import com.bradesco.sistemabradesco.services.TipoTelefoneService;


@RestController
@RequestMapping("api/tipoTelefone")
public class TipoTelefoneController {
  
  @Autowired
  private TipoTelefoneRepository repository;

  // public TipoTelefoneController(TipoTelefoneRepository repository){
  //   this.repository=repository;
  // }

  @Autowired
  private TipoTelefoneService service;

  // public TipoTelefoneController(TipoTelefoneService service){
  //   this.service=service;
  // }

  // listar
  @GetMapping("/lista")
  public List<TipoTelefone> listarTipoTelefones(){
    List<TipoTelefone> tipoTelefones = repository.findAll();
    return tipoTelefones;
  }

  // filtrar 
  @GetMapping(value = "/{codigo}")
  public TipoTelefone findByCodigo(@PathVariable Integer codigo){
  TipoTelefone result = repository.findByCodigo(codigo);
  return result;
}
  // criar
  public TipoTelefone criarTipoTelefone(@RequestBody TipoTelefoneDTO tipoTelefoneDTO){
    return service.criarTipoTelefone(tipoTelefoneDTO);
  }

  // deletar
   
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarTipoTelefone(@PathVariable int codigo){
        service.deletarTipoTelefone(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "TipoTelefone deletado com sucesso!");
        return ResponseEntity.ok(message);

    }

}