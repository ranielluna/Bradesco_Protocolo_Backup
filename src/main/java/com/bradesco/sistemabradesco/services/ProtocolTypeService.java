package com.bradesco.sistemabradesco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.ProtocolTypeDTO;
import com.bradesco.sistemabradesco.models.ProtocolType;
import com.bradesco.sistemabradesco.repository.ProtocolTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class ProtocolTypeService {
  
  @Autowired
  private ProtocolTypeRepository protocolTypeRepository;



  public List<ProtocolType> listProtocolTypes(){
    return protocolTypeRepository.findAll();
  }

  public ProtocolType addProtocolType(ProtocolTypeDTO protocolTypeDTO){
      
      if(protocolTypeDTO.getType() != null){
        ProtocolType protocolType = new ProtocolType();
        protocolType.setType(protocolTypeDTO.getType());
        protocolType.setTreatmentDeadline(protocolTypeDTO.getTreatmentDeadline());
        protocolType.setBusinessDays(protocolTypeDTO.isBusinessDays());

        try{
          return protocolTypeRepository.save(protocolType);
        }catch (Exception e) {
          throw new RuntimeException("Erro ao salvar tipo protocolo: " + e.getMessage());
        }
      }else{
        throw new IllegalArgumentException("O preenchimento dos campos é obrigatório.");
      }
    }

        /* deletar conta */
    @Transactional
    public void deleteProtocolType(int code){
        protocolTypeRepository.deleteById(code);
    }


}