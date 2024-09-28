package com.projetointegrador.services;

import com.projetointegrador.entities.ClienteEntity;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GuiaSaidaServiceTest {

    @InjectMocks
    private GuiaSaidaService guiaSaidaService;

    @Mock
    private GuiaSaidaRepository guiaSaidaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    private GuiaSaidaEntity guiaSaidaEntity;
    private ProdutoEntity produtoEntity;
    private ClienteEntity clienteEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configura entidades para os testes
        produtoEntity = new ProdutoEntity();
        produtoEntity.setId(1L);
        produtoEntity.setQuantidade_atual(10);

        clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);

        guiaSaidaEntity = new GuiaSaidaEntity();
        guiaSaidaEntity.setId(1L);
        guiaSaidaEntity.setQuantidade(5);
        guiaSaidaEntity.setProduto(produtoEntity);
        guiaSaidaEntity.setCliente(clienteEntity);
    }

    @Test
    public void testSave() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoEntity));
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteEntity));
        when(guiaSaidaRepository.save(any(GuiaSaidaEntity.class))).thenReturn(guiaSaidaEntity);

        GuiaSaidaEntity saved = guiaSaidaService.save(guiaSaidaEntity, 1L, 1L, null);

        assertNotNull(saved);
        assertEquals(guiaSaidaEntity.getId(), saved.getId());
        verify(produtoRepository).save(produtoEntity);
        verify(guiaSaidaRepository).save(guiaSaidaEntity);
    }

    @Test
    public void testFindById() {
        when(guiaSaidaRepository.findById(1L)).thenReturn(Optional.of(guiaSaidaEntity));

        GuiaSaidaEntity found = guiaSaidaService.findById(1L);

        assertNotNull(found);
        assertEquals(guiaSaidaEntity.getId(), found.getId());
    }

    @Test
    public void testFindAll() {
        when(guiaSaidaRepository.findAll()).thenReturn(List.of(guiaSaidaEntity));

        List<GuiaSaidaEntity> all = guiaSaidaService.findAll();

        assertFalse(all.isEmpty());
        assertEquals(1, all.size());
    }

    @Test
    public void testUpdate() {
        when(guiaSaidaRepository.findById(1L)).thenReturn(Optional.of(guiaSaidaEntity));
        when(guiaSaidaRepository.save(any(GuiaSaidaEntity.class))).thenReturn(guiaSaidaEntity);

        guiaSaidaEntity.setQuantidade(7);
        GuiaSaidaEntity updated = guiaSaidaService.update(guiaSaidaEntity);

        assertNotNull(updated);
        assertEquals(7, updated.getQuantidade());
    }

    @Test
    public void testDelete() {
        when(guiaSaidaRepository.findById(1L)).thenReturn(Optional.of(guiaSaidaEntity));

        String response = guiaSaidaService.delete(1L);

        assertEquals("GuiaSaida removido com sucesso!", response);
        verify(guiaSaidaRepository).delete(guiaSaidaEntity);
    }

    @Test
    public void testSaveProdutoNotFound() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            guiaSaidaService.save(guiaSaidaEntity, 1L, 1L, null);
        });

        assertEquals("Produto não encontrado", exception.getMessage());
    }
}