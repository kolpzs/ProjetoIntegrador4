package com.projetointegrador.services;

import com.projetointegrador.entities.FornecedorEntity;
import com.projetointegrador.entities.ProdutoEntity;
import com.projetointegrador.repositories.FornecedorRepository;
import com.projetointegrador.repositories.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProdutoWithFornecedor() {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome("Produto Teste");

        FornecedorEntity fornecedor = new FornecedorEntity();
        fornecedor.setId(1L);

        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedor));
        when(produtoRepository.save(produto)).thenReturn(produto);

        ProdutoEntity result = produtoService.save(produto, 1L);
        assertNotNull(result);
        assertEquals("Produto Teste", result.getNome());
        assertEquals(1, result.getFornecedores().size());

        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testFindProdutoById() {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(1L);
        produto.setNome("Produto Teste");

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        ProdutoEntity result = produtoService.findById(1L);
        assertNotNull(result);
        assertEquals("Produto Teste", result.getNome());

        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAllProdutos() {
        ProdutoEntity produto1 = new ProdutoEntity();
        ProdutoEntity produto2 = new ProdutoEntity();

        when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto1, produto2));

        List<ProdutoEntity> result = produtoService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void testFindByNome() {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome("Produto Teste");

        when(produtoRepository.findByNome("Produto Teste")).thenReturn(Arrays.asList(produto));

        List<ProdutoEntity> result = produtoService.findByNome("Produto Teste");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Produto Teste", result.get(0).getNome());

        verify(produtoRepository, times(1)).findByNome("Produto Teste");
    }

    @Test
    void testFindAllByFornecedorId() {
        ProdutoEntity produto = new ProdutoEntity();
        FornecedorEntity fornecedor = new FornecedorEntity();
        fornecedor.setId(1L);
        produto.setFornecedores(Arrays.asList(fornecedor));

        when(produtoRepository.findAllByFornecedorId(1L)).thenReturn(Arrays.asList(produto));

        List<ProdutoEntity> result = produtoService.findAllByFornecedorId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getFornecedores().get(0).getId());

        verify(produtoRepository, times(1)).findAllByFornecedorId(1L);
    }

    @Test
    void testFindByMarca() {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setMarca("Marca Teste");

        when(produtoRepository.findByMarca("Marca Teste")).thenReturn(Arrays.asList(produto));

        List<ProdutoEntity> result = produtoService.findByMarca("Marca Teste");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Marca Teste", result.get(0).getMarca());

        verify(produtoRepository, times(1)).findByMarca("Marca Teste");
    }

    @Test
    void testUpdateProdutoWithNewFornecedor() {
        // Produto existente
        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(1L);
        produto.setNome("Produto Antigo");

        // Fornecedor existente no sistema
        FornecedorEntity fornecedorExistente = new FornecedorEntity();
        fornecedorExistente.setId(1L);
        produto.setFornecedores(Arrays.asList(fornecedorExistente));

        // Produto atualizado com novo fornecedor
        ProdutoEntity updatedProduto = new ProdutoEntity();
        updatedProduto.setId(1L);
        updatedProduto.setNome("Produto Novo");
        updatedProduto.setMarca("Marca Nova");

        // Novo fornecedor criado para ser adicionado
        FornecedorEntity novoFornecedor = new FornecedorEntity();
        novoFornecedor.setId(2L); // Novo ID do fornecedor
        novoFornecedor.setNome_social("Fornecedor Novo");

        updatedProduto.setFornecedores(Arrays.asList(novoFornecedor)); // Associar novo fornecedor

        // Simular o comportamento dos repositórios
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedorExistente));
        when(fornecedorRepository.findById(2L)).thenReturn(Optional.of(novoFornecedor)); // Simular novo fornecedor encontrado
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(produto);

        // Executar a atualização
        ProdutoEntity result = produtoService.update(updatedProduto);

        // Validações do resultado
        assertNotNull(result);
        assertEquals("Produto Novo", result.getNome());
        assertEquals("Marca Nova", result.getMarca());
        assertEquals(1, result.getFornecedores().size());
        assertEquals("Fornecedor Novo", result.getFornecedores().get(0).getNome_social()); // Verifica se o fornecedor foi atualizado

        // Verificar se o produto foi salvo com as mudanças
        verify(produtoRepository, times(1)).save(produto);
    }


    @Test
    void testDeleteProduto() {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(1L);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        doNothing().when(produtoRepository).delete(produto);

        String result = produtoService.delete(1L);
        assertEquals("Produto removido com sucesso!", result);

        verify(produtoRepository, times(1)).delete(produto);
    }

    @Test
    void testSaveProdutoWithNonExistentFornecedor() {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome("Produto Teste");

        when(fornecedorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            produtoService.save(produto, 1L);
        });

        verify(produtoRepository, times(0)).save(any(ProdutoEntity.class)); // O método save não deve ser chamado
    }
}
