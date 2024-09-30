package com.projetointegrador.controllers;

import com.projetointegrador.entities.ProdutoEntity;
import com.projetointegrador.services.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Test
    void testFindById() throws Exception {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(1L);
        produto.setNome("Produto Teste");

        Mockito.when(produtoService.findById(anyLong())).thenReturn(produto);

        mockMvc.perform(get("/produto/findById")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("Produto Teste")));
    }

    @Test
    void testFindAll() throws Exception {
        ProdutoEntity produto1 = new ProdutoEntity();
        produto1.setId(1L);
        produto1.setNome("Produto 1");

        ProdutoEntity produto2 = new ProdutoEntity();
        produto2.setId(2L);
        produto2.setNome("Produto 2");

        List<ProdutoEntity> produtos = Arrays.asList(produto1, produto2);

        Mockito.when(produtoService.findAll()).thenReturn(produtos);

        mockMvc.perform(get("/produto/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nome", is("Produto 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].nome", is("Produto 2")));
    }

    @Test
    void testFindByNome() throws Exception {
        ProdutoEntity produto1 = new ProdutoEntity();
        produto1.setId(1L);
        produto1.setNome("Produto Teste");

        List<ProdutoEntity> produtos = List.of(produto1);

        Mockito.when(produtoService.findByNome(anyString())).thenReturn(produtos);

        mockMvc.perform(get("/produto/findByNome")
                        .param("nome", "Produto Teste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", is("Produto Teste")));
    }

    @Test
    void testFindByFornecedorId() throws Exception {
        ProdutoEntity produto1 = new ProdutoEntity();
        produto1.setId(1L);
        produto1.setNome("Produto Fornecedor Teste");

        List<ProdutoEntity> produtos = List.of(produto1);

        Mockito.when(produtoService.findAllByFornecedorId(anyLong())).thenReturn(produtos);

        mockMvc.perform(get("/produto/findAllByFornecedorId")
                        .param("fornecedorId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nome", is("Produto Fornecedor Teste")));
    }

    @Test
    void testFindByMarca() throws Exception {
        ProdutoEntity produto1 = new ProdutoEntity();
        produto1.setId(1L);
        produto1.setNome("Produto Marca Teste");

        List<ProdutoEntity> produtos = List.of(produto1);

        Mockito.when(produtoService.findByMarca(anyString())).thenReturn(produtos);

        mockMvc.perform(get("/produto/findByMarca")
                        .param("marca", "Marca Teste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", is("Produto Marca Teste")));
    }
}