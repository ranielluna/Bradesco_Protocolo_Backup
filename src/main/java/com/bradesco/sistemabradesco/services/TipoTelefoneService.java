package com.bradesco.sistemabradesco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.TipoTelefoneDTO;
import com.bradesco.sistemabradesco.models.TipoTelefone;
import com.bradesco.sistemabradesco.repository.TipoTelefoneRepository;

import jakarta.transaction.Transactional;

@Service
public class TipoTelefoneService {
  
   @Autowired
   private TipoTelefoneRepository repository;

  // public TipoTelefoneService(TipoTelefoneRepository repository) {
  //   this.repository = repository;
  // }

   public List<TipoTelefone> listarTipoProtocolo(){
    return repository.findAll();
  }

  public TipoTelefone criarTipoTelefone(TipoTelefoneDTO tipoTelefoneDTO){
      
      if(tipoTelefoneDTO.getTipo() != null){
        TipoTelefone tipoTelefone = new TipoTelefone();
        tipoTelefone.setTipo(tipoTelefoneDTO.getTipo());

        try{
          return repository.save(tipoTelefone);
        }catch (Exception e) {
          throw new RuntimeException("Erro ao salvar tipo protocolo: " + e.getMessage());
        }
      }else{
        throw new IllegalArgumentException("O preenchimento dos campos é obrigatório.");
      }
    }

        /* deletar conta */
        @Transactional
        public void deletarTipoTelefone(int codigo){
              repository.deleteById(codigo);
        }


}
