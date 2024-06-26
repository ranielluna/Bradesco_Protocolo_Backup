package com.bradesco.sistemabradesco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.sistemabradesco.dto.ClientDTO;
import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.services.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // criando cliente 
    @Operation(description = "Cria um novo cliente na aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna o objeto cliente com suas informações."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PostMapping("/addClient")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }


    // Listando cliente 
    @Operation(description = "Lista todos os clientes criados na aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Mostra todos os objetos clientes em um arrayList."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping("/listClients")
    public List<Client> listClients() {
        return clientService.listClients();
    }

    // Buscando clientes por variaves 
    @Operation(description = "Busca clientes pelo CPF.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto cliente com seu CPF."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClientDTO> findByCpf(@PathVariable String cpf) {
        Client client = clientService.findByCpf(cpf);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClientDTO(client));
    }

    @Operation(description = "Busca clientes pelo CNPJ.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto cliente com seu CNPJ."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<ClientDTO> findByCnpj(@PathVariable String cnpj) {
        Client client = clientService.findByCnpj(cnpj);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClientDTO(client));
    }

    @Operation(description = "Busca cliente pelo Nome.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto cliente com seu Nome."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<ClientDTO> findByName(@PathVariable String name) {
        Client client = clientService.findByName(name);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClientDTO(client));
    }

    @Operation(description = "Busca clientes pela Razão Social.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto cliente com sua Razão Social."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping("/companyName/{companyName}")
    public ResponseEntity<ClientDTO> findByCompanyName(@PathVariable String companyName) {
        Client client = clientService.findByCompanyName(companyName);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClientDTO(client));
    }

    // UPDATES PESSOA FISICA
    // Atualizar nome do cliente
    @Operation(description = "Atualiza o nome de um cliente.É indispensável para casos de erros no cadastro do cliente.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto cliente com seu nome atualizado."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{cpf}/name")
    public ResponseEntity<Client> updateClientName(@PathVariable String cpf, @RequestBody ClientDTO clientDTO) {
        Client updatedClient = clientService.updateClientName(cpf, clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    // Atualizar tipo do cliente
    @Operation(description = "Atualiza o tipo de um cliente pessoa física.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto cliente com seu tipo atualizado."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{cpf}/type")
    public ResponseEntity<Client> updateClientType(@PathVariable String cpf, @RequestBody ClientDTO clientDTO) {
        Client updatedClient = clientService.updateClientType(cpf, clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    // Atualizar email do cliente
    @Operation(description = "Atualiza o email de um cliente.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto cliente com seu email atualizado."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{cpf}/email")
    public ResponseEntity<Client> updateClientEmail(@PathVariable String cpf, @RequestBody ClientDTO clientDTO) {
        Client updatedClient = clientService.updateClientEmail(cpf, clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    // Atualizar profissão do cliente
    @Operation(description = "Atualiza a profissão de um cliente.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto cliente com sua profissão atualizada."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{cpf}/profession")
    public ResponseEntity<Client> updateClientPosition(@PathVariable String cpf, @RequestBody ClientDTO clientDTO) {
        Client updatedClient = clientService.updateClientPosition(cpf, clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    // UPDATES PESSOA JURIDICA
    // Atualizar nome da empresa
    @Operation(description = "Atualiza a razão social de um cliente.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto cliente com sua Razão Social atualizada."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{cnpj}/name")
    public ResponseEntity<Client> updateClientCompany(@PathVariable String cnpj, @RequestBody ClientDTO clientDTO) {
        Client updatedCompany = clientService.updateClientCompany(cnpj, clientDTO);
        return ResponseEntity.ok(updatedCompany);
    }

    // Atualizar tipo do cliente
    @Operation(description = "Atualiza o tipo de um cliente pessoa jurídica")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto cliente com seu tipo atualizado."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{cnpj}/type")
    public ResponseEntity<Client> updateCompanyTypeClient(@PathVariable String cnpj, @RequestBody ClientDTO clientDTO) {
        Client updatedCompany = clientService.updateCompanyTypeClient(cnpj, clientDTO);
        return ResponseEntity.ok(updatedCompany);
    }

    // Atualizar email da empresa
    @Operation(description = "Atualiza o email de um cliente empresa.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto cliente com seu email atualizado."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{cnpj}/email")
    public ResponseEntity<Client> updateCompanyEmail(@PathVariable String cnpj, @RequestBody ClientDTO clientDTO) {
        Client updatedCompany = clientService.updateCompanyEmail(cnpj, clientDTO);
        return ResponseEntity.ok(updatedCompany);
    }

    // Atualizar profissão da empresa
    @Operation(description = "Atualiza o ramo de um cliente empresa.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto cliente com sua profissão atualizada."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{cnpj}/profession")
    public ResponseEntity<Client> updateCompanyProfession(@PathVariable String cnpj, @RequestBody ClientDTO clientDTO) {
        Client updatedCompany = clientService.updateCompanyProfession(cnpj, clientDTO);
        return ResponseEntity.ok(updatedCompany);
    }

    // Deletando cliente 
    @Operation(description = "Deleta um cliente por meio do ID.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Remove o objeto cliente e retorna uma mensagem de sucesso."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> deleteClient(@PathVariable String code) {
        clientService.deleteClient(code);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Cliente deletado com sucesso");
        return ResponseEntity.ok(message);
    }

    

    
}
