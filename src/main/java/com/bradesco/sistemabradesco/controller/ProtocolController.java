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


	@GetMapping("/listProtocols")
	public List<Protocol> listProtocols(){
		List<Protocol> Protocols = protocolRepository.findAll();
		return Protocols;
	}

	@GetMapping(value = "/{codigo}")
	public Protocol findByCodigo(@PathVariable Integer codigo) {
		Protocol result = protocolRepository.findByCodigo(codigo);
		return result;
	}

	@GetMapping(value = "/number/{numeroProtocolo}")
	public Protocol findByNumeroProtocolo(@PathVariable Long numeroProtocolo) {
		Protocol result = protocolRepository.findByNumeroProtocolo(numeroProtocolo);
		return result;
	}

    @PostMapping("/openProtocol")
    public Protocol openProtocol(@RequestBody ProtocolDTO protocolDTO) {
        return protocoloService.openProtocol(protocolDTO);

    }

	 
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<Object> deleteProtocol(@PathVariable int codigo){
        protocoloService.deleteProtocol(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Protocolo deletado com sucesso!");
        return ResponseEntity.ok(message);

    }





}
