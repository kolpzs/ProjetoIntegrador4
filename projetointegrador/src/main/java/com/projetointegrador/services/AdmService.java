package com.projetointegrador.services;

import com.projetointegrador.entities.AdmEntity;
import com.projetointegrador.entities.EnderecoEntity;
import com.projetointegrador.entities.FuncionarioEntity;
import com.projetointegrador.repositories.AdmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdmService {

    @Autowired
    private AdmRepository admRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private EnderecoService enderecoService;

    public AdmEntity save(AdmEntity admEntity) {
        return admRepository.save(admEntity);
    }

    public AdmEntity findById(Long id) {
        return admRepository.findById(id).orElseThrow();
    }

    public List<AdmEntity> findAll() {
        return admRepository.findAll();
    }

    public AdmEntity update(AdmEntity admEntity) {
        AdmEntity base = findById(admEntity.getId());
        if (Objects.equals(admEntity.getId(), base.getId())) {
            if (admEntity.getNome() != null) {
                base.setNome(admEntity.getNome());
            }
            if (admEntity.getSenha() != null) {
                base.setSenha(admEntity.getSenha());
            }
            return save(base);
        }
        return null;
    }

    public String delete(Long id) {
        admRepository.delete(findById(id));
        return "Usuario removido com sucesso!";
    }

    public boolean validaLogin(Long id, String senha) {
        AdmEntity adm = findById(id);
        return Objects.equals(adm.getId(), id) && Objects.equals(adm.getSenha(), senha);
    }

    public FuncionarioEntity saveFuncionario(Long id, String senha, FuncionarioEntity funcionario) {
        if (validaLogin(id, senha)) {
            return funcionarioService.save(funcionario);
        }
        return null;
    }

    public EnderecoEntity saveEndereco(Long id, String senha, EnderecoEntity endereco) {
        if (validaLogin(id, senha)) {
            return enderecoService.save(endereco);
        }

        return null;
    }

    public EnderecoEntity associarEnderecoFuncionario(Long funcionario_id, Long endereco_id) {
        return enderecoService.updateEnderecoFuncionario(funcionario_id, endereco_id);
    }

    public boolean login(String user, String senha) {
        AdmEntity adm = findById(Long.parseLong(user));
        return Objects.equals(adm.getNome(), user) && Objects.equals(adm.getSenha(), senha);
    }
}
