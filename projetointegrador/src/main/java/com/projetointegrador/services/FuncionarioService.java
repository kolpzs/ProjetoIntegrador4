package com.projetointegrador.services;

import com.projetointegrador.entities.FuncionarioEntity;
import com.projetointegrador.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioEntity save(FuncionarioEntity funcionarioEntity) {
        return funcionarioRepository.save(funcionarioEntity);
    }

    public FuncionarioEntity findById(Long id) {
        return funcionarioRepository.findById(id).orElseThrow();
    }

    public List<FuncionarioEntity> findAll() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioEntity update(FuncionarioEntity funcionarioEntity) {
        FuncionarioEntity base = findById(funcionarioEntity.getId());
        if(Objects.equals(funcionarioEntity.getId(), base.getId())) {
            if(funcionarioEntity.getNome() != null) {
                base.setNome(funcionarioEntity.getNome());
            }
            if(funcionarioEntity.getCpf() != null) {
                base.setCpf(funcionarioEntity.getCpf());
            }
            if(funcionarioEntity.getEmail() != null) {
                base.setEmail(funcionarioEntity.getEmail());
            }
            if(funcionarioEntity.getTelefone() != null) {
                base.setTelefone(funcionarioEntity.getTelefone());
            }
            return save(base);
        }
        return null;
    }

    public String delete(Long id) {
        funcionarioRepository.delete(findById(id));
        return "Funcionario removido com sucesso!";
    }
}
