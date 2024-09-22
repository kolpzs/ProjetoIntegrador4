package com.projetointegrador.services;

import com.projetointegrador.entities.FornecedorEntity;
import com.projetointegrador.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public FornecedorEntity save(FornecedorEntity fornecedorEntity) {
        return fornecedorRepository.save(fornecedorEntity);
    }

    public FornecedorEntity findById(Long id) {
        return fornecedorRepository.findById(id).orElseThrow();
    }

    public List<FornecedorEntity> findAll() {
        return fornecedorRepository.findAll();
    }

    public FornecedorEntity update(FornecedorEntity fornecedorEntity) {
        FornecedorEntity base = findById(fornecedorEntity.getId());
        if (Objects.equals(fornecedorEntity.getId(), base.getId())) {
            if (fornecedorEntity.getNome_social() != null) {
                base.setNome_social(fornecedorEntity.getNome_social());
            }
            if (fornecedorEntity.getCnpj() != null) {
                base.setCnpj(fornecedorEntity.getCnpj());
            }
            if (fornecedorEntity.getEmail() != null) {
                base.setEmail(fornecedorEntity.getEmail());
            }
            if (fornecedorEntity.getTelefone() != null) {
                base.setTelefone(fornecedorEntity.getTelefone());
            }
            return save(base);
        }
        return null;
    }

    public String delete(Long id) {
        fornecedorRepository.delete(findById(id));
        return "Fornecedor removido com sucesso!";
    }
}
