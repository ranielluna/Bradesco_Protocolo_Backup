package com.bradesco.sistemabradesco.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.sistemabradesco.dto.CargoDTO;
import com.bradesco.sistemabradesco.models.Cargo;
import com.bradesco.sistemabradesco.services.CargoService;


@RestController
@RequestMapping("/cargos")
public class CargoController {
    
    @Autowired
    private CargoService cargoService;
    // public CargoController(CargoService cargoService){
    //     this.cargoService=cargoService;
    // }

    @GetMapping("/listar")
    public List<Cargo> listarCargos() {
        return cargoService.listarCargos();
    }

    @PostMapping("/criar")
    public Cargo criarCargo(@RequestBody CargoDTO cargoDTO) {
        return cargoService.criarCargo(cargoDTO);
    }

    // deletar    
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<Object> deletarTipoTelefone(@PathVariable int codigo){
        cargoService.deletarCargo(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Cargo deletado com sucesso!");
        return ResponseEntity.ok(message);

    }
}