package com.bradesco.sistemabradesco.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.FuncionarioDTO;
import com.bradesco.sistemabradesco.models.Funcionario;
import com.bradesco.sistemabradesco.repository.FuncionarioRepository;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {
  // @SuppressWarnings("unused")
  @Autowired
  private FuncionarioRepository funcionarioRepository;

  // public FuncionarioService(FuncionarioRepository funcionarioRepository){
  //   this.funcionarioRepository=funcionarioRepository;
  // }
  
  public List<FuncionarioDTO> listarFuncionarios(){
    List<Funcionario> funcionarios = funcionarioRepository.findAll();
    return funcionarios.stream().map(FuncionarioDTO::new).toList();
  }

     /* Criar conta */
    public Funcionario criarFuncionario(FuncionarioDTO funcionarioDTO){
        Funcionario novoFuncionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDTO, novoFuncionario);
        return funcionarioRepository.save(novoFuncionario);

    }


  /* deletar */
     @Transactional
    public void deletarFuncionario(String codigo){
        funcionarioRepository.deleteById(codigo);
    }


}
