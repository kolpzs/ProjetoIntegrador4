package com.projetointegrador.controllers;

import com.projetointegrador.entities.ProdutoEntity;
import com.projetointegrador.entities.EnderecoEntity;
import com.projetointegrador.entities.FuncionarioEntity;
import com.projetointegrador.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/findById")
    public ResponseEntity<ProdutoEntity> findById(@RequestParam Long id) {
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProdutoEntity>> findAll() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @GetMapping("/findByNome")
    public ResponseEntity<List<ProdutoEntity>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(produtoService.findByNome(nome));
    }

    @GetMapping("/findAllByFornecedorId")
    public ResponseEntity<List<ProdutoEntity>> findByFornecedorId(@RequestParam Long fornecedorId) {
        return ResponseEntity.ok(produtoService.findAllByFornecedorId(fornecedorId));
    }

    @GetMapping("/findByMarca")
    public ResponseEntity<List<ProdutoEntity>> findByMarca(@RequestParam String marca) {
        return ResponseEntity.ok(produtoService.findByMarca(marca));
    }

    @GetMapping("/findByEstoque")
    public ResponseEntity<List<ProdutoEntity>> findByEstoque() {
        return ResponseEntity.ok(produtoService.findByEstoque());
    }

    @GetMapping("/relatorioEstoque")
    public ResponseEntity<List<String>> relatorioEstoque() {
        return ResponseEntity.ok(produtoService.relatorioEstoque());
    }
}
