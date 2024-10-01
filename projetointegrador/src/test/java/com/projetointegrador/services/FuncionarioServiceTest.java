package com.projetointegrador.services;

import com.projetointegrador.entities.*;
import com.projetointegrador.repositories.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class FuncionarioServiceTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private FornecedorService fornecedorService;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private GuiaEntradaService guiaEntradaService;

    @Mock
    private GuiaSaidaService guiaSaidaService;

    @Mock
    private EnderecoService enderecoService;

    private FuncionarioEntity funcionario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        funcionario = new FuncionarioEntity();
        funcionario.setId(1L);
        funcionario.setNome("Test Funcionario");
        funcionario.setCpf("123456789");
        funcionario.setEmail("test@domain.com");
        funcionario.setTelefone("1234567890");
    }

    @Test
    void testSave() {
        when(funcionarioRepository.save(any(FuncionarioEntity.class))).thenReturn(funcionario);

        FuncionarioEntity result = funcionarioService.save(funcionario);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Funcionario", result.getNome());
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    void testFindById() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        FuncionarioEntity result = funcionarioService.findById(1L);

        assertNotNull(result);
        assertEquals("Test Funcionario", result.getNome());
        verify(funcionarioRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateFuncionario() {
        FuncionarioEntity updatedFuncionario = new FuncionarioEntity();
        updatedFuncionario.setId(1L);
        updatedFuncionario.setNome("Updated Funcionario");
        updatedFuncionario.setCpf("987654321");
        updatedFuncionario.setEmail("updated@domain.com");
        updatedFuncionario.setTelefone("0987654321");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        when(funcionarioRepository.save(any(FuncionarioEntity.class))).thenReturn(updatedFuncionario);

        FuncionarioEntity result = funcionarioService.update(updatedFuncionario);

        assertNotNull(result);
        assertEquals("Updated Funcionario", result.getNome());
        assertEquals("987654321", result.getCpf());
        verify(funcionarioRepository, times(1)).save(updatedFuncionario);
    }

    @Test
    void testDelete() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        String result = funcionarioService.delete(1L);

        assertEquals("Funcionario removido com sucesso!", result);
        verify(funcionarioRepository, times(1)).delete(funcionario);
    }

    @Test
    void testSaveCliente() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(1L);
        cliente.setNome("Cliente Test");

        when(clienteService.save(any(ClienteEntity.class))).thenReturn(cliente);

        ClienteEntity result = funcionarioService.saveCliente(cliente);

        assertNotNull(result);
        assertEquals("Cliente Test", result.getNome());
        verify(clienteService, times(1)).save(cliente);
    }

    @Test
    void testSaveFornecedor() {
        FornecedorEntity fornecedor = new FornecedorEntity();
        fornecedor.setId(1L);
        fornecedor.setNome_social("Fornecedor Test");

        when(fornecedorService.save(any(FornecedorEntity.class))).thenReturn(fornecedor);

        FornecedorEntity result = funcionarioService.saveFornecedor(fornecedor);

        assertNotNull(result);
        assertEquals("Fornecedor Test", result.getNome_social());
        verify(fornecedorService, times(1)).save(fornecedor);
    }

    @Test
    void testSaveProduto() {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(1L);
        produto.setNome("Produto Test");

        when(produtoService.save(any(ProdutoEntity.class), anyLong())).thenReturn(produto);

        ProdutoEntity result = funcionarioService.saveProduto(produto, 1L);

        assertNotNull(result);
        assertEquals("Produto Test", result.getNome());
        verify(produtoService, times(1)).save(produto, 1L);
    }

    @Test
    void testAssociarEnderecoFornecedor() {
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setId(1L);
        endereco.setRua("Rua Teste");

        when(enderecoService.updateEnderecoFornecedor(anyLong(), anyLong())).thenReturn(endereco);

        EnderecoEntity result = funcionarioService.associarEnderecoFornecedor(1L, 1L);

        assertNotNull(result);
        assertEquals("Rua Teste", result.getRua());
        verify(enderecoService, times(1)).updateEnderecoFornecedor(1L, 1L);
    }
}