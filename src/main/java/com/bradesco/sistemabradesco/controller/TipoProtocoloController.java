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

import com.bradesco.sistemabradesco.dto.TipoProtocoloDTO;
import com.bradesco.sistemabradesco.models.TipoProtocolo;
import com.bradesco.sistemabradesco.repository.TipoProtocoloRepository;
import com.bradesco.sistemabradesco.services.TipoProtocoloService;

@RestController
@RequestMapping("api/tipoProtocolo")
public class TipoProtocoloController {
  
  @Autowired
  private TipoProtocoloRepository repository;

  // public TipoProtocoloController(TipoProtocoloRepository repository){
  //   this.repository=repository;
  // }
  
  @Autowired
  private TipoProtocoloService service;

  // public TipoProtocoloController(TipoProtocoloService service){
  //   this.service=service;
  // }


  // listar
  @GetMapping("/lista")
  public List<TipoProtocolo> listarTipoProtocolos(){
    List<TipoProtocolo> tipoProtocolos = repository.findAll();
    return tipoProtocolos;
  }
  // filtrar 
  @GetMapping(value = "/{codigo}")
  public TipoProtocolo findByCodigo(@PathVariable Integer codigo){
    TipoProtocolo result = repository.findByCodigo(codigo);
    return result;
  }

  // criar
  @PostMapping("/criarTipoProtocolo")
  public TipoProtocolo criarTipoProtocolo(@RequestBody TipoProtocoloDTO tipoProtocoloDTO){
    return service.criarTipoProtocolo(tipoProtocoloDTO);
  }

   
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarTipoProtocolo(@PathVariable int codigo){
        service.deletarTipoProtocolo(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "TipoProtocolo deletado com sucesso!");
        return ResponseEntity.ok(message);

    }
}
