package com.bradesco.sistemabradesco.services;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.SituationProtocolDTO;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.models.Employee;
import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.models.SituationProtocol;
import com.bradesco.sistemabradesco.repository.ProtocolRepository;
import com.bradesco.sistemabradesco.repository.SituationProtocolRepository;

import jakarta.transaction.Transactional;

@Service
public class SituationProtocolService {

    @Autowired
    private SituationProtocolRepository situationProtocolRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProtocolRepository protocolRepository;



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

 // registro de protocolos q ainda nn foram registrado em situação protocolo
    public SituationProtocol findUnregisteredProtocols() {
        // Obtém todos os códigos de protocolos registrados na SituaçãoProtocolo
        Set<Integer> registeredCodes = situationProtocolRepository.findAll().stream()
               .map(situationProtocol -> situationProtocol.getProtocol().getCode())
               .collect(Collectors.toSet());

        // Filtra os protocolos que não estão na lista de códigos registrados
        List<Protocol> unregisteredProtocols = protocolRepository.findAll().stream()
        .filter(protocol ->!registeredCodes.contains(protocol.getCode()) && protocol.getDepartment()!= null)
        .collect(Collectors.toList());

        SituationProtocol situationProtocol = new SituationProtocol();
        // TRatativa dos protocolos para o encaminhamento 
        if( unregisteredProtocols != null){
            for(Protocol protocol: unregisteredProtocols){
                Department dep = protocol.getDepartment();
                List <Employee> employee = employeeService.listEmployeeByDepartment(dep);
                Employee littleEmployee = employeeService.filterEmployeesCount(employee);

                //registro situation
                situationProtocol.setEmployee(littleEmployee);
                situationProtocol.setProtocol(protocol);
                situationProtocol.setReceiptDate(LocalDate.now());
                situationProtocol.setLastActionDate(LocalDate.now());
                return situationProtocolRepository.save(situationProtocol);
                
            }
        }
        //return vai ser o registro em situationprotocol com o Employee e o protocol definidos
        return null;
    }





}
