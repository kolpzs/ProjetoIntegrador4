package com.projetointegrador.services;

import com.projetointegrador.entities.FornecedorEntity;
import com.projetointegrador.entities.ProdutoEntity;
import com.projetointegrador.repositories.FornecedorRepository;
import com.projetointegrador.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public ProdutoEntity save(ProdutoEntity produto, Long id) {
        FornecedorEntity fornecedor = fornecedorRepository.findById(id).orElseThrow();
        List<FornecedorEntity> fornecedores = new ArrayList<>();
        fornecedores.add(fornecedor);
        produto.setFornecedores(fornecedores);
        return produtoRepository.save(produto);
    }

    public ProdutoEntity findById(Long id) {
        return produtoRepository.findById(id).orElseThrow();
    }

    public List<ProdutoEntity> findAll() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity update(ProdutoEntity produtoEntity) {
        ProdutoEntity base = findById(produtoEntity.getId());
        if (Objects.equals(produtoEntity.getId(), base.getId())) {
            if (produtoEntity.getNome() != null) {
                base.setNome(produtoEntity.getNome());
            }
            if (produtoEntity.getMarca() != null) {
                base.setMarca(produtoEntity.getMarca());
            }
            if (produtoEntity.getModelo() != null) {
                base.setModelo(produtoEntity.getModelo());
            }
            List<FornecedorEntity> fornecedores = produtoEntity.getFornecedores();
            return save(base, fornecedores.get(0).getId());
        }
        return null;
    }

    public String delete(Long id) {
        produtoRepository.delete(findById(id));
        return "Produto removido com sucesso!";
    }
}
