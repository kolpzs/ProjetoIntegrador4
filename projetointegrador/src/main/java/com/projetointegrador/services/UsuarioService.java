package com.projetointegrador.services;

import com.projetointegrador.entities.UsuarioEntity;
import com.projetointegrador.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity save(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    public UsuarioEntity findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public List<UsuarioEntity> findAll() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity update(UsuarioEntity usuarioEntity) {
        UsuarioEntity base = findById(usuarioEntity.getId());
        if(Objects.equals(usuarioEntity.getId(), base.getId())) {
            if(usuarioEntity.getNome() != null) {
                base.setNome(usuarioEntity.getNome());
            }
            if(usuarioEntity.getSenha() != null) {
                base.setSenha(usuarioEntity.getSenha());
            }
            return save(base);
        }
        return null;
    }

    public String delete(Long id) {
        usuarioRepository.delete(findById(id));
        return "Usuario removido com sucesso!";
    }
}
