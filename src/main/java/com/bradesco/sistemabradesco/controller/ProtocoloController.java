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

@RestController
@RequestMapping("/api/protocolo")
public class ProtocoloController {

	@Autowired
	private ProtocoloRepository repository;

	// public ProtocoloController(ProtocoloRepository repository){
	// 	this.repository=repository;
	// }

	@GetMapping
	public String paginaInicial() {
		return "Bem-vindo à API de protocolos!";
	}

	@Autowired
	private ProtocoloService protocoloService;

	@GetMapping("/lista")
	public List<Protocolo> listarProtocolos(){
		List<Protocolo> Protocolos = repository.findAll();
		return Protocolos;
	}

	@GetMapping(value = "/{codigo}")
	public Protocolo findByCodigo(@PathVariable Integer codigo) {
		Protocolo result = repository.findByCodigo(codigo);
		return result;
	}

	@GetMapping(value = "/numero/{numeroProtocolo}")
	public Protocolo findByNumeroProtocolo(@PathVariable Long numeroProtocolo) {
		Protocolo result = repository.findByNumeroProtocolo(numeroProtocolo);
		return result;
	}

    @PostMapping("/abrirProtocolo")
    public Protocolo abrirProtocolo(@RequestBody ProtocoloDTO protocoloDTO) {
        return protocoloService.abrirProtocolo(protocoloDTO);

    }

	 
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarTipoTelefone(@PathVariable int codigo){
        protocoloService.deletarProtocolo(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Protocolo deletado com sucesso!");
        return ResponseEntity.ok(message);

    }





}
