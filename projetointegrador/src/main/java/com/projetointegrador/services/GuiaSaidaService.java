package com.projetointegrador.services;

import com.projetointegrador.entities.*;
import com.projetointegrador.repositories.ClienteRepository;
import com.projetointegrador.repositories.FuncionarioRepository;
import com.projetointegrador.repositories.GuiaSaidaRepository;
import com.projetointegrador.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public GuiaSaidaEntity save(GuiaSaidaEntity guia_saidaEntity, List<Long> produtos_id, Long cliente_id, Long funcionario_id) {
        List<ProdutoEntity> produtos = new ArrayList<>();
        ClienteEntity cliente = clienteRepository.findById(cliente_id).orElseThrow();
        FuncionarioEntity funcionario = funcionarioRepository.findById(funcionario_id).orElseThrow();
        for (Long produto_id: produtos_id) {
            ProdutoEntity produto = produtoRepository.findById(produto_id).orElseThrow();
            produtos.add(produto);
        }
        guia_saidaEntity.setProdutos(produtos);
        guia_saidaEntity.setCliente(cliente);
        guia_saidaEntity.setFuncionario(funcionario);
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
            List<Long> ids = new ArrayList<>();
            for(ProdutoEntity produto: base.getProdutos()) {
                ids.add(produto.getId());
            }
            return save(base, ids, base.getCliente().getId(), base.getFuncionario().getId());
        }
        return null;
    }

    public String delete(Long id) {
        guia_saidaRepository.delete(findById(id));
        return "GuiaSaida removido com sucesso!";
    }

    public GuiaSaidaEntity updateCliente(Long guia_saida_id, Long cliente_id) {
        GuiaSaidaEntity base = findById(guia_saida_id);
        ClienteEntity cliente = clienteRepository.findById(cliente_id).orElseThrow();
        if (!Objects.equals(base.getCliente().getId(), cliente.getId())) {
            base.setCliente(cliente);
            return update(base);
        }

        return base;
    }
}
