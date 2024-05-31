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

  public List<ProtocolType> listProtocolTypes() {
    return protocolTypeRepository.findAll();
  }

  // criar tipo protocolo
  @Transactional
  public ProtocolType addProtocolType(ProtocolTypeDTO protocolTypeDTO) {

    if (protocolTypeDTO.getType() != null) {
      ProtocolType protocolType = new ProtocolType();
      protocolType.setType(protocolTypeDTO.getType());
      protocolType.setTreatmentDeadline(protocolTypeDTO.getTreatmentDeadline());
      protocolType.setBusinessDays(protocolTypeDTO.isBusinessDays());

      try {
        return protocolTypeRepository.save(protocolType);
      } catch (Exception e) {
        throw new RuntimeException("Erro ao salvar tipo protocolo: " + e.getMessage());
      }
    } else {
      throw new IllegalArgumentException("O preenchimento dos campos é obrigatório.");
    }
  }

  //updates
  //tipo protocolo
  @Transactional
  public ProtocolType updateProtocolType(int code, ProtocolTypeDTO protocolTypeDTO ){
    //encontrando tipo de protocolo por codigo
    ProtocolType type = protocolTypeRepository.findByCode(code);
    type.setType(protocolTypeDTO.getType());
    return protocolTypeRepository.save(type);

  }

  // dias uteis
  @Transactional
  public ProtocolType updateBusinessDays(int code, ProtocolTypeDTO protocolTypeDTO){
    ProtocolType businessDays = protocolTypeRepository.findByCode(code);
    businessDays.setBusinessDays(protocolTypeDTO.isBusinessDays());
    return protocolTypeRepository.save(businessDays);
  }

  // prazo de tratativa
  @Transactional
  public ProtocolType updateTreatmentDeadline(int code, ProtocolTypeDTO protocolTypeDTO){
    ProtocolType treatmentDeadline = protocolTypeRepository.findByCode(code);
    treatmentDeadline.setBusinessDays(protocolTypeDTO.isBusinessDays());
    return protocolTypeRepository.save(treatmentDeadline);
  }// updates


  // deletar conta 
  @Transactional
  public void deleteProtocolType(int code) {
    protocolTypeRepository.deleteById(code);
  }

}