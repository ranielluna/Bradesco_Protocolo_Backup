package com.bradesco.sistemabradesco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.PhoneTypeDTO;
import com.bradesco.sistemabradesco.models.PhoneType;
import com.bradesco.sistemabradesco.repository.PhoneTypeRepository; 


import jakarta.transaction.Transactional;

@Service
public class PhoneTypeService {

  @Autowired
  private PhoneTypeRepository phoneTypeRepository;

  // listar telefones
  public List<PhoneType> listPhoneTypes() {
    return phoneTypeRepository.findAll();
  }

  @Transactional
  public PhoneType addPhoneType(PhoneTypeDTO phoneTypeDTO) {

    if (phoneTypeDTO.getType() != null) {
      PhoneType phoneType = new PhoneType();
      phoneType.setType(phoneTypeDTO.getType());

      try {
        return phoneTypeRepository.save(phoneType);
      } catch (Exception e) {
        throw new RuntimeException("Erro ao salvar tipo protocolo: " + e.getMessage());
      }
    } else {
      throw new IllegalArgumentException("O preenchimento dos campos é obrigatório.");
    }
  }

  // atualizar telefone
  @Transactional
  public PhoneType updatePhoneType(int code, PhoneTypeDTO phoneTypeDTO){
      // procurando o tipo de telefone por código
      PhoneType phoneType = phoneTypeRepository.findByCode(code);      
      // update compos
      phoneType.setType(phoneTypeDTO.getType());
      // salvando alterações  
      return phoneTypeRepository.save(phoneType);
  }

  // deletar tipo de telefone
  @Transactional
  public void deletePhoneType(int code) {
    phoneTypeRepository.deleteById(code);
  }

}
