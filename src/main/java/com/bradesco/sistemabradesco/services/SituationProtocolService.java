package com.bradesco.sistemabradesco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.SituationProtocolDTO;
import com.bradesco.sistemabradesco.models.Employee;
import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.models.SituationProtocol;
import com.bradesco.sistemabradesco.repository.SituationProtocolRepository;

import jakarta.transaction.Transactional;

@Service
public class SituationProtocolService {

    @Autowired
    private SituationProtocolRepository situationProtocolRepository;

    /* Criar situacao protocolo*/
    public SituationProtocol addSituationProtocol(SituationProtocolDTO situationProtocolDTO){
        SituationProtocol situationProtocol = new SituationProtocol();
        Protocol protocol = situationProtocolDTO.getProtocol();
        Employee employee = situationProtocolDTO.getEmployee();
        
        situationProtocol.setCode(situationProtocolDTO.getCode());
        situationProtocol.setProtocol(protocol);
        situationProtocol.setEmployee(employee);
        situationProtocol.setProtocolResponse(situationProtocolDTO.getProtocolResponse());
        situationProtocol.setReceiptDate(situationProtocolDTO.getReceiptDate());
        situationProtocol.setLastActionDate(situationProtocolDTO.getLastActionDate());
        try {
            return situationProtocolRepository.save(situationProtocol);
        } catch (Exception e) {
            // Lidar com qualquer exceção ao salvar o protocolo
            throw new RuntimeException("Erro ao salvar a situação do protocolo: " + e.getMessage());
        }
    }

    /* Listar histórico */

    /* Atualizar */


    /* Deletar Situacao protocolo */
     @Transactional
    public void deleteSituationProtocol(int code){
        situationProtocolRepository.deleteById(code);
    }

    /*Mostrar a situação de um protocolo filtrando pelo codigo do protocolo*/
    @Transactional
    public SituationProtocol findByProtocol(Protocol protocol) {
        return situationProtocolRepository.findByProtocol(protocol);
    }

    @Transactional
    public SituationProtocol findByProtocolResponse(Protocol protocol) {
        return situationProtocolRepository.findByProtocolResponse(protocol);
    }
}
