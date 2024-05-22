package com.bradesco.sistemabradesco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.TelefoneClienteDTO;
import com.bradesco.sistemabradesco.models.Cliente;
import com.bradesco.sistemabradesco.models.TelefoneCliente;
import com.bradesco.sistemabradesco.models.TipoTelefone;
import com.bradesco.sistemabradesco.repository.TelefoneClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class TelefoneClienteService {
  
  @Autowired
  private TelefoneClienteRepository repository;

  // public TelefoneClienteService(TelefoneClienteRepository repository){
  //   this.repository = repository;
  // }

  public List<TelefoneCliente> listaTelefoneCliente(){
    return repository.findAll();
  }


  public TelefoneCliente criarTelefoneCliente(TelefoneClienteDTO telefoneClienteDTO){
    
    if(telefoneClienteDTO.getCliente() != null){
      TelefoneCliente telefoneCliente = new TelefoneCliente();
      telefoneCliente.setDdd(telefoneClienteDTO.getDdd());
      telefoneCliente.setNumero(telefoneClienteDTO.getNumero());

      Cliente cliente = telefoneClienteDTO.getCliente();
      telefoneCliente.setCliente(cliente);

      TipoTelefone tipoTelefone = telefoneClienteDTO.getTipoTelefone();
      telefoneCliente.setTipoTelefone(tipoTelefone);

      try{
        return repository.save(telefoneCliente);
      }catch (Exception e) {
        throw new RuntimeException("Erro ao salvar telefone: " + e.getMessage());
    }


    }else{
      throw new IllegalArgumentException("O preenchimento dos campos é obrigatório.");
    }
  }

    
    @Transactional
    public void deletarTelefoneCliente(int codigo){
        repository.deleteById(codigo);
    }

}
