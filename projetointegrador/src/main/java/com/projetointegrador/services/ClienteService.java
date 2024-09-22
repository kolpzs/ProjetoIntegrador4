package com.projetointegrador.services;

import com.projetointegrador.entities.ClienteEntity;
import com.projetointegrador.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteEntity save(ClienteEntity clienteEntity) {
        return clienteRepository.save(clienteEntity);
    }

    public ClienteEntity findById(Long id) {
        return clienteRepository.findById(id).orElseThrow();
    }

    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    public ClienteEntity update(ClienteEntity clienteEntity) {
        ClienteEntity base = findById(clienteEntity.getId());
        if (Objects.equals(clienteEntity.getId(), base.getId())) {
            if (clienteEntity.getNome() != null) {
                base.setNome(clienteEntity.getNome());
            }
            if (clienteEntity.getCpf() != null) {
                base.setCpf(clienteEntity.getCpf());
            }
            if (clienteEntity.getEmail() != null) {
                base.setEmail(clienteEntity.getEmail());
            }
            if (clienteEntity.getTelefone() != null) {
                base.setTelefone(clienteEntity.getTelefone());
            }
            return save(base);
        }
        return null;
    }

    public String delete(Long id) {
        clienteRepository.delete(findById(id));
        return "Cliente removido com sucesso!";
    }
}
