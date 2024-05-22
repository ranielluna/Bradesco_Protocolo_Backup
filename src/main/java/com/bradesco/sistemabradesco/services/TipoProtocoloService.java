package com.bradesco.sistemabradesco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.TipoProtocoloDTO;
import com.bradesco.sistemabradesco.models.TipoProtocolo;
import com.bradesco.sistemabradesco.repository.TipoProtocoloRepository;

import jakarta.transaction.Transactional;

@Service
public class TipoProtocoloService {
  
  @Autowired
  private TipoProtocoloRepository repository;

  // public TipoProtocoloService(TipoProtocoloRepository repository) {
  //   this.repository = repository;
  // }

  public List<TipoProtocolo> listarTipoProtocolo(){
    return repository.findAll();
  }

  public TipoProtocolo criarTipoProtocolo(TipoProtocoloDTO tipoProtocoloDTO){
      
      if(tipoProtocoloDTO.getTipo() != null){
        TipoProtocolo tipoProtocolo = new TipoProtocolo();
        tipoProtocolo.setTipo(tipoProtocoloDTO.getTipo());
        tipoProtocolo.setPrazoTratativa(tipoProtocoloDTO.getPrazoTratativa());
        tipoProtocolo.setDiasUteis(tipoProtocoloDTO.isDiasUteis());

        try{
          return repository.save(tipoProtocolo);
        }catch (Exception e) {
          throw new RuntimeException("Erro ao salvar tipo protocolo: " + e.getMessage());
        }
      }else{
        throw new IllegalArgumentException("O preenchimento dos campos é obrigatório.");
      }
    }

        /* deletar conta */
    @Transactional
    public void deletarTipoProtocolo(int codigo){
        repository.deleteById(codigo);
    }


}