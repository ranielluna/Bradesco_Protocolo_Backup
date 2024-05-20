package com.bradesco.sistemabradesco.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.ContaDTO;
import com.bradesco.sistemabradesco.models.Conta;
import com.bradesco.sistemabradesco.repository.ContaRepository;
import java.util.List;

import jakarta.transaction.Transactional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    // public ContaService(ContaRepository contaRepository){
    //     this.contaRepository=contaRepository;
    // }

    /* Criar conta */
    public Conta criarConta(ContaDTO contaDTO){
        Conta novaConta = new Conta();
        BeanUtils.copyProperties(contaDTO, novaConta);
        return contaRepository.save(novaConta);

    }

    /* deletar conta */
    @Transactional
    public void deletarConta(int codigo){
        contaRepository.deleteById(codigo);
    }

    /* Listar conta */
    public List<Conta> listarContas(){
        return contaRepository.findAll();
    }

    /* encontrar conta por codigo, numero e agencia */
    @Transactional
    public Conta encontrarPorCodigo(int codigo){
        return contaRepository.findByCodigo(codigo)
                   .orElse(null);
    }

    @Transactional
    public Conta encontrarPorNumero(int numeroConta){
        return contaRepository.findByNumeroConta(numeroConta)
                    .orElse(null);

    }
  
    @Transactional
    public List<Conta> encontrarPorAgencia(int agencia){
        return contaRepository.findByAgencia(agencia);
                     
    }
   
   
}
