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

import com.bradesco.sistemabradesco.dto.ProtocolTypeDTO;
import com.bradesco.sistemabradesco.models.ProtocolType;
import com.bradesco.sistemabradesco.repository.ProtocolTypeRepository;
import com.bradesco.sistemabradesco.services.ProtocolTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/protocolType")
public class ProtocolTypeController {

  @Autowired
  private ProtocolTypeRepository protocolTypeRepository;

  @Autowired
  private ProtocolTypeService protocolTypeService;

  // listar
  @Operation(description = "Lista todos os tipos de protocolo da aplicação.")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Retorna uma ArrayList com todos os tipos de protocolo."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @GetMapping("/listProtocolType")
  public List<ProtocolType> listProtocolTypes() {
    List<ProtocolType> protocolTypes = protocolTypeRepository.findAll();
    return protocolTypes;
  }

  // filtrar
  @Operation(description = "Filtra os Tipos de protocolos pelo código deles.")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Retorna um tipo de protocolo com o seu código."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @GetMapping(value = "/{code}")
  public ProtocolType findByCode(@PathVariable Integer code) {
    ProtocolType result = protocolTypeRepository.findByCode(code);
    return result;
  }

  // criar
  @Operation(description = "Cria um novo tipo de protocolo na aplicação")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Retorna o tipo de protocolo criado com as suas informações."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @PostMapping("/addProtocolType")
  public ProtocolType addProtocolType(@RequestBody ProtocolTypeDTO protocolTypeDTO) {
    return protocolTypeService.addProtocolType(protocolTypeDTO);
  }

  // UPDATE
  // Atualizar tipo de protocolo
  @Operation(description = "Atualiza o tipo de um protocolo.")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Retorna o objeto 'tipo protocolo' atualizado."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @PutMapping("/{code}/type")
  public ResponseEntity<ProtocolType> updateProtocolType(@PathVariable int code,
      @RequestBody ProtocolTypeDTO protocolTypeDTO) {
    ProtocolType updatedType = protocolTypeService.updateProtocolType(code, protocolTypeDTO);
    return ResponseEntity.ok(updatedType);
  }

  // Atualizar dias úteis
  @Operation(description = "Atualiza os dias úteis de um tipo de protocolo.")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Retorna o objeto 'tipo protocolo' com seus dias úteis atualizado."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @PutMapping("/{code}/business-days")
  public ResponseEntity<ProtocolType> updateBusinessDays(@PathVariable int code,
      @RequestBody ProtocolTypeDTO protocolTypeDTO) {
    ProtocolType updatedBusinessDays = protocolTypeService.updateBusinessDays(code, protocolTypeDTO);
    return ResponseEntity.ok(updatedBusinessDays);
  }

  // Atualizar prazo de tratativa
  @Operation(description = "Atualiza o prazo de tratativa de um tipo de protocolo.")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Retorna o objeto 'tipo protocolo' com seu prazo de tratativa atualizado."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @PutMapping("/{code}/treatment-deadline")
  public ResponseEntity<ProtocolType> updateTreatmentDeadline(@PathVariable int code,
      @RequestBody ProtocolTypeDTO protocolTypeDTO) {
    ProtocolType updatedTreatmentDeadline = protocolTypeService.updateTreatmentDeadline(code, protocolTypeDTO);
    return ResponseEntity.ok(updatedTreatmentDeadline);
  }

  // delete
  @Operation(description = "Deleta um tipo de  protocolo da aplicação")
  @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Remove um tipo de protocolo e existe uma mensagem de sucesso."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
  })
  @DeleteMapping("/delete/{code}")
  public ResponseEntity<Object> deleteProtocolType(@PathVariable int code) {
    protocolTypeService.deleteProtocolType(code);
    Map<String, String> message = new HashMap<>();
    message.put("message", "TipoProtocolo deletado com sucesso!");
    return ResponseEntity.ok(message);

  }
}
