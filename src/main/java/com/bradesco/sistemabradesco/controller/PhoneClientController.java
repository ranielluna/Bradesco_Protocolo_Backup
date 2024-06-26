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

import com.bradesco.sistemabradesco.dto.PhoneClientDTO;
import com.bradesco.sistemabradesco.models.PhoneClient;
import com.bradesco.sistemabradesco.repository.PhoneClientRepository;
import com.bradesco.sistemabradesco.services.PhoneClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/phoneClient")
public class PhoneClientController {

  @Autowired
  private PhoneClientRepository phoneClientRepository;

  @Autowired
  private PhoneClientService phoneClientService;

  // listar
  @Operation(description = "Lista todos os telefones de um cliente")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Retorna uma arrayList com todos os telefones de um cliente."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @GetMapping("/listPhoneClient")
  public List<PhoneClient> listPhoneClients() {
    List<PhoneClient> phoneClients = phoneClientRepository.findAll();
    return phoneClients;
  }

  // Criar
  @Operation(description = "Cria um telefone para um cliente na aplicação.")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Retorna o telefone criado com suas informações."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @PostMapping("/addphoneClient")
  public PhoneClient addPhoneClient(@RequestBody PhoneClientDTO phoneClientDTO) {
    return phoneClientService.addpPhoneClient(phoneClientDTO);
  }

  // UPDATES
  // Atualizar DDD
  @Operation(description = "Atualiza o DDD de um telefone.")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Retorna o objeto telefone com seu DDD atualizado."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @PutMapping("/{code}/ddd")
  public ResponseEntity<PhoneClient> updateDdd(@PathVariable int code, @RequestBody PhoneClientDTO phoneClientDTO) {
    PhoneClient updatedPhone = phoneClientService.updateDdd(code, phoneClientDTO);
    return ResponseEntity.ok(updatedPhone);
  }

  // Atualizar tipo de telefone
  @Operation(description = "Atualiza o tipo de um telefone.")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Retorna o objeto telefone com seu tipo atualizado."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @PutMapping("/{code}/type")
  public ResponseEntity<PhoneClient> updatePhoneType(@PathVariable int code,
      @RequestBody PhoneClientDTO phoneClientDTO) {
    PhoneClient updatedPhone = phoneClientService.updatePhoneType(code, phoneClientDTO);
    return ResponseEntity.ok(updatedPhone);
  }

  // Atualizar número de telefone
  @Operation(description = "Atualiza o número de um telefone.")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Retorna o objeto telefone com seu número atualizado."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @PutMapping("/{code}/number")
  public ResponseEntity<PhoneClient> updateNumber(@PathVariable int code, @RequestBody PhoneClientDTO phoneClientDTO) {
    PhoneClient updatedPhone = phoneClientService.updateNumber(code, phoneClientDTO);
    return ResponseEntity.ok(updatedPhone);
  }

  // Deletar
  @Operation(description = "Deleta um telefone de um cliente na aplicação.")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Remove um telefone de um cliente e exite uma mensagem de sucesso."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @DeleteMapping("/delete/{code}")
  public ResponseEntity<Object> deletePhoneClient(@PathVariable int code) {
    phoneClientService.deletePhoneClient(code);
    Map<String, String> message = new HashMap<>();
    message.put("message", "TelefoneCliente deletado com sucesso!");
    return ResponseEntity.ok(message);

  }
}