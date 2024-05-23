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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/telefoneCliente")
public class TelefoneClienteController {

  @Autowired
  private TelefoneClienteRepository repository;

  @Autowired
  private TelefoneClienteService service;

  // public TelefoneClienteController(TelefoneClienteService service){
  //   this.service=service;
  // }

  // listar
  @Operation(description = "Lista todos os telefones de um cliente")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna uma arrayList com todos os telefones de um cliente."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
  @GetMapping("/lista")
  public List<TelefoneCliente> listarTelefoneCliente(){
    List<TelefoneCliente> telefoneClientes = repository.findAll(); 
    return telefoneClientes;
  }
  // filtrar por cliente??

  // criar
  @Operation(description = "Cria um telefone para um cliente na aplicação.")
  @ApiResponses({

    @ApiResponse(responseCode = "200", description = "Retorna o telefone criado com suas informações."),
    @ApiResponse(responseCode = "400", description = "Bad request.")
 }
)
  @PostMapping("/criarTelefoneCliente")
  public TelefoneCliente criarTelefoneCliente(@RequestBody TelefoneClienteDTO telefoneClienteDTO){
    return service.criarTelefoneCliente(telefoneClienteDTO);
  }

   // deletar
    @Operation(description = "Deleta um telefone de um cliente na aplicação.")
    @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Remove um telefone de um cliente e exite uma mensagem de sucesso."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
   }
  )
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarTelefoneCliente(@PathVariable int codigo){
        service.deletarTelefoneCliente(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "TelefoneCliente deletado com sucesso!");
        return ResponseEntity.ok(message);

    }
}