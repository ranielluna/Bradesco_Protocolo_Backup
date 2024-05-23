package com.bradesco.sistemabradesco.services;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bradesco.sistemabradesco.dto.ProtocolDTO;
import com.bradesco.sistemabradesco.models.Channels;
import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.models.TipoProtocolo;
import com.bradesco.sistemabradesco.repository.ProtocolRepository;

import jakarta.transaction.Transactional;

@Service
public class ProtocolService {

    @Autowired
    private ProtocolRepository protocolRepository;

    public Protocol openProtocol(ProtocolDTO protocolDTO) {

        if (protocolDTO.getDescricao() == null || protocolDTO.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("Descrição do protocolo é obrigatória.");
        }

        Protocol protocol = new Protocol();
        TipoProtocolo tpProtocol = protocolDTO.getTipoProtocolo();
        Client client = protocolDTO.getCliente();
        Channels channels = protocolDTO.getCanal();

        protocol.setCodigo(protocolDTO.getCodigo());
        protocol.setDataAbertura(LocalDate.now());
        LocalDate prazo = protocol.getDataAbertura().plusDays(tpProtocol.getPrazoTratativa()); 
        protocol.setData_prazo(prazo);
        protocol.setDescricao(protocolDTO.getDescricao());
        protocol.setCliente(client);
        protocol.setCanal(channels);
        protocol.setTipoProtocolo(tpProtocol);
        protocol.setNumeroProtocolo(protocolDTO.getNumeroProtocolo());

        try {
            return protocolRepository.save(protocol);
        } catch (Exception e) {
            // Lidar com qualquer exceção ao salvar o protocolo
            throw new RuntimeException("Erro ao salvar o protocolo: " + e.getMessage());
        }
    }

    
       @Transactional
    public void deleteProtocol(int codigo){
        protocolRepository.deleteById(codigo);
    }

}//class