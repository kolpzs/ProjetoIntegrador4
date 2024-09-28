package com.projetointegrador.controllers;

import com.projetointegrador.entities.EnderecoEntity;
import com.projetointegrador.services.EnderecoService;
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

@WebMvcTest(EnderecoController.class)
class EnderecoControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private EnderecoService enderecoService;

    @InjectMocks
    private EnderecoController enderecoController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(enderecoController).build();
    }

    @Test
    void testFindById() throws Exception {
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setId(1L);

        when(enderecoService.findById(1L)).thenReturn(endereco);

        mockMvc.perform(get("/endereco/find/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testFindAll() throws Exception {
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setId(1L);
        List<EnderecoEntity> enderecoList = new ArrayList<>();
        enderecoList.add(endereco);

        when(enderecoService.findAll()).thenReturn(enderecoList);

        mockMvc.perform(get("/endereco/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testUpdate() throws Exception {
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setId(1L);

        when(enderecoService.update(any(EnderecoEntity.class))).thenReturn(endereco);

        mockMvc.perform(put("/endereco/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testDelete() throws Exception {
        when(enderecoService.delete(1L)).thenReturn("Deleted");

        mockMvc.perform(delete("/endereco/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }
}