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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.sistemabradesco.dto.ChannelsDTO;
import com.bradesco.sistemabradesco.models.Channels;
import com.bradesco.sistemabradesco.services.ChannelsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/channels")
public class ChannelsController {

    @Autowired
    private ChannelsService channelsService;

    // Metodo para listar os canais ja cadastrados
    @Operation(description = "Lista os canais que existem na aplicação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna um arrayList com os canais existentes."),
            @ApiResponse(responseCode = "400", description = "Canais não encontrados.")
    })
    @GetMapping("/listChannels")
    public List<Channels> listChannels() {
        return channelsService.listChannels();
    }

    // Metodo para criar um novo canal
    @Operation(description = "Cria um novo canal para a aplicação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o objeto canal que foi criado com suas informações."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PostMapping("/addChannel")
    public Channels addChannel(@RequestBody ChannelsDTO channelDTO) {
        return channelsService.addChannel(channelDTO);
    }


    // UPDATE
    @PutMapping("/{code}")
    public ResponseEntity<Channels> updateChannel(@PathVariable int code,
            @RequestBody ChannelsDTO channelsDTO) {
        Channels updatedChannel = channelsService.updateChannel(code, channelsDTO);
        return ResponseEntity.ok(updatedChannel);
    }

    // Metodo para deletar um canal
    @Operation(description = "Deleta um canal por meio do seu codigo.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Canal deletado com sucesso!"))
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> deleteChannel(@PathVariable int code) {
        channelsService.deleteChannel(code);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Canal deletado com sucesso!");
        return ResponseEntity.ok(message);

    }
}