package com.bradesco.sistemabradesco.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.sistemabradesco.dto.ProtocolDTO;
import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.models.SituationProtocol;
import com.bradesco.sistemabradesco.repository.ClientRepository;
import com.bradesco.sistemabradesco.repository.DepartmentRepository;
import com.bradesco.sistemabradesco.repository.ProtocolRepository;
import com.bradesco.sistemabradesco.repository.SituationProtocolRepository;
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
	private ProtocolService protocolService;

	@Autowired
	private SituationProtocolRepository situationProtocolRepository;

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
	public Protocol findByCode(@PathVariable Integer code) {
		Protocol result = protocolRepository.findByCode(code);
		return result;
	}



	
	
	/*@Operation(description = "Encontra um protocolo pelo numero dele.")
	@ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna apenas um protocolo com o número dele."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
	@GetMapping(value = "/number/{protocolNumber}")
	public Protocol findByProtocolNumber(@PathVariable Long protocolNumber) {
		Protocol result = protocolRepository.findByProtocolNumber(protocolNumber);
		return result;
	}*/
	@GetMapping(value = "/number/{protocolNumber}")
public ResponseEntity<Protocol> findByProtocolNumber(@PathVariable Long protocolNumber) {
    Optional<Protocol> result = protocolRepository.findByProtocolNumber(protocolNumber);
    if (result.isPresent()) {
        return ResponseEntity.ok(result.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}
	
	
	@Operation(description = "Listar protocolos pelo status.")
	@ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna os protocolos que estão com determinado status."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
	@GetMapping("/status")
	public List<Protocol> listProtocolStatus(@RequestParam String status) {
			return protocolService.listProtocolStatus(status);
	}

	//http://localhost:8080/api/protocol/status?status=Em%20an%C3%A1lise




	@Operation(description = "Cria um protocolo na aplicação.")
	@ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna o protocolo criado com as suas informações."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
		)
    @PostMapping("/openProtocol")
    public Protocol openProtocol(@RequestBody ProtocolDTO protocolDTO) {
        return protocolService.openProtocol(protocolDTO);

    }

	 
		@Operation(description = "Deleta um protocolo da aplicação.")
		@ApiResponses({
	
					@ApiResponse(responseCode = "200", description = "Remove um protocolo e exibe uma mensagem de sucesso."),
					@ApiResponse(responseCode = "400", description = "Bad request.")
			 }
			) 
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> deleteProtocol(@PathVariable int code){
        protocolService.deleteProtocol(code);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Protocolo deletado com sucesso!");
        return ResponseEntity.ok(message);

    }



		/*@GetMapping("/responsavel/{number}")
    public ResponseEntity<String> getEmployee(@PathVariable Long number) {
        Protocol protocol = protocolRepository.findByProtocolNumber(number);

        if (protocol != null) {
            SituationProtocol protocolSituation = situationProtocolRepository.findByProtocol(protocol);

            if (protocolSituation != null && protocolSituation.getEmployee() != null) {
						 String nameEmployee = protocolSituation.getEmployee().getName();
							return ResponseEntity.ok(nameEmployee);
            } else {
                return ResponseEntity.status(404).body("Funcionário não encontrado");
            }
        } else {
            return ResponseEntity.status(404).body("Protocolo não encontrado");
        }
    }*/

		@GetMapping("/responsavel/{number}")
public ResponseEntity<String> getEmployee(@PathVariable Long number) {
    Optional<Protocol> protocolOpt = protocolRepository.findByProtocolNumber(number);
    if (protocolOpt.isPresent()) {
        Protocol protocol = protocolOpt.get();
        SituationProtocol protocolSituation = situationProtocolRepository.findByProtocol(protocol);

        if (protocolSituation!= null && protocolSituation.getEmployee()!= null) {
            String nameEmployee = protocolSituation.getEmployee().getName();
            return ResponseEntity.ok(nameEmployee);
        } else {
            return ResponseEntity.status(404).body("Funcionário não encontrado");
        }
    } else {
        return ResponseEntity.status(404).body("Protocolo não encontrado");
    }
}

	
		//Verifica os protocolos que nao estão em situação protocolo
		@GetMapping("/situationless")
		public List<Protocol> listProtocolNotSituation() {
			List<Protocol> Protocols = protocolService.findUnregistered();
			return Protocols;
		}


		
		@Operation(description = "Atualiza um protocolo na aplicação.")
		@ApiResponses({
				@ApiResponse(responseCode = "200", description = "Retorna o protocolo atualizado com as suas informações."),
				@ApiResponse(responseCode = "400", description = "Bad request.")
		})
		@PutMapping("/update/{number}")
		public ResponseEntity<Protocol> updateProtocol(@PathVariable Long number, @RequestBody ProtocolDTO protocolDTO) {

			//Optional<Protocol> optionalProtocol = protocolService.findById(code);
			Optional<Protocol> optionalProtocol = protocolRepository.findByProtocolNumber(number);
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
	
				protocolService.save(protocol);
				return ResponseEntity.ok(protocol);
			} else {
				return ResponseEntity.notFound().build();
			}
		}
	
		@PutMapping("/final/{number}")
		public ResponseEntity<Protocol> finalUpdateStatusProtocol(@PathVariable Long number){
			try {
				Protocol update = protocolService.finalUpdateStatusProtocol(number);
				return ResponseEntity.ok(update);
			}catch (RuntimeException e) {
				return ResponseEntity.notFound().build();
		}
		}

		@PutMapping("/updateStatus/{number}")
		public ResponseEntity<Protocol> updateStatusProtocol(@PathVariable Long number,@RequestBody ProtocolDTO protocolDTO){
			try {
				Protocol update = protocolService.updateStatusProtocol(number, protocolDTO);
				return ResponseEntity.ok(update);
			}catch (RuntimeException e) {
				return ResponseEntity.notFound().build();
		}
		}




}//class