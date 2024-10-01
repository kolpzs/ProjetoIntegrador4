package com.projetointegrador.services;

import com.projetointegrador.entities.*;
import com.projetointegrador.repositories.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    private GuiaEntradaServiceTest guiaEntradaService;

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
        funcionario.setNome("João");
        funcionario.setCpf("123.456.789-00");
        funcionario.setEmail("joao@email.com");
        funcionario.setTelefone("(45)99999-9399");
    }

    @Test
    void testSaveFuncionario() {
        when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);

        FuncionarioEntity savedFuncionario = funcionarioService.save(funcionario);

        assertNotNull(savedFuncionario);
        assertEquals("João", savedFuncionario.getNome());
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    void testFindById() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        FuncionarioEntity foundFuncionario = funcionarioService.findById(1L);

        assertNotNull(foundFuncionario);
        assertEquals(1L, foundFuncionario.getId());
        verify(funcionarioRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        when(funcionarioRepository.findAll()).thenReturn(Arrays.asList(funcionario));

        List<FuncionarioEntity> funcionarios = funcionarioService.findAll();

        assertNotNull(funcionarios);
        assertFalse(funcionarios.isEmpty());
        assertEquals(1, funcionarios.size());
        verify(funcionarioRepository, times(1)).findAll();
    }

    @Test
    void testUpdateFuncionario() {
        FuncionarioEntity updatedFuncionario = new FuncionarioEntity();
        updatedFuncionario.setId(1L);
        updatedFuncionario.setNome("João Atualizado");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        when(funcionarioRepository.save(any(FuncionarioEntity.class))).thenReturn(updatedFuncionario);

        FuncionarioEntity result = funcionarioService.update(updatedFuncionario);

        assertNotNull(result);
        assertEquals("João Atualizado", result.getNome());
        verify(funcionarioRepository, times(1)).findById(1L);
        verify(funcionarioRepository, times(1)).save(any(FuncionarioEntity.class));
    }

    @Test
    void testDeleteFuncionario() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        doNothing().when(funcionarioRepository).delete(funcionario);

        String result = funcionarioService.delete(1L);

        assertEquals("Funcionario removido com sucesso!", result);
        verify(funcionarioRepository, times(1)).findById(1L);
        verify(funcionarioRepository, times(1)).delete(funcionario);
    }

    @Test
    void testSaveCliente() {
        ClienteEntity cliente = new ClienteEntity();
        when(clienteService.save(cliente)).thenReturn(cliente);

        ClienteEntity savedCliente = funcionarioService.saveCliente(cliente);

        assertNotNull(savedCliente);
        verify(clienteService, times(1)).save(cliente);
    }

    @Test
    void testSaveFornecedor() {
        FornecedorEntity fornecedor = new FornecedorEntity();
        when(fornecedorService.save(fornecedor)).thenReturn(fornecedor);

        FornecedorEntity savedFornecedor = funcionarioService.saveFornecedor(fornecedor);

        assertNotNull(savedFornecedor);
        verify(fornecedorService, times(1)).save(fornecedor);
    }

    @Test
    void testSaveProduto() {
        ProdutoEntity produto = new ProdutoEntity();
        when(produtoService.save(produto, 1L)).thenReturn(produto);

        ProdutoEntity savedProduto = funcionarioService.saveProduto(produto, 1L);

        assertNotNull(savedProduto);
        verify(produtoService, times(1)).save(produto, 1L);
    }

    @Test
    void testSaveGuiaEntrada() {
        GuiaEntradaEntity guiaEntrada = new GuiaEntradaEntity();

        // Mocka o serviço GuiaEntradaService
        when(guiaEntradaService.save(any(GuiaEntradaEntity.class), anyLong(), anyLong(), anyLong()))
                .thenReturn(guiaEntrada); // Retorna a entidade mockada quando o método save é chamado

        // Testa o método saveGuiaEntrada do FuncionarioService
        GuiaEntradaEntity savedGuiaEntrada = funcionarioService.saveGuiaEntrada(guiaEntrada, 1L, 2L, 3L);

        // Verifica se a entidade não é nula
        assertNotNull(savedGuiaEntrada);

        // Verifica se o método save do GuiaEntradaService foi chamado com os parâmetros corretos
        verify(guiaEntradaService, times(1)).save(guiaEntrada, 1L, 2L, 3L);
    }

    @Test
    void testSaveGuiaSaida() {
        GuiaSaidaEntity guiaSaida = new GuiaSaidaEntity();
        when(guiaSaidaService.save(guiaSaida, 1L, 2L, 3L)).thenReturn(guiaSaida);

        GuiaSaidaEntity savedGuiaSaida = funcionarioService.saveGuiaSaida(guiaSaida, 1L, 2L, 3L);

        assertNotNull(savedGuiaSaida);
        verify(guiaSaidaService, times(1)).save(guiaSaida, 1L, 2L, 3L);
    }

    @Test
    void testAssociarEnderecoFornecedor() {
        EnderecoEntity endereco = new EnderecoEntity();
        when(enderecoService.updateEnderecoFornecedor(1L, 2L)).thenReturn(endereco);

        EnderecoEntity result = funcionarioService.associarEnderecoFornecedor(1L, 2L);

        assertNotNull(result);
        verify(enderecoService, times(1)).updateEnderecoFornecedor(1L, 2L);
    }
}
