package com.bradesco.sistemabradesco.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.SituationProtocolDTO;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.models.Employee;
import com.bradesco.sistemabradesco.models.Position;
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

    @Autowired
    private ProtocolService protocolService;

    /* Criar situacao protocolo */
    public SituationProtocol addSituationProtocol(SituationProtocolDTO situationProtocolDTO) {
        SituationProtocol situationProtocol = new SituationProtocol();
        Protocol protocol = situationProtocolDTO.getProtocol();
        Employee employee = situationProtocolDTO.getEmployee();

        situationProtocol.setCode(situationProtocolDTO.getCode());
        situationProtocol.setProtocol(protocol);
        situationProtocol.setEmployee(employee);
        situationProtocol.setProtocolResponse(situationProtocolDTO.getProtocolResponse());
        situationProtocol.setReceiptDate(situationProtocolDTO.getReceiptDate());
        situationProtocol.setLastActionDate(LocalDate.now());
        try {
            return situationProtocolRepository.save(situationProtocol);
        } catch (Exception e) {
            // Lidar com qualquer exceção ao salvar o protocolo
            throw new RuntimeException("Erro ao salvar a situação do protocolo: " + e.getMessage());
        }
    }

    // Updates
    // funcionario
    @Transactional
    public SituationProtocol updateSituationProtocolEmployee(int code, SituationProtocolDTO situationProtocolDTO) {
        SituationProtocol situation = situationProtocolRepository.findByCode(code);
        // atualizando campos
        situation.setEmployee(situationProtocolDTO.getEmployee());
        return situationProtocolRepository.save(situation);
    }

    // ultima data de ação
    @Transactional
    public SituationProtocol updateLastActionDate(int code, SituationProtocolDTO situationProtocolDTO) {
        SituationProtocol situation = situationProtocolRepository.findByCode(code);
        situation.setLastActionDate(situationProtocolDTO.getLastActionDate());
        return situationProtocolRepository.save(situation);
    }

    // resposta protocolo
    // @Transactional
    // public SituationProtocol updateProtocolResponse(int code,
    // SituationProtocolDTO situationProtocolDTO) {

    // SituationProtocol situation = situationProtocolRepository.findByCode(code);
    // situation.setProtocolResponse(situationProtocolDTO.getProtocolResponse());

    // return situationProtocolRepository.save(situation);
    // }

    
    @Transactional
    public SituationProtocol updateProtocolResponse(Optional<Long> protocolNumber, SituationProtocolDTO situationProtocolDTO) {
        if (!protocolNumber.isPresent()) {
            throw new IllegalArgumentException("Protocol number is required");
        }

        Long numeroProtocolo = protocolNumber.get();

        // Busca o Protocolo pelo número
        Optional<Protocol> protocolOptional = protocolRepository.findByProtocolNumber(numeroProtocolo);
        if (!protocolOptional.isPresent()) {
            throw new RuntimeException("Protocolo não encontrado para o valor: " + numeroProtocolo);
        }

        Protocol protocol = protocolOptional.get();

        // Encontra o SituacaoProtocolo associado ao Protocolo
        SituationProtocol situationProtocolOptional = situationProtocolRepository.findByProtocol(protocol);
        if (situationProtocolOptional == null ) {
            throw new RuntimeException("SituacaoProtocolo não encontrado para o protocolo: " + numeroProtocolo);
        }
        
        // Atualiza o campo response
        situationProtocolOptional.setProtocolResponse(situationProtocolDTO.getProtocolResponse());

        // Atualizando o status do protocolo pra finalizar
        protocol = protocolService.finalUpdateStatusProtocol(numeroProtocolo);
        
        // Salva o SituacaoProtocolo atualizado
        return situationProtocolRepository.save(situationProtocolOptional);
    }


    // data de recebimento
    @Transactional
    public SituationProtocol updateProtocolReceiptDate(int code, SituationProtocolDTO situationProtocolDTO) {
        SituationProtocol situation = situationProtocolRepository.findByCode(code);
        situation.setReceiptDate(situationProtocolDTO.getReceiptDate());
        return situationProtocolRepository.save(situation);
    }// updates

    /* Mostrar a situação de um protocolo filtrando pelo codigo do protocolo */
    @Transactional
    public SituationProtocol findByProtocol(Protocol protocol) {
        return situationProtocolRepository.findByProtocol(protocol);
    }

    /* Mostrar a resposta do protocolo */
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
                .filter(protocol -> !registeredCodes.contains(protocol.getCode()) && protocol.getDepartment() != null)
                .collect(Collectors.toList());

        SituationProtocol situationProtocol = new SituationProtocol();
        // TRatativa dos protocolos para o encaminhamento
        if (unregisteredProtocols != null) {
            for (Protocol protocol : unregisteredProtocols) {
                Department dep = protocol.getDepartment();
                List<Employee> employee = employeeService.listEmployeeByDepartment(dep);
                List<Employee> analystEmployees = employeeService.listAnalystEmployess(employee);
                Employee littleEmployee = employeeService.filterEmployeesCount(analystEmployees);

                // registro situation
                situationProtocol.setEmployee(littleEmployee);
                situationProtocol.setProtocol(protocol);
                situationProtocol.setReceiptDate(LocalDate.now());
                situationProtocol.setLastActionDate(LocalDate.now());
                return situationProtocolRepository.save(situationProtocol);

            }
        }
        // return vai ser o registro em situationprotocol com o Employee e o protocol
        // definidos
        return null;
    }


    public SituationProtocol ProtocolDueFalse(Protocol protocol){
        //instanciar position
        Position position = new Position();
        position.setCode(2);;
        position.setPosition("Analista");
        

        SituationProtocol situationProtocol = new SituationProtocol();
        List<Employee> employee = employeeService.listEmployeeByPosition(position); // filtra funcionario por cargo
        Employee littleEmployee = employeeService.filterEmployeesCount(employee); // pegar o funcionario com menor demanda ainda nn tratada


        //situationProtocol.setCode(situationProtocolDTO.getCode());
        situationProtocol.setProtocol(protocol);
        situationProtocol.setEmployee(littleEmployee);
        situationProtocol.setProtocolResponse("Abertura do seu serviço não foi considerado plausível baseado nos parâmetros do sistema.");
        situationProtocol.setReceiptDate(LocalDate.now());
        situationProtocol.setLastActionDate(LocalDate.now());
        try {
            return situationProtocolRepository.save(situationProtocol);
        } catch (Exception e) {
            // Lidar com qualquer exceção ao salvar o protocolo
            throw new RuntimeException("Erro ao salvar a situação do protocolo: " + e.getMessage());
        }
    }

    /* Deletar Situacao protocolo */
    @Transactional
    public void deleteSituationProtocol(int code) {
        situationProtocolRepository.deleteById(code);
    }

}
