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

import com.bradesco.sistemabradesco.dto.PositionDTO;
import com.bradesco.sistemabradesco.models.Position;
import com.bradesco.sistemabradesco.services.PositionService;


@RestController
@RequestMapping("/api/positions")
public class PositionController {
    
    @Autowired
    private PositionService positionService;

    @GetMapping("/getPositions")
    public List<Position> getPositions() {
        return positionService.getPositions();
    }

    @PostMapping("/addPosition")
    public Position addPosition(@RequestBody PositionDTO cargoDTO) {
        return positionService.addPosition(cargoDTO);
    }

    // deletar    
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<Object> deletePosition(@PathVariable int codigo){
        positionService.deletePosition(codigo);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Cargo deletado com sucesso!");
        return ResponseEntity.ok(message);

    }
}