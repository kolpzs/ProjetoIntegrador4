package com.projetointegrador.services;

import com.projetointegrador.entities.GuiaSaidaEntity;
import com.projetointegrador.repositories.GuiaSaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GuiaSaidaService {

    @Autowired
    private GuiaSaidaRepository guia_saidaRepository;

    public GuiaSaidaEntity save(GuiaSaidaEntity guia_saidaEntity) {
        return guia_saidaRepository.save(guia_saidaEntity);
    }

    public GuiaSaidaEntity findById(Long id) {
        return guia_saidaRepository.findById(id).orElseThrow();
    }

    public List<GuiaSaidaEntity> findAll() {
        return guia_saidaRepository.findAll();
    }

    public GuiaSaidaEntity update(GuiaSaidaEntity guia_saidaEntity) {
        GuiaSaidaEntity base = findById(guia_saidaEntity.getId());
        if(Objects.equals(guia_saidaEntity.getId(), base.getId())) {
            if(guia_saidaEntity.getData() != null) {
                base.setData(guia_saidaEntity.getData());
            }
            if(guia_saidaEntity.getValor() > 0) {
                base.setValor(guia_saidaEntity.getValor());
            }
            if(guia_saidaEntity.getQuantidade() > 0) {
                base.setQuantidade(guia_saidaEntity.getQuantidade());
            }
            return save(base);
        }
        return null;
    }

    public String delete(Long id) {
        guia_saidaRepository.delete(findById(id));
        return "GuiaSaida removido com sucesso!";
    }
}
