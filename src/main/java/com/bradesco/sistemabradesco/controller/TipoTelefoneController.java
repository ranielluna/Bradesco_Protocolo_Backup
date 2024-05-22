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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("api/tipoTelefone")
public class TipoTelefoneController {
  
  @Autowired
  private TipoTelefoneRepository repository;

  @Autowired
  private TipoTelefoneService service;

  // listar
  @Operation(description = "Lista os tipos de telefone presentes na aplicação.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna uma ArrayList com todos os tipos de telefone."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
  @GetMapping("/lista")
  public List<TipoTelefone> listarTipoTelefones(){
    List<TipoTelefone> tipoTelefones = repository.findAll();
    return tipoTelefones;
  }

  // filtrar 
  @Operation(description = "Filtra os tipos de telefones por meio do código deles.")
  @ApiResponses({

    @ApiResponse(responseCode = "200", description = "Retorna um tipo de telefone por meio do código deles."),
    @ApiResponse(responseCode = "400", description = "Bad request.")
 }
)
  @GetMapping(value = "/{codigo}")
  public TipoTelefone findByCodigo(@PathVariable Integer codigo){
  TipoTelefone result = repository.findByCodigo(codigo);
  return result;
}
  // criar
  @Operation(description = "Cria um novo tipo de telefone na aplicação.")
  @ApiResponses({

    @ApiResponse(responseCode = "200", description = "Retorna apenas um objeto tipo de telefone com as suas informações."),
    @ApiResponse(responseCode = "400", description = "Bad request.")
 }
)
  public TipoTelefone criarTipoTelefone(@RequestBody TipoTelefoneDTO tipoTelefoneDTO){
    return service.criarTipoTelefone(tipoTelefoneDTO);
  }

  // deletar
    @Operation(description = "Deleta um tipo de telefone da aplicação.")
    @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Remove um objeto tipoTelefone por meio do seu código e exime uma mensagem de sucesso."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
   }
  )
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarTipoTelefone(@PathVariable int codigo){
        service.deletarTipoTelefone(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "TipoTelefone deletado com sucesso!");
        return ResponseEntity.ok(message);

    }

}