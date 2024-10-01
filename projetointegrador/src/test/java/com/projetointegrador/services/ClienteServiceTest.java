package com.projetointegrador.services;

import com.projetointegrador.entities.ClienteEntity;
import com.projetointegrador.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCliente() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);
        clienteEntity.setNome("Cliente Teste");

        when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);

        ClienteEntity result = clienteService.save(clienteEntity);
        assertNotNull(result);
        assertEquals("Cliente Teste", result.getNome());

        verify(clienteRepository, times(1)).save(clienteEntity);
    }

    @Test
    void testFindById() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteEntity));

        ClienteEntity result = clienteService.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAllClientes() {
        ClienteEntity clienteEntity1 = new ClienteEntity();
        clienteEntity1.setId(1L);

        ClienteEntity clienteEntity2 = new ClienteEntity();
        clienteEntity2.setId(2L);

        when(clienteRepository.findAll()).thenReturn(List.of(clienteEntity1, clienteEntity2));

        List<ClienteEntity> result = clienteService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testUpdateCliente() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);
        clienteEntity.setNome("Nome Antigo");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteEntity));
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(clienteEntity);

        ClienteEntity updatedCliente = new ClienteEntity();
        updatedCliente.setId(1L);
        updatedCliente.setNome("Nome Novo");

        ClienteEntity result = clienteService.update(updatedCliente);
        assertEquals("Nome Novo", result.getNome());

        verify(clienteRepository, times(1)).save(any(ClienteEntity.class));
    }

    @Test
    void testDeleteCliente() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteEntity));

        String result = clienteService.delete(1L);
        assertEquals("Cliente removido com sucesso!", result);

        verify(clienteRepository, times(1)).delete(clienteEntity);
    }
}
