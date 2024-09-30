package com.projetointegrador.controllers;

import com.projetointegrador.entities.*;
import com.projetointegrador.services.FuncionarioService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FuncionarioController.class)
class FuncionarioControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService funcionarioService;

    @InjectMocks
    private FuncionarioController funcionarioController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(funcionarioController).build();
    }

    @Test
    void testFindAll() throws Exception {
        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setId(1L);
        List<FuncionarioEntity> funcionarioList = new ArrayList<>();
        funcionarioList.add(funcionario);

        when(funcionarioService.findAll()).thenReturn(funcionarioList);

        mockMvc.perform(get("/funcionario/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testUpdate() throws Exception {
        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setId(1L);

        when(funcionarioService.update(any(FuncionarioEntity.class))).thenReturn(funcionario);

        mockMvc.perform(put("/funcionario/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testDelete() throws Exception {
        when(funcionarioService.delete(1L)).thenReturn("Deleted");

        mockMvc.perform(delete("/funcionario/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }

    @Test
    void testSaveCliente() throws Exception {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(1L);

        when(funcionarioService.saveCliente(any(ClienteEntity.class))).thenReturn(cliente);

        mockMvc.perform(post("/funcionario/saveCliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testSaveFornecedor() throws Exception {
        FornecedorEntity fornecedor = new FornecedorEntity();
        fornecedor.setId(1L);

        when(funcionarioService.saveFornecedor(any(FornecedorEntity.class))).thenReturn(fornecedor);

        mockMvc.perform(post("/funcionario/saveFornecedor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testSaveProduto() throws Exception {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(1L);

        when(funcionarioService.saveProduto(any(ProdutoEntity.class), any(Long.class))).thenReturn(produto);

        mockMvc.perform(post("/funcionario/saveProduto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testSaveGuiaEntrada() throws Exception {
        GuiaEntradaEntity guiaEntrada = new GuiaEntradaEntity();
        guiaEntrada.setId(1L);

        when(funcionarioService.saveGuiaEntrada(any(GuiaEntradaEntity.class), any(Long.class), any(Long.class), any(Long.class))).thenReturn(guiaEntrada);

        mockMvc.perform(post("/funcionario/saveGuiaEntrada")
                        .param("produto_id", "1")
                        .param("fornecedor_id", "2")
                        .param("funcionario_id", "3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testSaveGuiaSaida() throws Exception {
        GuiaSaidaEntity guiaSaida = new GuiaSaidaEntity();
        guiaSaida.setId(1L);

        when(funcionarioService.saveGuiaSaida(any(GuiaSaidaEntity.class), any(Long.class), any(Long.class), any(Long.class))).thenReturn(guiaSaida);

        mockMvc.perform(post("/funcionario/saveGuiaSaida")
                        .param("produto_id", "1")
                        .param("cliente_id", "2")
                        .param("funcionario_id", "3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testAssociarEnderecoFornecedor() throws Exception {
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setId(1L);

        when(funcionarioService.associarEnderecoFornecedor(any(Long.class), any(Long.class))).thenReturn(endereco);

        mockMvc.perform(put("/funcionario/associarEnderecoFornecedor")
                        .param("fornecedor_id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}