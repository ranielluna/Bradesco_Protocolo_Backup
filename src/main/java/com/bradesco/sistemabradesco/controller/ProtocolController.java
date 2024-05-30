package com.bradesco.sistemabradesco.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.bradesco.sistemabradesco.dto.ClientDTO;
import com.bradesco.sistemabradesco.dto.DepartmentDTO;
import com.bradesco.sistemabradesco.models.*;
import com.bradesco.sistemabradesco.repository.ClientRepository;
import com.bradesco.sistemabradesco.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bradesco.sistemabradesco.dto.ProtocolDTO;
import com.bradesco.sistemabradesco.repository.ProtocolRepository;
import com.bradesco.sistemabradesco.repository.EmployeeRepository;
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

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ClientRepository clientRepository;


	@GetMapping
	public String home() {
		return "Bem-vindo à API de protocolos!";
	}



	@Operation(description = "Lista todos protocolos da aplicação.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Retorna uma lista de todos os protocolos."),
			@ApiResponse(responseCode = "400", description = "Bad request.")
	})
	@GetMapping("/listProtocols")
	public List<Protocol> listProtocols() {
		return protocolRepository.findAll();
	}

	@Operation(description = "Encontra um protocolo por meio do Código dele.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Retorna apenas um protocolo com o código dele."),
			@ApiResponse(responseCode = "400", description = "Bad request.")
	})
	@GetMapping(value = "/{code}")
	public Protocol findByCode(@PathVariable int code) {
		return protocolRepository.findByCode(code);
	}

	@Operation(description = "Encontra um protocolo pelo numero dele.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Retorna apenas um protocolo com o número dele."),
			@ApiResponse(responseCode = "400", description = "Bad request.")
	})
	@GetMapping(value = "/number/{protocolNumber}")
	public Protocol findByProtocolNumber(@PathVariable Long protocolNumber) {
		return protocolRepository.findByProtocolNumber(protocolNumber);
	}

	@Operation(description = "Cria um protocolo na aplicação.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Retorna o protocolo criado com as suas informações."),
			@ApiResponse(responseCode = "400", description = "Bad request.")
	})
	@PostMapping("/openProtocol")
	public Protocol openProtocol(@RequestBody ProtocolDTO protocolDTO) {
		return protocoloService.openProtocol(protocolDTO);
	}

	@Operation(description = "Deleta um protocolo da aplicação.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Remove um protocolo e exibe uma mensagem de sucesso."),
			@ApiResponse(responseCode = "400", description = "Bad request.")
	})
	@DeleteMapping("/delete/{code}")
	public ResponseEntity<Object> deleteProtocol(@PathVariable int code) {
		protocoloService.deleteProtocol(code);
		Map<String, String> message = new HashMap<>();
		message.put("message", "Protocolo deletado com sucesso!");
		return ResponseEntity.ok(message);
	}

	@GetMapping("/client/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable String id) {
		Optional<Client> optionalClient = clientRepository.findById(id);
		if (optionalClient.isPresent()) {
			return ResponseEntity.ok(optionalClient.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(description = "Atualiza um protocolo na aplicação.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Retorna o protocolo atualizado com as suas informações."),
			@ApiResponse(responseCode = "400", description = "Bad request.")
	})

	@PutMapping("/update/{code}")
	public ResponseEntity<Protocol> updateProtocol(@PathVariable int code, @RequestBody ProtocolDTO protocolDTO) {
		Optional<Protocol> optionalProtocol = protocoloService.findById(code);
		if (optionalProtocol.isPresent()) {
			Protocol protocol = optionalProtocol.get();

			if (protocolDTO.getClient() != null) {
				Client clientDTO = protocolDTO.getClient();
				String cpfCliente = clientDTO.getCpf();
				Optional<Client> optionalClient = clientRepository.findById(cpfCliente);
				Client client;
				if (optionalClient.isPresent()) {
					client = optionalClient.get();
				} else {
					client = new Client();
					BeanUtils.copyProperties(clientDTO, client);
					clientRepository.save(client);
				}
				protocol.setClient(client);
			}

			if (protocolDTO.getInitialReceiptDate() == null) {
				protocol.setInitialReceiptDate(LocalDate.now());
			} else {
				protocol.setInitialReceiptDate(protocolDTO.getInitialReceiptDate());
			}

			if (protocolDTO.getDepartment() != null) {
				Department departmentDTO = protocolDTO.getDepartment();
				Optional<Department> optionalDepartment = departmentRepository.findById(departmentDTO.getCode());
				Department department;
				if (optionalDepartment.isPresent()) {
					department = optionalDepartment.get();
				} else {
					department = new Department();
					BeanUtils.copyProperties(departmentDTO, department);
					departmentRepository.save(department);
				}
				protocol.setDepartment(department);
			}

			protocol.setPropensaoBacen(protocolDTO.isPropensaoBacen());
			protocol.setExpedite(protocolDTO.isExpedite());
			protocol.setDue(protocolDTO.isDue());
			protocol.setJustified(protocolDTO.isJustified());

			protocoloService.save(protocol);
			return ResponseEntity.ok(protocol);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}

