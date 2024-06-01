package com.bradesco.sistemabradesco.services;

import java.util.List;

import com.bradesco.sistemabradesco.dto.PositionDTO;
import com.bradesco.sistemabradesco.models.Position;
import com.bradesco.sistemabradesco.repository.PositionRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    // Metodo de listagem de cargos existentes no Banco de dados
    public List<Position> listPositions() {
        return positionRepository.findAll();
    }

    // Metodo de criação de um cargo
    public Position addPosition(PositionDTO positionDTO) {
        Position position = new Position();
        position.setCode(positionDTO.getCode());
        position.setPosition(positionDTO.getPosition());
        return positionRepository.save(position);
    }

    // Metodo de deletar um cargo
    @Transactional
    public void deletePosition(int code){
        positionRepository.deleteById(code);
    }

}
