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

import com.bradesco.sistemabradesco.dto.PhoneTypeDTO;
import com.bradesco.sistemabradesco.models.PhoneType;
import com.bradesco.sistemabradesco.repository.PhoneTypeRepository;
import com.bradesco.sistemabradesco.services.PhoneTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("api/phoneType")
public class PhoneTypeController {
  
  @Autowired
  private PhoneTypeRepository phoneTypeRepository;

  @Autowired
  private PhoneTypeService phoneTypeService;

  // listar
  @Operation(description = "Lista os tipos de telefone presentes na aplicação.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna uma ArrayList com todos os tipos de telefone."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
  @GetMapping("/listPhoneType")
  public List<PhoneType> listPhoneTypes(){
    List<PhoneType> phoneTypes = phoneTypeRepository.findAll();
    return phoneTypes;
  }

  // filtrar 
  @Operation(description = "Filtra os tipos de telefones por meio do código deles.")
  @ApiResponses({

    @ApiResponse(responseCode = "200", description = "Retorna um tipo de telefone por meio do código deles."),
    @ApiResponse(responseCode = "400", description = "Bad request.")
 }
)
  @GetMapping(value = "/{code}")
  public PhoneType findByCode(@PathVariable Integer code){
  PhoneType result = phoneTypeRepository.findByCode(code);
  return result;
}


  // criar
  @Operation(description = "Cria um novo tipo de telefone na aplicação.")
  @ApiResponses({

    @ApiResponse(responseCode = "200", description = "Retorna apenas um objeto tipo de telefone com as suas informações."),
    @ApiResponse(responseCode = "400", description = "Bad request.")
 }
)
  public PhoneType addPhoneType(@RequestBody PhoneTypeDTO phoneTypeDTO){
    return phoneTypeService.addPhoneType(phoneTypeDTO);
  }



  // deletar
    @Operation(description = "Deleta um tipo de telefone da aplicação.")
    @ApiResponses({

      @ApiResponse(responseCode = "200", description = "Remove um objeto tipoTelefone por meio do seu código e exime uma mensagem de sucesso."),
      @ApiResponse(responseCode = "400", description = "Bad request.")
   }
  )
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> deletePhoneType(@PathVariable int code){
        phoneTypeService.deletePhoneType(code);
        Map<String, String> message = new HashMap<>();
        message.put("message", "TipoTelefone deletado com sucesso!");
        return ResponseEntity.ok(message);

    }

}