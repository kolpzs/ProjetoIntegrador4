package com.projetointegrador.services;

import com.projetointegrador.entities.FornecedorEntity;
import com.projetointegrador.entities.FuncionarioEntity;
import com.projetointegrador.entities.GuiaEntradaEntity;
import com.projetointegrador.entities.ProdutoEntity;
import com.projetointegrador.repositories.FornecedorRepository;
import com.projetointegrador.repositories.FuncionarioRepository;
import com.projetointegrador.repositories.GuiaEntradaRepository;
import com.projetointegrador.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public GuiaEntradaEntity save(GuiaEntradaEntity guia_entradaEntity, List<Long> produtos_id, Long fornecedor_id, Long funcionario_id) {
        List<ProdutoEntity> produtos = new ArrayList<>();
        FornecedorEntity fornecedor = fornecedorRepository.findById(fornecedor_id).orElseThrow();
        FuncionarioEntity funcionario = funcionarioRepository.findById(funcionario_id).orElseThrow();
        for (Long produto_id: produtos_id) {
            ProdutoEntity produto = produtoRepository.findById(produto_id).orElseThrow();
            produtos.add(produto);
        }
        guia_entradaEntity.setProdutos(produtos);
        guia_entradaEntity.setFornecedor(fornecedor);
        guia_entradaEntity.setFuncionario(funcionario);
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
            List<Long> ids = new ArrayList<>();
            for(ProdutoEntity produto : base.getProdutos()) {
                Long id = produto.getId();
                ids.add(id);
            }
            return save(base, ids, base.getFornecedor().getId(), base.getFuncionario().getId());
        }
        return null;
    }

    public String delete(Long id) {
        guia_entradaRepository.delete(findById(id));
        return "GuiaEntrada removido com sucesso!";
    }
}
