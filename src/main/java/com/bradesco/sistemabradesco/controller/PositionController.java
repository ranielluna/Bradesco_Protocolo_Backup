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

import com.bradesco.sistemabradesco.dto.PositionDTO;
import com.bradesco.sistemabradesco.models.Position;
import com.bradesco.sistemabradesco.services.PositionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/positions")
public class PositionController {
    
    @Autowired
    private PositionService positionService;
    // public CargoController(CargoService cargoService){
    //     this.cargoService=cargoService;
    // }
    
    @Operation(description = "Lista os cargos presentes na aplicação.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna todos os cargos presentes"),
        @ApiResponse(responseCode = "400", description = "Cargo não encontrado.")
     }
    )
    @GetMapping("/listar")
    public List<Position> listPositions() {
        return positionService.listPositions();
    }

    @Operation(description = "Criar um novo cargo na aplicação.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna o cargo com o código dele."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @PostMapping("/criar")
    public Position criarCargo(@RequestBody PositionDTO positionDTO) {
        return positionService.addPosition(positionDTO);
    }

    @Operation(description = "Deleta um cargo da aplicação por meio do codigo do cargo.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Remove o cargo e retorna uma menssagem de sucesso."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )     
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<Object> deletePosition(@PathVariable int codigo){
        positionService.deletePosition(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Cargo deletado com sucesso!");
        return ResponseEntity.ok(message);

    }
}