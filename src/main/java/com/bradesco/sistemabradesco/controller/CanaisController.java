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

import com.bradesco.sistemabradesco.dto.CanaisDTO;
import com.bradesco.sistemabradesco.models.Canais;
import com.bradesco.sistemabradesco.services.CanaisService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/canais")
public class CanaisController {
    
    @Autowired
    private CanaisService canaisService;

    /* usando as anotações swagger */
    @Operation(description = "Lista os canais que existem na aplicação.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna os canais existentes."),
        @ApiResponse(responseCode = "400", description = "Canais não encontrados.")
    }

    )
    @GetMapping("/listar")
    public List<Canais> listarCanais() {
        return canaisService.listarCanais();
    }

    @Operation(description = "Cria um novo canal para a aplicação.")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "Retorna o canal que foi criado com suas informações."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
    }

    )
    @PostMapping("/criar")
    public Canais criarCanal(@RequestBody CanaisDTO canaisDTO) {
        return canaisService.criarCanal(canaisDTO);
    }


    @Operation(description = "Deleta um canal por meio do seu codigo.")  
    @ApiResponses(
        @ApiResponse(responseCode = "200", description = "Canal deletado com sucesso!")
    ) 
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarTipoTelefone(@PathVariable int codigo){
        canaisService.deletarCanal(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Canal deletado com sucesso!");
        return ResponseEntity.ok(message);

    }
}