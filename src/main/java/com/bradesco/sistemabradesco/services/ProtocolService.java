package com.bradesco.sistemabradesco.services;

import java.time.LocalDate;
import java.util.Optional;

import com.bradesco.sistemabradesco.dto.*;
import com.bradesco.sistemabradesco.models.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bradesco.sistemabradesco.repository.ProtocolRepository;
import com.bradesco.sistemabradesco.repository.SituationProtocolRepository;
import com.bradesco.sistemabradesco.dto.ClientDTO;
import com.bradesco.sistemabradesco.dto.ProtocolDTO;

import jakarta.transaction.Transactional;

@Service
public class ProtocolService {

    @Autowired
    private ProtocolRepository protocolRepository;

    @Autowired
    private SituationProtocolRepository situationProtocolRepository;

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
            throw new RuntimeException("Erro ao salvar o protocolo: " + e.getMessage());
        }
    }

    public Protocol updateProtocol(int code, ProtocolDTO protocolDTO) {
        Protocol existingProtocol = protocolRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Protocol not found"));

        if (protocolDTO.getClient() != null) {
            existingProtocol.setClient(protocolDTO.getClient());
        }

        if (protocolDTO.getDeadline() != null) {
            existingProtocol.setDeadline(protocolDTO.getDeadline());
        }
        if (protocolDTO.getDescription() != null) {
            existingProtocol.setDescription(protocolDTO.getDescription());
        }
        if (protocolDTO.getChannel() != null) {
            existingProtocol.setChannel(protocolDTO.getChannel());
        }
        if (protocolDTO.getProtocolType() != null) {
            existingProtocol.setProtocolType(protocolDTO.getProtocolType());
        }
        if (protocolDTO.getInitialReceiptDate() != null) {
            existingProtocol.setInitialReceiptDate(protocolDTO.getInitialReceiptDate());
        }
        if (protocolDTO.getDepartment() != null) {
            existingProtocol.setDepartment(protocolDTO.getDepartment());
        }
        if (protocolDTO.isPropensaoBacen()) {
            existingProtocol.setPropensaoBacen(protocolDTO.isPropensaoBacen());
        }
        if (protocolDTO.isExpedite()) {
            existingProtocol.setExpedite(protocolDTO.isExpedite());
        }
        if (protocolDTO.isDue()) {
            existingProtocol.setDue(protocolDTO.isDue());
        }
        if (protocolDTO.isJustified()) {
            existingProtocol.setJustified(protocolDTO.isJustified());
        }
        if (protocolDTO.getProtocolNumber() != null) {
            existingProtocol.setProtocolNumber(protocolDTO.getProtocolNumber());
        }
        if (protocolDTO.getProtocolStatus() != null) {
            existingProtocol.setProtocolStatus(protocolDTO.getProtocolStatus());
        }
        if (protocolDTO.getInitialReceiptDate() != null) {
            existingProtocol.setInitialReceiptDate(protocolDTO.getInitialReceiptDate());
        } else {
            existingProtocol.setInitialReceiptDate(LocalDate.now());
        }

        return protocolRepository.save(existingProtocol);
    }

    // Métodos para converter DTOs em entidades
    private Client toClientEntity(ClientDTO clientDTO) {
        if (clientDTO == null) return null;
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }

    private Channels toChannelsEntity(ChannelsDTO channelsDTO) {
        if (channelsDTO == null) return null;
        Channels channels = new Channels();
        BeanUtils.copyProperties(channelsDTO, channels);
        return channels;
    }

    private ProtocolType toProtocolTypeEntity(ProtocolTypeDTO protocolTypeDTO) {
        if (protocolTypeDTO == null) return null;
        ProtocolType protocolType = new ProtocolType();
        BeanUtils.copyProperties(protocolTypeDTO, protocolType);
        return protocolType;
    }

    private Department toDepartmentEntity(DepartmentDTO departmentDTO) {
        if (departmentDTO == null) return null;
        Department department = new Department();
        BeanUtils.copyProperties(departmentDTO, department);
        return department;
    }

    @Transactional
    public void deleteProtocol(int code) {
        protocolRepository.deleteById(code);
    }

    public Optional<Protocol> findById(int code) {
        return protocolRepository.findById(code);
    }

    public Protocol save(Protocol protocol) {
        return protocolRepository.save(protocol);
    }
}
