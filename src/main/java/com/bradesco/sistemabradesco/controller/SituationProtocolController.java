package com.bradesco.sistemabradesco.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bradesco.sistemabradesco.dto.SituationProtocolDTO;
import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.models.SituationProtocol;
import com.bradesco.sistemabradesco.repository.ProtocolRepository;
// import com.bradesco.sistemabradesco.repository.SituationProtocolRepository;
import com.bradesco.sistemabradesco.services.SituationProtocolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/situationProtocol")
public class SituationProtocolController {
    @Autowired
    private SituationProtocolService situationProtocolService;

    @Autowired
    private ProtocolRepository protocolRepository;

    @Operation(description = "Cria uma situação de protocolo na aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna uma situação de protocolo com suas informações."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PostMapping("/addSituationProtocol")
    public SituationProtocol addSituationProtocol(@RequestBody SituationProtocolDTO situationProtocolDTO) {
        return situationProtocolService.addSituationProtocol(situationProtocolDTO);
    }

    // UPDATES
    // Atualizar situação do protocolo do funcionário
    @Operation(description = "Atualiza a situação de um protocolo do funcionário.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna o objeto 'situação de protocolo' com sua situação atualizada."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{code}/employee")
    public ResponseEntity<SituationProtocol> updateSituationProtocolEmployee(@PathVariable int code,
            @RequestBody SituationProtocolDTO situationProtocolDTO) {
        SituationProtocol updatedSituation = situationProtocolService.updateSituationProtocolEmployee(code,
                situationProtocolDTO);
        return ResponseEntity.ok(updatedSituation);
    }

    // Atualizar última data de ação
    @Operation(description = "Atualiza a data de última ação do protocolo.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna uma situação de protocolo com o campo 'data de última ação' atualizada."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{code}/last-action-date")
    public ResponseEntity<SituationProtocol> updateLastActionDate(@PathVariable int code,
            @RequestBody SituationProtocolDTO situationProtocolDTO) {
        SituationProtocol updatedSituation = situationProtocolService.updateLastActionDate(code, situationProtocolDTO);
        return ResponseEntity.ok(updatedSituation);
    }

    // Atualizar resposta do protocolo
    // @PutMapping("/{code}/protocol-response")
    // public ResponseEntity<SituationProtocol> updateProtocolResponse(@PathVariable
    // int code,
    // @RequestBody SituationProtocolDTO situationProtocolDTO) {
    // SituationProtocol updatedSituation =
    // situationProtocolService.updateProtocolResponse(code,situationProtocolDTO);

    // return ResponseEntity.ok(updatedSituation);
    // }

    // Atualizar data de recebimento
    @Operation(description = "Cria a data de recebimento de um protocolo.Insdispensável para casas de redirecionamento de protocolo para outro funcionário.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna o objeto 'situação protocolo' com a sua data de recebimento atualizada."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{code}/receipt-date")
    public ResponseEntity<SituationProtocol> updateProtocolReceiptDate(@PathVariable int code,
            @RequestBody SituationProtocolDTO situationProtocolDTO) {
        SituationProtocol updatedSituation = situationProtocolService.updateProtocolReceiptDate(code,
                situationProtocolDTO);
        return ResponseEntity.ok(updatedSituation);
    }

    // atualizar resposta de um protocolo
    @Operation(description = "Cria a data de recebimento de um protocolo.Insdispensável para casas de redirecionamento de protocolo para outro funcionário.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna o objeto 'situação protocolo' com a sua data de recebimento atualizada."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{protocolNumber}/protocolResponse")
    public ResponseEntity<SituationProtocol> updateResponse(
            @PathVariable(required = false) Long protocolNumber,
            @RequestBody SituationProtocolDTO situationProtocolDTO) {

        if (protocolNumber == null) {
            return ResponseEntity.badRequest().body(null); // Ou lance uma exceção
        }

        try {
            SituationProtocol updatedProtocol = situationProtocolService
                    .updateProtocolResponse(Optional.ofNullable(protocolNumber), situationProtocolDTO);
            return ResponseEntity.ok(updatedProtocol);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    } // UPDATES

    // encontrar protocolo com a resposta
    @Operation(description = "Cria a data de recebimento de um protocolo.Insdispensável para casas de redirecionamento de protocolo para outro funcionário.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna o objeto 'situação protocolo' com a sua data de recebimento atualizada."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping(value = "/number/{protocolNumber}/situation")
    public SituationProtocol findByProtocolNumberWithResponse(@PathVariable Long protocolNumber) {
        Optional<Protocol> protocolOpt = protocolRepository.findByProtocolNumber(protocolNumber);
        if (protocolOpt.isPresent()) {
            Protocol protocol = protocolOpt.get();
            SituationProtocol situationProtocol = situationProtocolService.findByProtocol(protocol);
            return situationProtocol;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Protocolo não encontrado");
        }
    }

    // encontrar a resposta de um protocolo
    @Operation(description = "Mostra a resposta que o protoloco recebeu.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna a resposta do protocolo."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping(value = "/{protocolNumber}/response")
    public List<Object> findByProtocolResponse(@PathVariable Long protocolNumber) {
        Optional<Protocol> protocolOpt = protocolRepository.findByProtocolNumber(protocolNumber);
        if (protocolOpt.isPresent()) {
            Protocol protocol = protocolOpt.get();
            SituationProtocol situationProtocol = situationProtocolService.findByProtocol(protocol);

            List<Object> responseList = new ArrayList<>();
            responseList.add(protocol.getProtocolNumber()); // Adicionando o número do protocolo
            responseList.add(situationProtocol.getProtocolResponse()); // Adicionando a resposta do protocolo
            responseList.add(protocol.getProtocolStatus()); // Adicionando o status do protocolo

            return responseList;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Protocolo não encontrado");
        }
    }

    @Operation(description = "Lista protocolos que não possuem registro na tabela situação protocolo.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um arrayList de protocolos sem situação."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping("/autoSituations")
    public SituationProtocol listProtocolNotSituation() {
        return situationProtocolService.findUnregisteredProtocols();
    }

    // deletar situação de protocolo
    @Operation(description = "Deleta uma situação de protocolo na aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Deleta uma situação de protocolo e exibe uma mensagem de sucesso."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Object> deleteSituationProtocol(@PathVariable int code) {
        situationProtocolService.deleteSituationProtocol(code);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Funcionário deletado com sucesso!");
        return ResponseEntity.ok(message);

    }

}
