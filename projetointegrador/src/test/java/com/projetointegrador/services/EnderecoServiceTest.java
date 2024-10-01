package com.projetointegrador.services;

import com.projetointegrador.entities.EnderecoEntity;
import com.projetointegrador.entities.FornecedorEntity;
import com.projetointegrador.entities.FuncionarioEntity;
import com.projetointegrador.repositories.EnderecoRepository;
import com.projetointegrador.repositories.FornecedorRepository;
import com.projetointegrador.repositories.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveEndereco() {
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(1L);
        enderecoEntity.setRua("Rua A");

        when(enderecoRepository.save(enderecoEntity)).thenReturn(enderecoEntity);

        EnderecoEntity result = enderecoService.save(enderecoEntity);
        assertNotNull(result);
        assertEquals("Rua A", result.getRua());

        verify(enderecoRepository, times(1)).save(enderecoEntity);
    }

    @Test
    void testFindById() {
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(1L);

        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(enderecoEntity));

        EnderecoEntity result = enderecoService.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(enderecoRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAllEnderecos() {
        EnderecoEntity enderecoEntity1 = new EnderecoEntity();
        enderecoEntity1.setId(1L);

        EnderecoEntity enderecoEntity2 = new EnderecoEntity();
        enderecoEntity2.setId(2L);

        when(enderecoRepository.findAll()).thenReturn(List.of(enderecoEntity1, enderecoEntity2));

        List<EnderecoEntity> result = enderecoService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(enderecoRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEndereco() {
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(1L);
        enderecoEntity.setRua("Rua Antiga");

        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(enderecoEntity));
        when(enderecoRepository.save(any(EnderecoEntity.class))).thenReturn(enderecoEntity);

        EnderecoEntity updatedEndereco = new EnderecoEntity();
        updatedEndereco.setId(1L);
        updatedEndereco.setRua("Rua Nova");

        EnderecoEntity result = enderecoService.update(updatedEndereco);
        assertEquals("Rua Nova", result.getRua());

        verify(enderecoRepository, times(1)).save(any(EnderecoEntity.class));
    }

    @Test
    void testDeleteEndereco() {
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(1L);

        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(enderecoEntity));

        String result = enderecoService.delete(1L);
        assertEquals("Endereco removido com sucesso!", result);

        verify(enderecoRepository, times(1)).delete(enderecoEntity);
    }

    @Test
    void testUpdateEnderecoFornecedor() {
        FornecedorEntity fornecedorEntity = new FornecedorEntity();
        fornecedorEntity.setId(1L);

        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(1L);

        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedorEntity));
        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(enderecoEntity));
        when(enderecoRepository.save(enderecoEntity)).thenReturn(enderecoEntity);

        EnderecoEntity result = enderecoService.updateEnderecoFornecedor(1L, 1L);
        assertNotNull(result);
        assertEquals(fornecedorEntity, result.getFornecedor());

        verify(enderecoRepository, times(1)).save(enderecoEntity);
    }

    @Test
    void testUpdateEnderecoFuncionario() {
        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setId(1L);

        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(1L);

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionarioEntity));
        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(enderecoEntity));
        when(enderecoRepository.save(enderecoEntity)).thenReturn(enderecoEntity);

        EnderecoEntity result = enderecoService.updateEnderecoFuncionario(1L, 1L);
        assertNotNull(result);
        assertEquals(funcionarioEntity, result.getFuncionario());

        verify(enderecoRepository, times(1)).save(enderecoEntity);
    }
}
