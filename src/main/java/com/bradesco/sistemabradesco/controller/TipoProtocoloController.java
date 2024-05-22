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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/tipoProtocolo")
public class TipoProtocoloController {
  
  @Autowired
  private TipoProtocoloRepository repository;

  @Autowired
  private TipoProtocoloService service;

  // listar
  @Operation(description = "Lista todos os tipos de protocolo da aplicação.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna uma ArrayList com todos os tipos de protocolo."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
  @GetMapping("/lista")
  public List<TipoProtocolo> listarTipoProtocolos(){
    List<TipoProtocolo> tipoProtocolos = repository.findAll();
    return tipoProtocolos;
  }
  // filtrar 
  @Operation(description = "Filtra os Tipos de protocolos pelo código deles.")
  @ApiResponses({

    @ApiResponse(responseCode = "200", description = "Retorna um tipo de protocolo com o seu código."),
    @ApiResponse(responseCode = "400", description = "Bad request.")
 }
)
  @GetMapping(value = "/{codigo}")
  public TipoProtocolo findByCodigo(@PathVariable Integer codigo){
    TipoProtocolo result = repository.findByCodigo(codigo);
    return result;
  }

  // criar
  @Operation(description = "Cria um novo tipo de protocolo na aplicação")
  @ApiResponses({

    @ApiResponse(responseCode = "200", description = "Retorna o tipo de protocolo criado com as suas informações."),
    @ApiResponse(responseCode = "400", description = "Bad request.")
 }
)
  @PostMapping("/criarTipoProtocolo")
  public TipoProtocolo criarTipoProtocolo(@RequestBody TipoProtocoloDTO tipoProtocoloDTO){
    return service.criarTipoProtocolo(tipoProtocoloDTO);
  }

  @Operation(description = "Deleta um tipo de  protocolo da aplicação")
  @ApiResponses({

    @ApiResponse(responseCode = "200", description = "Remove um tipo de protocolo e existe uma mensagem de sucesso."),
    @ApiResponse(responseCode = "400", description = "Bad request.")
 }
)
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarTipoProtocolo(@PathVariable int codigo){
        service.deletarTipoProtocolo(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "TipoProtocolo deletado com sucesso!");
        return ResponseEntity.ok(message);

    }
}
