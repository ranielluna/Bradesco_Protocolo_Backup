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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.bradesco.sistemabradesco.repository.ContaRepository;
import com.bradesco.sistemabradesco.models.Conta;



@RestController
@RequestMapping("/conta")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaRepository contaRepository; 
  
    /* criando conta */
    @Operation(description = "Cria uma conta na aplicação.")
      @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna a conta com suas informações."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @PostMapping("/criar")
    public Conta criarConta(@RequestBody ContaDTO contaDTO){
        return contaService.criarConta(contaDTO);
    }

    /* deletando conta */
    @Operation(description = "Deleta uma conta da aplicação.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Remove a conta e exite uma mensagem de sucesso."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarConta(@PathVariable int codigo){
        contaService.deletarConta(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Conta deletado com sucesso!");
        return ResponseEntity.ok(message);

    }

    /* Listando contas*/
    @Operation(description = "Lista todas as contas da aplicação.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna um array list com as contas e seus dados."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @GetMapping("/listar")
    public List<Conta> listarContas(){
        return contaService.listarContas();
    }
     
    /* encontrando conta por codigo*/
    @Operation(description = "Encontra uma conta por meio do código dela.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna apenas uma conta com seu código."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @GetMapping("/{codigo}")
    public ResponseEntity<ContaDTO> encontrarPorCodigo(@PathVariable int codigo){
        Conta conta = contaService.encontrarPorCodigo(codigo);
        if(conta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ContaDTO(conta));
    }

    /* encontrando conta por numero */
    @Operation(description = "Encontra uma conta por meio do numero dela.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna apenas uma conta com o seu número."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @GetMapping("/{numero}")
    public ResponseEntity<ContaDTO> encontrarPorNumero(@PathVariable int numero){
        Conta conta = contaService.encontrarPorNumero(numero);
        if(conta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ContaDTO(conta));
    }

    @Operation(description = "Encontra uma conta por meio da agência dela.")
    @ApiResponses({

        @ApiResponse(responseCode = "200", description = "Retorna apenas uma conta com a agência."),
        @ApiResponse(responseCode = "400", description = "Bad request.")
     }
    )
    @GetMapping("/{agencia}")
    public ResponseEntity<List<Conta>> encontrarPorAgencia(@PathVariable Integer agencia){
        List<Conta> contas = contaRepository.findByAgencia(agencia);
        if (contas.isEmpty()) {
            return ResponseEntity.status(404).body(contas);
        }
         return ResponseEntity.ok(contas);
    }

 
}
