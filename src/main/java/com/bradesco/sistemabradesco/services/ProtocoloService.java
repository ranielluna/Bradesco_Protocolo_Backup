package com.bradesco.sistemabradesco.services;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bradesco.sistemabradesco.dto.ProtocoloDTO;
import com.bradesco.sistemabradesco.models.Canais;
// import com.bradesco.sistemabradesco.models.Cliente;
import com.bradesco.sistemabradesco.models.Protocolo;
import com.bradesco.sistemabradesco.models.TipoProtocolo;
import com.bradesco.sistemabradesco.repository.ProtocoloRepository;

@Service
public class ProtocoloService {

    @Autowired
    private ProtocoloRepository protocoloRepository;

    public Protocolo abrirProtocolo(ProtocoloDTO protocoloDTO) {

        if (protocoloDTO.getDescricao() == null || protocoloDTO.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("Descrição do protocolo é obrigatória.");
        }

        Protocolo protocolo = new Protocolo();
        protocolo.setCodigo(protocoloDTO.getCodigo());
        protocolo.setDataAbertura(LocalDate.now());
        protocolo.setData_prazo(LocalDate.now());
        protocolo.setDescricao(protocoloDTO.getDescricao());


        // Cliente cliente = protocoloDTO.getCliente();
        Canais canais = protocoloDTO.getCanal();
        TipoProtocolo tpProtocolo = protocoloDTO.getTipoProtocolo();

        // if (cliente == null) {

        //     cliente = new Cliente();
        //     cliente.setNome("Cliente Anônimo");
        //     cliente.setEmail("anonimo@example.com");
        // }

        // protocolo.setCliente(cliente);
        protocolo.setCanal(canais);
        protocolo.setTipoProtocolo(tpProtocolo);
        protocolo.setNumeroProtocolo(protocoloDTO.getNumeroProtocolo());

        try {
            return protocoloRepository.save(protocolo);
        } catch (Exception e) {
            // Lidar com qualquer exceção ao salvar o protocolo
            throw new RuntimeException("Erro ao salvar o protocolo: " + e.getMessage());
        }
    }
}
