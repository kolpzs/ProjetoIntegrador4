package com.projetointegrador.services;

import com.projetointegrador.entities.GuiaEntradaEntity;
import com.projetointegrador.repositories.GuiaEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GuiaEntradaService {

    @Autowired
    private GuiaEntradaRepository guia_entradaRepository;

    public GuiaEntradaEntity save(GuiaEntradaEntity guia_entradaEntity) {
        return guia_entradaRepository.save(guia_entradaEntity);
    }

    public GuiaEntradaEntity findById(Long id) {
        return guia_entradaRepository.findById(id).orElseThrow();
    }

    public List<GuiaEntradaEntity> findAll() {
        return guia_entradaRepository.findAll();
    }

    public GuiaEntradaEntity update(GuiaEntradaEntity guia_entradaEntity) {
        GuiaEntradaEntity base = findById(guia_entradaEntity.getId());
        if(Objects.equals(guia_entradaEntity.getId(), base.getId())) {
            if(guia_entradaEntity.getData() != null) {
                base.setData(guia_entradaEntity.getData());
            }
            if(guia_entradaEntity.getValor() > 0) {
                base.setValor(guia_entradaEntity.getValor());
            }
            if(guia_entradaEntity.getQuantidade() > 0) {
                base.setQuantidade(guia_entradaEntity.getQuantidade());
            }
            return save(base);
        }
        return null;
    }

    public String delete(Long id) {
        guia_entradaRepository.delete(findById(id));
        return "GuiaEntrada removido com sucesso!";
    }
}
