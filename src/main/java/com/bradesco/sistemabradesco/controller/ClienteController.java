package com.bradesco.sistemabradesco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.sistemabradesco.dto.ClienteDTO;
import com.bradesco.sistemabradesco.models.Cliente;
import com.bradesco.sistemabradesco.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /* criando cliente */
    
    @Operation(description = "Cria um novo cliente na aplicação.")
     @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna o cliente com suas informações."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @PostMapping("/criar")
    public Cliente criarCliente(@RequestBody ClienteDTO clienteDTO){
        return clienteService.criarCliente(clienteDTO);
    }

    @Operation(description = "Deleta um cliente por meio do ID.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Remove o objeto cliente e retorna uma mensagem de sucesso."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarCliente(@PathVariable String id){
    clienteService.deletarCliente(id);
    Map<String, String> message = new HashMap<>();
    message.put("message", "Cliente deletado com sucesso!");
    return ResponseEntity.ok(message);
}

    /* listando cliente */
    @Operation(description = "Lista todos os clientes criados na aplicação.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Mostra todos os clientes em uma lista."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @GetMapping("/listar")
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    /* buscando clientes por variaves */
    @Operation(description = "Busca clientes pelo CPF.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna apenas um cliente com seu CPF."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> encontrarPorCpf(@PathVariable String cpf) {
        Cliente cliente = clienteService.encontrarPorCpf(cpf);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClienteDTO(cliente)); 
    }

    @Operation(description = "Busca clientes pelo CNPJ.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna apenas um cliente com seu CNPJ."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<ClienteDTO> encontrarPorCnpj(@PathVariable String cnpj) {
        Cliente cliente = clienteService.encontrarPorCnpj(cnpj);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClienteDTO(cliente)); 
    }

    @Operation(description = "Busca cliente pelo Nome.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna apenas um cliente com seu Nome."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @GetMapping("/nome/{nome}")
    public ResponseEntity<ClienteDTO> encontrarPorNome(@PathVariable String nome) {
        Cliente cliente = clienteService.encontrarPorNome(nome);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClienteDTO(cliente)); 
    }

    @Operation(description = "Busca clientes pela Razão Social.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna apenas um cliente com sua Razão Social."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @GetMapping("/razao-social/{razaoSocial}")
    public ResponseEntity<ClienteDTO> encontrarPorRazaoSocial(@PathVariable String razaoSocial) {
        Cliente cliente = clienteService.encontrarPorRazaoSocial(razaoSocial);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClienteDTO(cliente)); 
    }
}
