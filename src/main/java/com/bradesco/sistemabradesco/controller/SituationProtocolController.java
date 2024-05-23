package com.bradesco.sistemabradesco.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.sistemabradesco.dto.SituationProtocolDTO;
import com.bradesco.sistemabradesco.models.SituationProtocol;
import com.bradesco.sistemabradesco.services.SituationProtocolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("api/situationProtocol")
public class SituationProtocolController {
    @Autowired
    private SituationProtocolService situationProtocolService;

    @Operation(description = "Cria uma situação de protocolo na aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna uma situação de protocolo com suas informações."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PostMapping("/addSituationProtocol")
    public SituationProtocol addSituationProtocol(@RequestBody SituationProtocolDTO situationProtocolDTO) {
        return situationProtocolService.addSituationProtocol(situationProtocolDTO);
    }

    @Operation(description = "Deleta uma situação de protocolo na aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Deleta uma situação de protocolo e exibe uma mensagem de sucesso."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> deleteSituationProtocol(@PathVariable int code) {
        situationProtocolService.deleteSituationProtocol(code);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Funcionário deletado com sucesso!");
        return ResponseEntity.ok(message);

    }

}
