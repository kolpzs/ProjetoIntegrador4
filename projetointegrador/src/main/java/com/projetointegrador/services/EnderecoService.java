package com.projetointegrador.services;

import com.projetointegrador.entities.EnderecoEntity;
import com.projetointegrador.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoEntity save(EnderecoEntity enderecoEntity) {
        return enderecoRepository.save(enderecoEntity);
    }

    public EnderecoEntity findById(Long id) {
        return enderecoRepository.findById(id).orElseThrow();
    }

    public List<EnderecoEntity> findAll() {
        return enderecoRepository.findAll();
    }

    public EnderecoEntity update(EnderecoEntity enderecoEntity) {
        EnderecoEntity base = findById(enderecoEntity.getId());
        if(Objects.equals(enderecoEntity.getId(), base.getId())) {
            if(enderecoEntity.getRua() != null) {
                base.setRua(enderecoEntity.getRua());
            }
            if(enderecoEntity.getNumero() > 0) {
                base.setNumero(enderecoEntity.getNumero());
            }
            if(enderecoEntity.getBairro() != null) {
                base.setBairro(enderecoEntity.getBairro());
            }
            if(enderecoEntity.getCidade() != null) {
                base.setCidade(enderecoEntity.getCidade());
            }
            if(enderecoEntity.getEstado() != null) {
                base.setEstado(enderecoEntity.getEstado());
            }
            if(enderecoEntity.getCep() != null) {
                base.setCep(enderecoEntity.getCep());
            }
            return save(base);
        }
        return null;
    }

    public String delete(Long id) {
        enderecoRepository.delete(findById(id));
        return "Endereco removido com sucesso!";
    }
}
