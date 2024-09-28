package com.projetointegrador.controllers;

import com.projetointegrador.entities.ClienteEntity;
import com.projetointegrador.services.ClienteService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void testFindAll() throws Exception {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(1L);
        List<ClienteEntity> clienteList = new ArrayList<>();
        clienteList.add(cliente);

        when(clienteService.findAll()).thenReturn(clienteList);

        mockMvc.perform(get("/cliente/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testFindById() throws Exception {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(1L);

        when(clienteService.findById(1L)).thenReturn(cliente);

        mockMvc.perform(get("/cliente/findById")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testUpdate() throws Exception {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(1L);

        when(clienteService.update(any(ClienteEntity.class))).thenReturn(cliente);

        mockMvc.perform(put("/cliente/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}