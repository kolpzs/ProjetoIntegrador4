package com.projetointegrador.controllers;

import com.projetointegrador.entities.GuiaSaidaEntity;
import com.projetointegrador.services.GuiaSaidaService;
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
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GuiaSaidaController.class)
class GuiaSaidaControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private GuiaSaidaService guiaSaidaService;

    @InjectMocks
    private GuiaSaidaController guiaSaidaController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(guiaSaidaController).build();
    }

    @Test
    void testFindAll() throws Exception {
        GuiaSaidaEntity guiaSaida = new GuiaSaidaEntity();
        guiaSaida.setId(1L);
        guiaSaida.setData(new Date());
        guiaSaida.setValor(100.0f);
        guiaSaida.setQuantidade(5);

        List<GuiaSaidaEntity> guiasSaidas = new ArrayList<>();
        guiasSaidas.add(guiaSaida);

        when(guiaSaidaService.findAll()).thenReturn(guiasSaidas);

        mockMvc.perform(get("/guia-saida/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].valor").value(100.0f))
                .andExpect(jsonPath("$[0].quantidade").value(5));
    }

    @Test
    void testFindById() throws Exception {
        GuiaSaidaEntity guiaSaida = new GuiaSaidaEntity();
        guiaSaida.setId(1L);
        guiaSaida.setData(new Date());
        guiaSaida.setValor(100.0f);
        guiaSaida.setQuantidade(5);

        when(guiaSaidaService.findById(anyLong())).thenReturn(guiaSaida);

        mockMvc.perform(get("/guia-saida/findById")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.valor").value(100.0f))
                .andExpect(jsonPath("$.quantidade").value(5));
    }

    @Test
    void testSaveGuiaSaida() throws Exception {
        GuiaSaidaEntity guiaSaida = new GuiaSaidaEntity();
        guiaSaida.setId(1L);
        guiaSaida.setData(new Date());
        guiaSaida.setValor(100.0f);
        guiaSaida.setQuantidade(5);

        when(guiaSaidaService.save(any(GuiaSaidaEntity.class), anyLong(), anyLong(), anyLong()))
                .thenReturn(guiaSaida);

        mockMvc.perform(post("/funcionario/saveGuiaEntrada")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"valor\": 100.0, \"quantidade\": 5, \"data\": \"2024-09-28\"}")
                        .param("produto_id", "1")
                        .param("cliente_id", "2")
                        .param("funcionario_id", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.valor").value(100.0f))
                .andExpect(jsonPath("$.quantidade").value(5));
    }
}