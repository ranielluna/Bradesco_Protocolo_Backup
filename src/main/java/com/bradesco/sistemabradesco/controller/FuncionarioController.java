package com.bradesco.sistemabradesco.controller;

import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bradesco.sistemabradesco.dto.FuncionarioDTO;
import com.bradesco.sistemabradesco.models.Funcionario;
import com.bradesco.sistemabradesco.repository.FuncionarioRepository;
import com.bradesco.sistemabradesco.services.FuncionarioService;


@RestController
@RequestMapping("/api/acesso")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FuncionarioService funcionarioService;

    // public FuncionarioController(FuncionarioRepository repository){
    //     this.repository=repository;
    // }

    
    // login codigos = i025368 - i054867 - i147857
    //senha = 010203

    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Funcionario funcionario) {
        String codigo = funcionario.getCodigo();
        String senha = funcionario.getSenha();
        
        funcionario = repository.findByCodigo(codigo);
        if (funcionario != null) {
            if (funcionario.getSenha().equals(senha)) {
                return new ResponseEntity<>("Acesso liberado", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Acesso negado - Dados inválidos", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Acesso negado - Usuário não encontrado", HttpStatus.UNAUTHORIZED);
        }
        
        
    }
    
    /* criando funcionario */
    @PostMapping("/criar")
    public Funcionario criarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO){
        return funcionarioService.criarFuncionario(funcionarioDTO);
        
    }
    
    //   /* criando conta */
    //   @PostMapping("/criar")
    //   public Conta criarConta(@RequestBody ContaDTO contaDTO){
    //       return contaService.criarConta(contaDTO);
    //   }
     
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarFuncionario(@PathVariable String codigo){
        funcionarioService.deletarFuncionario(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Funcionário deletado com sucesso!");
        return ResponseEntity.ok(message);

    }







}//Class
