package com.bradesco.sistemabradesco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.PhoneClientDTO;
import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.models.PhoneClient;
import com.bradesco.sistemabradesco.models.PhoneType;
import com.bradesco.sistemabradesco.repository.PhoneClientRepository;

import jakarta.transaction.Transactional;

@Service
public class PhoneClientService {
  
  @Autowired
  private PhoneClientRepository phoneClientRepository;



  public List<PhoneClient> listPhoneClients(){
    return phoneClientRepository.findAll();
  }


  public PhoneClient addpPhoneClient(PhoneClientDTO phoneClientDTO){
    
    if(phoneClientDTO.getClient() != null){
      PhoneClient phoneClient = new PhoneClient();
      phoneClient.setDdd(phoneClientDTO.getDdd());
      phoneClient.setNumber(phoneClientDTO.getNumber());

      Client client = phoneClientDTO.getClient();
      phoneClient.setClient(client);

      PhoneType phoneType = phoneClientDTO.getPhoneType();
      phoneClient.setPhoneType(phoneType);
      try{
        return phoneClientRepository.save(phoneClient);
      }catch (Exception e) {
        throw new RuntimeException("Erro ao salvar telefone: " + e.getMessage());
    }


    }else{
      throw new IllegalArgumentException("O preenchimento dos campos é obrigatório.");
    }
  }

    
    @Transactional
    public void deletePhoneClient(int code){
      phoneClientRepository.deleteById(code);
    }

}
