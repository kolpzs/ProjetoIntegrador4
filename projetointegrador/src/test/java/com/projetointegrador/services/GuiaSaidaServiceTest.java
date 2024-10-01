package com.projetointegrador.services;

import com.projetointegrador.entities.ClienteEntity;
import com.projetointegrador.entities.FuncionarioEntity;
import com.projetointegrador.entities.GuiaSaidaEntity;
import com.projetointegrador.entities.ProdutoEntity;
import com.projetointegrador.repositories.ClienteRepository;
import com.projetointegrador.repositories.FuncionarioRepository;
import com.projetointegrador.repositories.GuiaSaidaRepository;
import com.projetointegrador.repositories.ProdutoRepository;
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

public class GuiaSaidaServiceTest {

    @Mock
    private GuiaSaidaRepository guiaSaidaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private GuiaSaidaService guiaSaidaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveGuiaSaida() {
        GuiaSaidaEntity guiaSaida = new GuiaSaidaEntity();
        guiaSaida.setQuantidade(5);
        guiaSaida.setValor(100.0F);

        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(1L);
        produto.setQuantidade_atual(10);

        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(1L);

        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setId(1L);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        when(guiaSaidaRepository.save(any(GuiaSaidaEntity.class))).thenReturn(guiaSaida);

        GuiaSaidaEntity result = guiaSaidaService.save(guiaSaida, 1L, 1L, 1L);
        assertNotNull(result);
        assertEquals(5, result.getQuantidade());
        assertEquals(100.0, result.getValor());

        verify(guiaSaidaRepository, times(1)).save(guiaSaida);
        verify(produtoRepository, times(1)).save(produto);
        assertEquals(5, produto.getQuantidade_atual()); // Verifica se a quantidade foi atualizada
    }

    @Test
    void testFindById() {
        GuiaSaidaEntity guiaSaida = new GuiaSaidaEntity();
        guiaSaida.setId(1L);
        guiaSaida.setValor(100.0F);

        when(guiaSaidaRepository.findById(1L)).thenReturn(Optional.of(guiaSaida));

        GuiaSaidaEntity result = guiaSaidaService.findById(1L);
        assertNotNull(result);
        assertEquals(100.0, result.getValor());

        verify(guiaSaidaRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        GuiaSaidaEntity guiaSaida1 = new GuiaSaidaEntity();
        GuiaSaidaEntity guiaSaida2 = new GuiaSaidaEntity();

        when(guiaSaidaRepository.findAll()).thenReturn(Arrays.asList(guiaSaida1, guiaSaida2));

        List<GuiaSaidaEntity> result = guiaSaidaService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(guiaSaidaRepository, times(1)).findAll();
    }

    @Test
    void testUpdateGuiaSaida() {
        GuiaSaidaEntity guiaSaida = new GuiaSaidaEntity();
        guiaSaida.setId(1L);
        guiaSaida.setQuantidade(5);
        guiaSaida.setValor(100.0F);

        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(1L);
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(1L);
        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setId(1L);

        GuiaSaidaEntity updatedGuiaSaida = new GuiaSaidaEntity();
        updatedGuiaSaida.setId(1L);
        updatedGuiaSaida.setQuantidade(10);
        updatedGuiaSaida.setValor(200.0F);

        when(guiaSaidaRepository.findById(1L)).thenReturn(Optional.of(guiaSaida));
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        when(guiaSaidaRepository.save(any(GuiaSaidaEntity.class))).thenReturn(guiaSaida);

        GuiaSaidaEntity result = guiaSaidaService.update(updatedGuiaSaida);
        assertNotNull(result);
        assertEquals(10, result.getQuantidade());
        assertEquals(200.0, result.getValor());

        verify(guiaSaidaRepository, times(1)).save(guiaSaida);
        assertEquals(10, produto.getQuantidade_atual());
    }

    @Test
    void testDeleteGuiaSaida() {
        GuiaSaidaEntity guiaSaida = new GuiaSaidaEntity();
        guiaSaida.setId(1L);

        when(guiaSaidaRepository.findById(1L)).thenReturn(Optional.of(guiaSaida));
        doNothing().when(guiaSaidaRepository).delete(guiaSaida);

        String result = guiaSaidaService.delete(1L);
        assertEquals("GuiaSaida removido com sucesso!", result);

        verify(guiaSaidaRepository, times(1)).delete(guiaSaida);
    }

    @Test
    void testSaveGuiaSaidaWithNonExistentProduto() {
        GuiaSaidaEntity guiaSaida = new GuiaSaidaEntity();

        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            guiaSaidaService.save(guiaSaida, 1L, 1L, 1L);
        });

        verify(guiaSaidaRepository, times(0)).save(any(GuiaSaidaEntity.class)); // Não deve salvar
    }

    @Test
    void testSaveGuiaSaidaWithNonExistentCliente() {
        GuiaSaidaEntity guiaSaida = new GuiaSaidaEntity();
        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(1L);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            guiaSaidaService.save(guiaSaida, 1L, 1L, 1L);
        });

        verify(guiaSaidaRepository, times(0)).save(any(GuiaSaidaEntity.class)); // Não deve salvar
    }

    @Test
    void testSaveGuiaSaidaWithNonExistentFuncionario() {
        GuiaSaidaEntity guiaSaida = new GuiaSaidaEntity();
        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(1L);
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(1L);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            guiaSaidaService.save(guiaSaida, 1L, 1L, 1L);
        });

        verify(guiaSaidaRepository, times(0)).save(any(GuiaSaidaEntity.class)); // Não deve salvar
    }
}
