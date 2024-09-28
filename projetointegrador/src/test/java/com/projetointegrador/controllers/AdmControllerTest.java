package com.projetointegrador.controllers;

import com.projetointegrador.entities.AdmEntity;
import com.projetointegrador.entities.EnderecoEntity;
import com.projetointegrador.entities.FuncionarioEntity;
import com.projetointegrador.services.AdmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdmController.class)
class AdmControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private AdmService admService;

    @InjectMocks
    private AdmController admController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(admController).build();
    }

    @Test
    void testSave() throws Exception {
        AdmEntity adm = new AdmEntity();
        adm.setId(1L);

        when(admService.save(any(AdmEntity.class))).thenReturn(adm);

        mockMvc.perform(post("/adm/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testFindById() throws Exception {
        AdmEntity adm = new AdmEntity();
        adm.setId(1L);

        when(admService.findById(1L)).thenReturn(adm);

        mockMvc.perform(get("/adm/find/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testFindAll() throws Exception {
        AdmEntity adm = new AdmEntity();
        adm.setId(1L);
        List<AdmEntity> admList = new ArrayList<>();
        admList.add(adm);

        when(admService.findAll()).thenReturn(admList);

        mockMvc.perform(get("/adm/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testUpdate() throws Exception {
        AdmEntity adm = new AdmEntity();
        adm.setId(1L);

        when(admService.update(any(AdmEntity.class))).thenReturn(adm);

        mockMvc.perform(put("/adm/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testDelete() throws Exception {
        when(admService.delete(1L)).thenReturn("Deleted");

        mockMvc.perform(delete("/adm/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }

    @Test
    void testSaveFuncionario() throws Exception {
        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setId(1L);

        when(admService.saveFuncionario(any(Long.class), any(String.class), any(FuncionarioEntity.class)))
                .thenReturn(funcionario);

        mockMvc.perform(post("/adm/saveFuncionario/1/senha123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testSaveEndereco() throws Exception {
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setId(1L);

        when(admService.saveEndereco(any(Long.class), any(String.class), any(EnderecoEntity.class)))
                .thenReturn(endereco);

        mockMvc.perform(post("/adm/saveEndereco/1/senha123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testAssociarEnderecoFuncionario() throws Exception {
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setId(1L);

        when(admService.associarEnderecoFuncionario(any(Long.class), any(Long.class)))
                .thenReturn(endereco);

        mockMvc.perform(post("/adm/associarEnderecoFuncionario")
                        .param("funcionario_id", "1")
                        .param("endereco_id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}