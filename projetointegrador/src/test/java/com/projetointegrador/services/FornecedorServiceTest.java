package com.projetointegrador.services;

import com.projetointegrador.entities.FornecedorEntity;
import com.projetointegrador.repositories.FornecedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FornecedorServiceTest {

    @Mock
    private FornecedorRepository fornecedorRepository;

    @InjectMocks
    private FornecedorService fornecedorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFornecedor() {
        FornecedorEntity fornecedorEntity = new FornecedorEntity();
        fornecedorEntity.setId(1L);
        fornecedorEntity.setNome_social("Fornecedor Teste");

        when(fornecedorRepository.save(fornecedorEntity)).thenReturn(fornecedorEntity);

        FornecedorEntity result = fornecedorService.save(fornecedorEntity);
        assertNotNull(result);
        assertEquals("Fornecedor Teste", result.getNome_social());

        verify(fornecedorRepository, times(1)).save(fornecedorEntity);
    }

    @Test
    void testFindById() {
        FornecedorEntity fornecedorEntity = new FornecedorEntity();
        fornecedorEntity.setId(1L);

        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedorEntity));

        FornecedorEntity result = fornecedorService.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(fornecedorRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAllFornecedores() {
        FornecedorEntity fornecedorEntity1 = new FornecedorEntity();
        fornecedorEntity1.setId(1L);

        FornecedorEntity fornecedorEntity2 = new FornecedorEntity();
        fornecedorEntity2.setId(2L);

        when(fornecedorRepository.findAll()).thenReturn(List.of(fornecedorEntity1, fornecedorEntity2));

        List<FornecedorEntity> result = fornecedorService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(fornecedorRepository, times(1)).findAll();
    }

    @Test
    void testUpdateFornecedor() {
        FornecedorEntity fornecedorEntity = new FornecedorEntity();
        fornecedorEntity.setId(1L);
        fornecedorEntity.setNome_social("Nome Antigo");

        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedorEntity));
        when(fornecedorRepository.save(any(FornecedorEntity.class))).thenReturn(fornecedorEntity);

        FornecedorEntity updatedFornecedor = new FornecedorEntity();
        updatedFornecedor.setId(1L);
        updatedFornecedor.setNome_social("Nome Novo");

        FornecedorEntity result = fornecedorService.update(updatedFornecedor);
        assertEquals("Nome Novo", result.getNome_social());

        verify(fornecedorRepository, times(1)).save(any(FornecedorEntity.class));
    }

    @Test
    void testDeleteFornecedor() {
        FornecedorEntity fornecedorEntity = new FornecedorEntity();
        fornecedorEntity.setId(1L);

        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedorEntity));

        String result = fornecedorService.delete(1L);
        assertEquals("Fornecedor removido com sucesso!", result);

        verify(fornecedorRepository, times(1)).delete(fornecedorEntity);
    }
}
