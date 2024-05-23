package com.bradesco.sistemabradesco.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.SituationProtocolDTO;
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
        BeanUtils.copyProperties(situationProtocolDTO, situationProtocol);
        return situationProtocolRepository.save(situationProtocol);

    }

    /* Listar histórico */

    /* atualizar */


    /* Deletar Situacao protocolo */
     @Transactional
    public void deleteSituationProtocol(int code){
        situationProtocolRepository.deleteById(code);
    }


}
