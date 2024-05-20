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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    // public ClienteController(ClienteService clienteService){
    //     this.clienteService=clienteService;
    // }

    /* criando cliente */
    @PostMapping("/criar")
    public Cliente criarCliente(@RequestBody ClienteDTO clienteDTO){
        return clienteService.criarCliente(clienteDTO);
    }

    /* deletando cliente */
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarCliente(@PathVariable String id){
    clienteService.deletarCliente(id);
    Map<String, String> message = new HashMap<>();
    message.put("message", "Cliente deletado com sucesso");
    return ResponseEntity.ok(message);
}

    /* listando cliente */
    @GetMapping("/listar")
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    /* buscando clientes por variaves */
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> encontrarPorCpf(@PathVariable String cpf) {
        Cliente cliente = clienteService.encontrarPorCpf(cpf);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClienteDTO(cliente)); 
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<ClienteDTO> encontrarPorCnpj(@PathVariable String cnpj) {
        Cliente cliente = clienteService.encontrarPorCnpj(cnpj);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClienteDTO(cliente)); 
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ClienteDTO> encontrarPorNome(@PathVariable String nome) {
        Cliente cliente = clienteService.encontrarPorNome(nome);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClienteDTO(cliente)); 
    }

    @GetMapping("/razao-social/{razaoSocial}")
    public ResponseEntity<ClienteDTO> encontrarPorRazaoSocial(@PathVariable String razaoSocial) {
        Cliente cliente = clienteService.encontrarPorRazaoSocial(razaoSocial);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClienteDTO(cliente)); 
    }
}
