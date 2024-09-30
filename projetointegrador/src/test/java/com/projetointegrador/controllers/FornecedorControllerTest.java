package com.projetointegrador.controllers;

import com.projetointegrador.entities.FornecedorEntity;
import com.projetointegrador.services.FornecedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FornecedorController.class)
class FornecedorControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private FornecedorService fornecedorService;

    @InjectMocks
    private FornecedorController fornecedorController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(fornecedorController).build();
    }

    @Test
    void testFindAll() throws Exception {
        FornecedorEntity fornecedor = new FornecedorEntity();
        fornecedor.setId(1L);
        List<FornecedorEntity> fornecedorList = new ArrayList<>();
        fornecedorList.add(fornecedor);

        when(fornecedorService.findAll()).thenReturn(fornecedorList);

        mockMvc.perform(get("/fornecedor/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testFindById() throws Exception {
        FornecedorEntity fornecedor = new FornecedorEntity();
        fornecedor.setId(1L);

        when(fornecedorService.findById(1L)).thenReturn(fornecedor);

        mockMvc.perform(get("/fornecedor/findById").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testUpdate() throws Exception {
        FornecedorEntity fornecedor = new FornecedorEntity();
        fornecedor.setId(1L);

        when(fornecedorService.update(any(FornecedorEntity.class))).thenReturn(fornecedor);

        mockMvc.perform(put("/fornecedor/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}