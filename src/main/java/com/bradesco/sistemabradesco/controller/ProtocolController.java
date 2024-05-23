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

import com.bradesco.sistemabradesco.dto.ProtocolDTO;
import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.repository.ProtocolRepository;
import com.bradesco.sistemabradesco.services.ProtocolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/protocol")
public class ProtocolController {

	@Autowired
	private ProtocolRepository protocolRepository;

	@Autowired
	private ProtocolService protocoloService;



	@GetMapping
	public String home() {
		return "Bem-vindo à API de protocolos!";
	}



	@Operation(description = "Lista todos protocolos da aplicação.")
	  @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna uma lista de todos os protocolos."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
	@GetMapping("/listProtocols")
	public List<Protocol> listProtocols(){
		List<Protocol> Protocols = protocolRepository.findAll();
		return Protocols;
	}


	@Operation(description = "Encontra um protocolo por meio do Código dele.")
	@ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna apenas um protocolo com o código dele."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
	@GetMapping(value = "/{code}")
	public Protocol findByCodigo(@PathVariable Integer code) {
		Protocol result = protocolRepository.findByCodigo(code);
		return result;
	}


	@Operation(description = "Encontra um protocolo pelo numero dele.")
	@ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna apenas um protocolo com o número dele."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
	@GetMapping(value = "/number/{numeroProtocolo}")
	public Protocol findByNumeroProtocolo(@PathVariable Long numeroProtocolo) {
		Protocol result = protocolRepository.findByNumeroProtocolo(numeroProtocolo);
		return result;
	}



	@Operation(description = "Cria um protocolo na aplicação.")
	@ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna o protocolo criado com as suas informações."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
		)
    @PostMapping("/openProtocol")
    public Protocol openProtocol(@RequestBody ProtocolDTO protocolDTO) {
        return protocoloService.openProtocol(protocolDTO);

    }

	 
		@Operation(description = "Deleta um protocolo da aplicação.")
		@ApiResponses({
	
					@ApiResponse(responseCode = "200", description = "Remove um protocolo e exibe uma mensagem de sucesso."),
					@ApiResponse(responseCode = "400", description = "Bad request.")
			 }
			) 
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<Object> deleteProtocol(@PathVariable int codigo){
        protocoloService.deleteProtocol(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Protocolo deletado com sucesso!");
        return ResponseEntity.ok(message);

    }





}
