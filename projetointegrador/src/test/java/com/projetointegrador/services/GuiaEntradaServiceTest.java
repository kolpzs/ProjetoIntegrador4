package com.projetointegrador.services;

import com.projetointegrador.entities.FornecedorEntity;
import com.projetointegrador.entities.FuncionarioEntity;
import com.projetointegrador.entities.GuiaEntradaEntity;
import com.projetointegrador.entities.ProdutoEntity;
import com.projetointegrador.repositories.FornecedorRepository;
import com.projetointegrador.repositories.FuncionarioRepository;
import com.projetointegrador.repositories.GuiaEntradaRepository;
import com.projetointegrador.repositories.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuiaEntradaServiceTest {

    @Mock
    private GuiaEntradaRepository guiaEntradaRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private GuiaEntradaService guiaEntradaService;

    private GuiaEntradaEntity guiaEntrada;
    private ProdutoEntity produto;
    private FornecedorEntity fornecedor;
    private FuncionarioEntity funcionario;

    @BeforeEach
    void setUp() {
        produto = new ProdutoEntity();
        produto.setId(1L);
        produto.setQuantidade_atual(10);

        fornecedor = new FornecedorEntity();
        fornecedor.setId(1L);

        funcionario = new FuncionarioEntity();
        funcionario.setId(1L);

        guiaEntrada = new GuiaEntradaEntity();
        guiaEntrada.setId(1L);
        guiaEntrada.setData(new Date());
        guiaEntrada.setValor(100.0f);
        guiaEntrada.setQuantidade(5);
        guiaEntrada.setProduto(produto);
        guiaEntrada.setFornecedor(fornecedor);
        guiaEntrada.setFuncionario(funcionario);
    }

    @Test
    public void testSaveGuiaEntrada() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));
        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(fornecedor));
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(funcionario));
        when(guiaEntradaRepository.save(any(GuiaEntradaEntity.class))).thenReturn(guiaEntrada);

        GuiaEntradaEntity savedGuiaEntrada = guiaEntradaService.save(guiaEntrada, produto.getId(), fornecedor.getId(), funcionario.getId());

        assertNotNull(savedGuiaEntrada);
        assertEquals(guiaEntrada.getId(), savedGuiaEntrada.getId());
        assertEquals(guiaEntrada.getQuantidade(), produto.getQuantidade_atual()); // Produto atualizado
        verify(guiaEntradaRepository, times(1)).save(guiaEntrada);
    }

    @Test
    public void testFindById() {
        when(guiaEntradaRepository.findById(anyLong())).thenReturn(Optional.of(guiaEntrada));

        GuiaEntradaEntity foundGuiaEntrada = guiaEntradaService.findById(1L);

        assertNotNull(foundGuiaEntrada);
        assertEquals(guiaEntrada.getId(), foundGuiaEntrada.getId());
        verify(guiaEntradaRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        when(guiaEntradaRepository.findAll()).thenReturn(Arrays.asList(guiaEntrada));

        List<GuiaEntradaEntity> guiasEntradas = guiaEntradaService.findAll();

        assertNotNull(guiasEntradas);
        assertEquals(1, guiasEntradas.size());
        verify(guiaEntradaRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateGuiaEntrada() {
        when(guiaEntradaRepository.findById(anyLong())).thenReturn(Optional.of(guiaEntrada));
        when(guiaEntradaRepository.save(any(GuiaEntradaEntity.class))).thenReturn(guiaEntrada);

        GuiaEntradaEntity updatedGuiaEntrada = guiaEntradaService.update(guiaEntrada);

        assertNotNull(updatedGuiaEntrada);
        assertEquals(guiaEntrada.getId(), updatedGuiaEntrada.getId());
        verify(guiaEntradaRepository, times(1)).save(guiaEntrada);
    }

    @Test
    public void testDeleteGuiaEntrada() {
        when(guiaEntradaRepository.findById(anyLong())).thenReturn(Optional.of(guiaEntrada));
        doNothing().when(guiaEntradaRepository).delete(any(GuiaEntradaEntity.class));

        String result = guiaEntradaService.delete(1L);

        assertEquals("GuiaEntrada removido com sucesso!", result);
        verify(guiaEntradaRepository, times(1)).delete(guiaEntrada);
    }
}
