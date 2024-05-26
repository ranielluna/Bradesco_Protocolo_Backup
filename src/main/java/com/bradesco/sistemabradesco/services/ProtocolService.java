package com.bradesco.sistemabradesco.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bradesco.sistemabradesco.dto.ProtocolDTO;
import com.bradesco.sistemabradesco.models.Channels;
import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.models.ProtocolType;
import com.bradesco.sistemabradesco.repository.ProtocolRepository;

import jakarta.transaction.Transactional;

@Service
public class ProtocolService {

    @Autowired
    private ProtocolRepository protocolRepository;

    public Protocol openProtocol(ProtocolDTO protocolDTO) {

        if (protocolDTO.getDescription() == null || protocolDTO.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Descrição do protocolo é obrigatória.");
        }

        Protocol protocol = new Protocol();
        ProtocolType protocolType = protocolDTO.getProtocolType();
        Client client = protocolDTO.getClient();
        Channels channels = protocolDTO.getChannel();

        protocol.setCode(protocolDTO.getCode());
        protocol.setOpeningDate(LocalDate.now());
        LocalDate deadline = protocol.getOpeningDate().plusDays(protocolType.getTreatmentDeadline()); 
        protocol.setDeadline(deadline);
        protocol.setDescription(protocolDTO.getDescription());
        protocol.setClient(client);
        protocol.setChannel(channels);
        protocol.setProtocolType(protocolType);
        protocol.setProtocolNumber(protocolDTO.getProtocolNumber());
        protocol.setProtocolStatus("Novo");

        try {
            return protocolRepository.save(protocol);
        } catch (Exception e) {
            // Lidar com qualquer exceção ao salvar o protocolo
            throw new RuntimeException("Erro ao salvar o protocolo: " + e.getMessage());
        }
    }

    
       @Transactional
    public void deleteProtocol(int code){
        protocolRepository.deleteById(code);
    }

    public List<Protocol> listProtocolStatus(String status) {
        return protocolRepository.findByProtocolStatus(status);
    }

}//class