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

import com.bradesco.sistemabradesco.dto.ProtocoloDTO;
import com.bradesco.sistemabradesco.models.Protocolo;
import com.bradesco.sistemabradesco.repository.ProtocoloRepository;
import com.bradesco.sistemabradesco.services.ProtocoloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/protocolo")
public class ProtocoloController {

	@Autowired
	private ProtocoloRepository repository;

	@GetMapping
	public String paginaInicial() {
		return "Bem-vindo à API de protocolos!";
	}

	@Autowired
	private ProtocoloService protocoloService;

	@Operation(description = "Lista todos protocolos da aplicação.")
	  @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna uma lista de todos os protocolos."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
	@GetMapping("/lista")
	public List<Protocolo> listarProtocolos(){
		List<Protocolo> Protocolos = repository.findAll();
		return Protocolos;
	}

	@Operation(description = "Encontra um protocolo por meio do Código dele.")
	@ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna apenas um protocolo com o código dele."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
	@GetMapping(value = "/{codigo}")
	public Protocolo findByCodigo(@PathVariable Integer codigo) {
		Protocolo result = repository.findByCodigo(codigo);
		return result;
	}

	@Operation(description = "Encontra um protocolo pelo numero dele.")
	@ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna apenas um protocolo com o número dele."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
	@GetMapping(value = "/numero/{numeroProtocolo}")
	public Protocolo findByNumeroProtocolo(@PathVariable Long numeroProtocolo) {
		Protocolo result = repository.findByNumeroProtocolo(numeroProtocolo);
		return result;
	}

	@Operation(description = "Cria um protocolo na aplicação.")
	@ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna o protocolo criado com as suas informações."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @PostMapping("/abrirProtocolo")
    public Protocolo abrirProtocolo(@RequestBody ProtocoloDTO protocoloDTO) {
        return protocoloService.abrirProtocolo(protocoloDTO);

    }

	@Operation(description = "Deleta um protocolo da aplicação.")
	@ApiResponses({

        @ApiResponse(responseCode = "200", description = "Remove um protocolo e exibe uma mensagem de sucesso."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    ) 
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarTipoTelefone(@PathVariable int codigo){
        protocoloService.deletarProtocolo(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Protocolo deletado com sucesso!");
        return ResponseEntity.ok(message);

    }





}
