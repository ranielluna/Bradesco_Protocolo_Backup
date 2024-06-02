package com.bradesco.sistemabradesco.services;

import com.bradesco.sistemabradesco.dto.ChannelsDTO;
import com.bradesco.sistemabradesco.models.Channels;
import com.bradesco.sistemabradesco.repository.ChannelsRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelsService {

    @Autowired
    private ChannelsRepository channelsRepository;

    // listar canais
    public List<Channels> listChannels() {
        return channelsRepository.findAll();
    }

    // criar canal
    @Transactional
    public Channels addChannel(ChannelsDTO channelsDTO) {
        Channels newChannel = new Channels();
        BeanUtils.copyProperties(channelsDTO, newChannel);
        return channelsRepository.save(newChannel);
    }

    // Atualizar canal
    @Transactional
    public Channels updateChannel(int code, ChannelsDTO channelsDTO) {
        // encontrando canal pelo id
        Channels channel = channelsRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Canal não encontrado para o ID: " + code));
        // atualizando os campos de canal
        channel.setChannel(channelsDTO.getChannel());
        // salvando o canal
        return channelsRepository.save(channel);
    }

    // deletar canal
    @Transactional
    public void deleteChannel(int code) {
        channelsRepository.deleteById(code);
    }
}
