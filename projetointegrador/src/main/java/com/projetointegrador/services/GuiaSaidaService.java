package com.projetointegrador.services;

import com.projetointegrador.entities.ClienteEntity;
import com.projetointegrador.entities.GuiaSaidaEntity;
import com.projetointegrador.entities.ProdutoEntity;
import com.projetointegrador.repositories.ClienteRepository;
import com.projetointegrador.repositories.FuncionarioRepository;
import com.projetointegrador.repositories.GuiaSaidaRepository;
import com.projetointegrador.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GuiaSaidaService {

    @Autowired
    private GuiaSaidaRepository guia_saidaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public GuiaSaidaEntity save(GuiaSaidaEntity guia_saidaEntity, Long produto_id, Long cliente_id, Long funcionario_id) {
        guia_saidaEntity.setProduto(produtoRepository.findById(produto_id).orElseThrow());
        guia_saidaEntity.setCliente(clienteRepository.findById(cliente_id).orElseThrow());
        guia_saidaEntity.setFuncionario(funcionarioRepository.findById(funcionario_id).orElseThrow());
        ProdutoEntity produto = produtoRepository.findById(produto_id).orElseThrow();
        produto.setQuantidade_atual(guia_saidaEntity.getQuantidade());
        produtoRepository.save(produto);
        return guia_saidaRepository.save(guia_saidaEntity);
    }

    public GuiaSaidaEntity findById(Long id) {
        return guia_saidaRepository.findById(id).orElseThrow();
    }

    public List<GuiaSaidaEntity> findAll() {
        return guia_saidaRepository.findAll();
    }

    public GuiaSaidaEntity update(GuiaSaidaEntity guia_saidaEntity) {
        GuiaSaidaEntity base = findById(guia_saidaEntity.getId());
        if (Objects.equals(guia_saidaEntity.getId(), base.getId())) {
            if (guia_saidaEntity.getData() != null) {
                base.setData(guia_saidaEntity.getData());
            }
            if (guia_saidaEntity.getValor() > 0) {
                base.setValor(guia_saidaEntity.getValor());
            }
            if (guia_saidaEntity.getQuantidade() > 0) {
                base.setQuantidade(guia_saidaEntity.getQuantidade());
            }
            if (guia_saidaEntity.getProduto() != null) {
                base.setProduto(guia_saidaEntity.getProduto());
            }
            if (guia_saidaEntity.getCliente() != null) {
                base.setCliente(guia_saidaEntity.getCliente());
            }
            return save(base, base.getProduto().getId(), base.getCliente().getId(), base.getFuncionario().getId());
        }
        return null;
    }

    public String delete(Long id) {
        guia_saidaRepository.delete(findById(id));
        return "GuiaSaida removido com sucesso!";
    }
}
