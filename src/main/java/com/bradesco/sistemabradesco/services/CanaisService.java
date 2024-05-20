package com.bradesco.sistemabradesco.services;

import com.bradesco.sistemabradesco.dto.CanaisDTO;
import com.bradesco.sistemabradesco.models.Canais;
import com.bradesco.sistemabradesco.repository.CanaisRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanaisService {

    @Autowired
    private CanaisRepository canaisRepository;

   
    // public CanaisService(CanaisRepository canaisRepository) {
    //    this.canaisRepository = canaisRepository;
    // }

    public List<Canais> listarCanais() {
        return canaisRepository.findAll();
    }

    public Canais criarCanal(CanaisDTO canaisDTO) {
        Canais novoCanal = new Canais();
        BeanUtils.copyProperties(canaisDTO, novoCanal);
        return canaisRepository.save(novoCanal);
    }

    // Outros métodos do serviço de canais (se houver)
}
