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

    public List<Channels> listChannels() {
        return channelsRepository.findAll();
    }

    public Channels addChannel(ChannelsDTO channelsDTO) {
        Channels newChannel = new Channels();
        BeanUtils.copyProperties(channelsDTO, newChannel);
        return channelsRepository.save(newChannel);
    }

    @Transactional
    public void deleteChannel(int code){
        channelsRepository.deleteById(code);
    }

    // Outros métodos do serviço de canais (se houver)
}
