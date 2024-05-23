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
    public List<Position> getPositions() {
        return positionRepository.findAll();
    }

    // Metodo de criação de um cargo
    public Position addPosition(PositionDTO cargoDTO) {
        Position cargo = new Position();
        cargo.setCodigo(cargoDTO.getCodigo());
        cargo.setCargo(cargoDTO.getCargo());
        return positionRepository.save(cargo);
    }

    // Metodo de deletar um cargo
    @Transactional
    public void deletePosition(int codigo){
        positionRepository.deleteById(codigo);
    }

}
