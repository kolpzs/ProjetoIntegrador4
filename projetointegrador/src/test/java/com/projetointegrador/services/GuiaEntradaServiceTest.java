package com.projetointegrador.services;

import com.projetointegrador.entities.GuiaEntradaEntity;
import com.projetointegrador.entities.ProdutoEntity;
import com.projetointegrador.repositories.FornecedorRepository;
import com.projetointegrador.repositories.FuncionarioRepository;
import com.projetointegrador.repositories.GuiaEntradaRepository;
import com.projetointegrador.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GuiaEntradaService {

    @Autowired
    private GuiaEntradaRepository guia_entradaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public GuiaEntradaEntity save(GuiaEntradaEntity guia_entradaEntity, Long produtos_id, Long fornecedor_id, Long funcionario_id) {
        guia_entradaEntity.setProduto(produtoRepository.findById(produtos_id).orElseThrow());
        guia_entradaEntity.setFornecedor(fornecedorRepository.findById(fornecedor_id).orElseThrow());
        guia_entradaEntity.setFuncionario(funcionarioRepository.findById(funcionario_id).orElseThrow());
        ProdutoEntity produto = produtoRepository.findById(produtos_id).orElseThrow();
        produto.setQuantidade_atual(guia_entradaEntity.getQuantidade());
        produtoRepository.save(produto);
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
        if (Objects.equals(guia_entradaEntity.getId(), base.getId())) {
            if (guia_entradaEntity.getData() != null) {
                base.setData(guia_entradaEntity.getData());
            }
            if (guia_entradaEntity.getValor() > 0) {
                base.setValor(guia_entradaEntity.getValor());
            }
            if (guia_entradaEntity.getQuantidade() > 0) {
                base.setQuantidade(guia_entradaEntity.getQuantidade());
            }
            if (guia_entradaEntity.getProduto() != null) {
                base.setProduto(guia_entradaEntity.getProduto());
            }
            if (guia_entradaEntity.getFornecedor() != null) {
                base.setFornecedor(guia_entradaEntity.getFornecedor());
            }
            return save(base, base.getProduto().getId(), base.getFornecedor().getId(), base.getFuncionario().getId());
        }
        return null;
    }

    public String delete(Long id) {
        guia_entradaRepository.delete(findById(id));
        return "GuiaEntrada removido com sucesso!";
    }
}