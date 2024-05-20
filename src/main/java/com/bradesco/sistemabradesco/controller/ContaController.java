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


import com.bradesco.sistemabradesco.dto.ContaDTO;
import com.bradesco.sistemabradesco.services.ContaService;
import com.bradesco.sistemabradesco.repository.ContaRepository;
import com.bradesco.sistemabradesco.models.Conta;



@RestController
@RequestMapping("/conta")
public class ContaController {
    @Autowired
    private ContaService contaService;

    // public ContaController(ContaService contaService){
    //     this.contaService=contaService;
    // }
    @Autowired
    private ContaRepository contaRepository; 

    // public ContaController(ContaRepository contaRepository){
    //     this.contaRepository=contaRepository;
    // }
    
    /* criando conta */
    @PostMapping("/criar")
    public Conta criarConta(@RequestBody ContaDTO contaDTO){
        return contaService.criarConta(contaDTO);
    }

    /* deletando conta */
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarConta(@PathVariable int codigo){
        contaService.deletarConta(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Conta deletado com sucesso!");
        return ResponseEntity.ok(message);

    }

    /* Listando contas*/
    @GetMapping("/listar")
    public List<Conta> listarContas(){
        return contaService.listarContas();
    }
     
    /* encontrando conta por codigo*/
    @GetMapping("/{codigo}")
    public ResponseEntity<ContaDTO> encontrarPorCodigo(@PathVariable int codigo){
        Conta conta = contaService.encontrarPorCodigo(codigo);
        if(conta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ContaDTO(conta));
    }

    /* encontrando conta por numero */
    @GetMapping("/{numero}")
    public ResponseEntity<ContaDTO> encontrarPorNumero(@PathVariable int numero){
        Conta conta = contaService.encontrarPorNumero(numero);
        if(conta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ContaDTO(conta));
    }
    @GetMapping("/{agencia}")
    public ResponseEntity<List<Conta>> encontrarPorAgencia(@PathVariable Integer agencia){
        List<Conta> contas = contaRepository.findByAgencia(agencia);
        if (contas.isEmpty()) {
            return ResponseEntity.status(404).body(contas);
        }
         return ResponseEntity.ok(contas);
    }

 
}
