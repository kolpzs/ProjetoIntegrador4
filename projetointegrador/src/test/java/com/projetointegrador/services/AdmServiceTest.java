package com.projetointegrador.services;

import com.projetointegrador.entities.AdmEntity;
import com.projetointegrador.entities.EnderecoEntity;
import com.projetointegrador.entities.FuncionarioEntity;
import com.projetointegrador.repositories.AdmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdmServiceTest {

    @Mock
    private AdmRepository admRepository;

    @Mock
    private FuncionarioService funcionarioService;

    @Mock
    private EnderecoService enderecoService;

    @InjectMocks
    private AdmService admService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateWithAllFields() {
        AdmEntity admEntity = new AdmEntity();
        admEntity.setId(1L);
        admEntity.setNome("Nome Antigo");
        admEntity.setSenha("Senha Antiga");

        AdmEntity updatedAdm = new AdmEntity();
        updatedAdm.setId(1L);
        updatedAdm.setNome("Nome Novo");
        updatedAdm.setSenha("Senha Nova");

        when(admRepository.findById(1L)).thenReturn(Optional.of(admEntity));
        when(admRepository.save(any(AdmEntity.class))).thenReturn(updatedAdm);

        AdmEntity result = admService.update(updatedAdm);
        assertNotNull(result);
        assertEquals("Nome Novo", result.getNome());
        assertEquals("Senha Nova", result.getSenha());

        verify(admRepository, times(1)).save(admEntity);
    }

    @Test
    void testUpdateWithOnlyNome() {
        AdmEntity admEntity = new AdmEntity();
        admEntity.setId(1L);
        admEntity.setNome("Nome Antigo");
        admEntity.setSenha("Senha Antiga");

        AdmEntity updatedAdm = new AdmEntity();
        updatedAdm.setId(1L);
        updatedAdm.setNome("Nome Novo");

        when(admRepository.findById(1L)).thenReturn(Optional.of(admEntity));
        when(admRepository.save(any(AdmEntity.class))).thenReturn(admEntity);

        AdmEntity result = admService.update(updatedAdm);
        assertNotNull(result);
        assertEquals("Nome Novo", result.getNome());
        assertEquals("Senha Antiga", result.getSenha()); // Senha não deve mudar

        verify(admRepository, times(1)).save(admEntity);
    }

    @Test
    void testUpdateWithOnlySenha() {
        AdmEntity admEntity = new AdmEntity();
        admEntity.setId(1L);
        admEntity.setNome("Nome Antigo");
        admEntity.setSenha("Senha Antiga");

        AdmEntity updatedAdm = new AdmEntity();
        updatedAdm.setId(1L);
        updatedAdm.setSenha("Senha Nova");

        when(admRepository.findById(1L)).thenReturn(Optional.of(admEntity));
        when(admRepository.save(any(AdmEntity.class))).thenReturn(admEntity);

        AdmEntity result = admService.update(updatedAdm);
        assertNotNull(result);
        assertEquals("Nome Antigo", result.getNome()); // Nome não deve mudar
        assertEquals("Senha Nova", result.getSenha());

        verify(admRepository, times(1)).save(admEntity);
    }

    @Test
    void testUpdateNonExistentAdm() {
        AdmEntity updatedAdm = new AdmEntity();
        updatedAdm.setId(1L);
        updatedAdm.setNome("Nome Novo");

        when(admRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            admService.update(updatedAdm);
        });

        verify(admRepository, times(0)).save(any(AdmEntity.class)); // O método save não deve ser chamado
    }

    @Test
    void testUpdateWithNullId() {
        AdmEntity updatedAdm = new AdmEntity();
        updatedAdm.setId(null);
        updatedAdm.setNome("Nome Novo");

        assertThrows(NullPointerException.class, () -> {
            admService.update(updatedAdm);
        });

        verify(admRepository, times(0)).save(any(AdmEntity.class)); // O método save não deve ser chamado
    }

    @Test
    void testUpdateWithNullFields() {
        AdmEntity admEntity = new AdmEntity();
        admEntity.setId(1L);
        admEntity.setNome("Nome Antigo");
        admEntity.setSenha("Senha Antiga");

        AdmEntity updatedAdm = new AdmEntity();
        updatedAdm.setId(1L); // Mesmo ID, mas com nome e senha nulos

        when(admRepository.findById(1L)).thenReturn(Optional.of(admEntity));
        when(admRepository.save(any(AdmEntity.class))).thenReturn(admEntity);

        AdmEntity result = admService.update(updatedAdm);
        assertNotNull(result);
        assertEquals("Nome Antigo", result.getNome()); // Nome deve permanecer o mesmo
        assertEquals("Senha Antiga", result.getSenha()); // Senha deve permanecer a mesma

        verify(admRepository, times(1)).save(admEntity);
    }
    @Test
    void testSaveAdm() {
        AdmEntity admEntity = new AdmEntity();
        admEntity.setId(1L);
        admEntity.setNome("Admin");

        when(admRepository.save(admEntity)).thenReturn(admEntity);

        AdmEntity result = admService.save(admEntity);
        assertNotNull(result);
        assertEquals("Admin", result.getNome());

        verify(admRepository, times(1)).save(admEntity);
    }

    @Test
    void testFindById() {
        AdmEntity admEntity = new AdmEntity();
        admEntity.setId(1L);

        when(admRepository.findById(1L)).thenReturn(Optional.of(admEntity));

        AdmEntity result = admService.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(admRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateAdm() {
        AdmEntity admEntity = new AdmEntity();
        admEntity.setId(1L);
        admEntity.setNome("Old Name");
        admEntity.setSenha("1234");

        when(admRepository.findById(1L)).thenReturn(Optional.of(admEntity));
        when(admRepository.save(any(AdmEntity.class))).thenReturn(admEntity);

        AdmEntity updatedAdm = new AdmEntity();
        updatedAdm.setId(1L);
        updatedAdm.setNome("New Name");

        AdmEntity result = admService.update(updatedAdm);
        assertEquals("New Name", result.getNome());

        verify(admRepository, times(1)).save(any(AdmEntity.class));
    }

    @Test
    void testDeleteAdm() {
        AdmEntity admEntity = new AdmEntity();
        admEntity.setId(1L);

        when(admRepository.findById(1L)).thenReturn(Optional.of(admEntity));

        String result = admService.delete(1L);
        assertEquals("Usuario removido com sucesso!", result);

        verify(admRepository, times(1)).delete(admEntity);
    }

    @Test
    void testValidaLogin() {
        AdmEntity admEntity = new AdmEntity();
        admEntity.setId(1L);
        admEntity.setSenha("senha123");

        when(admRepository.findById(1L)).thenReturn(Optional.of(admEntity));

        boolean isValid = admService.validaLogin(1L, "senha123");
        assertTrue(isValid);

        verify(admRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveFuncionario() {
        AdmEntity admEntity = new AdmEntity();
        admEntity.setId(1L);
        admEntity.setSenha("senha123");

        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setId(1L);
        funcionarioEntity.setNome("Funcionario");

        when(admRepository.findById(1L)).thenReturn(Optional.of(admEntity));
        when(funcionarioService.save(funcionarioEntity)).thenReturn(funcionarioEntity);

        FuncionarioEntity result = admService.saveFuncionario(1L, "senha123", funcionarioEntity);
        assertNotNull(result);
        assertEquals("Funcionario", result.getNome());

        verify(funcionarioService, times(1)).save(funcionarioEntity);
    }

    @Test
    void testSaveEndereco() {
        AdmEntity admEntity = new AdmEntity();
        admEntity.setId(1L);
        admEntity.setSenha("senha123");

        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(1L);
        enderecoEntity.setRua("Rua A");

        when(admRepository.findById(1L)).thenReturn(Optional.of(admEntity));
        when(enderecoService.save(enderecoEntity)).thenReturn(enderecoEntity);

        EnderecoEntity result = admService.saveEndereco(1L, "senha123", enderecoEntity);
        assertNotNull(result);
        assertEquals("Rua A", result.getRua());

        verify(enderecoService, times(1)).save(enderecoEntity);
    }
}
