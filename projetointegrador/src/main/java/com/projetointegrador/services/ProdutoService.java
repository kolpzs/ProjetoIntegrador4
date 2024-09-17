package com.projetointegrador.services;

import com.projetointegrador.entities.ProdutoEntity;
import com.projetointegrador.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoEntity save(ProdutoEntity produtoEntity) {
        return produtoRepository.save(produtoEntity);
    }

    public ProdutoEntity findById(Long id) {
        return produtoRepository.findById(id).orElseThrow();
    }

    public List<ProdutoEntity> findAll() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity update(ProdutoEntity produtoEntity) {
        ProdutoEntity base = findById(produtoEntity.getId());
        if(Objects.equals(produtoEntity.getId(), base.getId())) {
            if(produtoEntity.getNome() != null) {
                base.setNome(produtoEntity.getNome());
            }
            if(produtoEntity.getMarca() != null) {
                base.setMarca(produtoEntity.getMarca());
            }
            if(produtoEntity.getModelo() != null) {
                base.setModelo(produtoEntity.getModelo());
            }
            return save(base);
        }
        return null;
    }

    public String delete(Long id) {
        produtoRepository.delete(findById(id));
        return "Produto removido com sucesso!";
    }
}
