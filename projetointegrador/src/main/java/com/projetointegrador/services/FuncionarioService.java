package com.projetointegrador.services;

import com.projetointegrador.entities.*;
import com.projetointegrador.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private GuiaEntradaService guiaEntradaService;

    @Autowired
    private GuiaSaidaService guiaSaidaService;

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
        if (Objects.equals(funcionarioEntity.getId(), base.getId())) {
            if (funcionarioEntity.getNome() != null) {
                base.setNome(funcionarioEntity.getNome());
            }
            if (funcionarioEntity.getCpf() != null) {
                base.setCpf(funcionarioEntity.getCpf());
            }
            if (funcionarioEntity.getEmail() != null) {
                base.setEmail(funcionarioEntity.getEmail());
            }
            if (funcionarioEntity.getTelefone() != null) {
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

    public ClienteEntity saveCliente(ClienteEntity cliente) {
        return clienteService.save(cliente);
    }

    public FornecedorEntity saveFornecedor(FornecedorEntity fornecedor) {
        return fornecedorService.save(fornecedor);
    }

    public ProdutoEntity saveProduto(ProdutoEntity produto, Long id) {
        return produtoService.save(produto, id);
    }

    public GuiaEntradaEntity saveGuiaEntrada(GuiaEntradaEntity guiaEntrada, List<Long> produtos_id, Long fornecedor_id, Long funcionario_id) {
        return guiaEntradaService.save(guiaEntrada, produtos_id, fornecedor_id, funcionario_id);
    }

    public GuiaSaidaEntity saveGuiaSaida(GuiaSaidaEntity guiaSaida, List<Long> produtos_id, Long cliente_id, Long funcionario_id) {
        return guiaSaidaService.save(guiaSaida, produtos_id, cliente_id, funcionario_id);
    }
}
