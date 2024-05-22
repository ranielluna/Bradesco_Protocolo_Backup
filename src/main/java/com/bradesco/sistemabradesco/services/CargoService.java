package com.bradesco.sistemabradesco.services;

import java.util.List;

import com.bradesco.sistemabradesco.dto.CargoDTO;
import com.bradesco.sistemabradesco.models.Cargo;
import com.bradesco.sistemabradesco.repository.CargoRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
    @Autowired
    private CargoRepository cargoRepository;

   
    // public CargoService(CargoRepository cargoRepository) {
    //     this.cargoRepository = cargoRepository;
    // }

    public List<Cargo> listarCargos() {
        return cargoRepository.findAll();
    }

    public Cargo criarCargo(CargoDTO cargoDTO) {
        Cargo cargo = new Cargo();
        cargo.setCodigo(cargoDTO.getCodigo());
        cargo.setCargo(cargoDTO.getCargo());
        return cargoRepository.save(cargo);
    }

    @Transactional
    public void deletarCargo(int codigo){
        cargoRepository.deleteById(codigo);
    }

}
