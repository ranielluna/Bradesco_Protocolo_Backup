package com.bradesco.sistemabradesco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private TipoTelefoneService service;

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

}
