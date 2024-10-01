package com.projetointegrador.controllers;

import com.projetointegrador.entities.GuiaEntradaEntity;
import com.projetointegrador.services.GuiaEntradaServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GuiaEntradaController.class)
class GuiaEntradaControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private GuiaEntradaServiceTest guiaEntradaService;

    @InjectMocks
    private GuiaEntradaController guiaEntradaController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(guiaEntradaController).build();
    }

    @Test
    void testFindAll() throws Exception {
        GuiaEntradaEntity guiaEntrada = new GuiaEntradaEntity();
        guiaEntrada.setId(1L);
        List<GuiaEntradaEntity> guiasEntradaList = new ArrayList<>();
        guiasEntradaList.add(guiaEntrada);

        when(guiaEntradaService.findAll()).thenReturn(guiasEntradaList);

        mockMvc.perform(get("/guia-entrada/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testFindById() throws Exception {
        GuiaEntradaEntity guiaEntrada = new GuiaEntradaEntity();
        guiaEntrada.setId(1L);

        when(guiaEntradaService.findById(anyLong())).thenReturn(guiaEntrada);

        mockMvc.perform(get("/guia-entrada/findById")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}