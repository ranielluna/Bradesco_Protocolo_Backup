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

  public List<PhoneClient> listPhoneClients() {
    return phoneClientRepository.findAll();
  }

  @Transactional
  public PhoneClient addpPhoneClient(PhoneClientDTO phoneClientDTO) {

    if (phoneClientDTO.getClient() != null) {
      PhoneClient phoneClient = new PhoneClient();
      phoneClient.setDdd(phoneClientDTO.getDdd());
      phoneClient.setNumber(phoneClientDTO.getNumber());

      Client client = phoneClientDTO.getClient();
      phoneClient.setClient(client);

      PhoneType phoneType = phoneClientDTO.getPhoneType();
      phoneClient.setPhoneType(phoneType);
      try {
        return phoneClientRepository.save(phoneClient);
      } catch (Exception e) {
        throw new RuntimeException("Erro ao salvar telefone: " + e.getMessage());
      }

    } else {
      throw new IllegalArgumentException("O preenchimento dos campos é obrigatório.");
    }
  }

  // updates
  // atualizar DDD
  @Transactional
  public PhoneClient updateDdd(int code, PhoneClientDTO phoneClientDTO) {
    // encontrar telefone por id
    PhoneClient phone = phoneClientRepository.findByCode(code);
    // atualizando meus campos
    phone.setDdd(phoneClientDTO.getDdd());
    return phoneClientRepository.save(phone);

  }

  // atualizar tipo telefone
  @Transactional
  public PhoneClient updatePhoneType(int code, PhoneClientDTO phoneClientDTO) {
    PhoneClient phoneType = phoneClientRepository.findByCode(code);
    phoneType.setPhoneType(phoneClientDTO.getPhoneType());
    return phoneClientRepository.save(phoneType);

  }

  // atualizar numero
  @Transactional
  public PhoneClient updateNumber(int code, PhoneClientDTO phoneClientDTO) {
    PhoneClient phoneNumber = phoneClientRepository.findByCode(code);
    phoneNumber.setNumber(phoneClientDTO.getNumber());
    return phoneClientRepository.save(phoneNumber);

  }// updates

  // deletar telefone cliente
  @Transactional
  public void deletePhoneClient(int code) {
    phoneClientRepository.deleteById(code);
  }
}
