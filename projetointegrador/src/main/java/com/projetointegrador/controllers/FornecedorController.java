package com.projetointegrador.controllers;

import com.projetointegrador.entities.EnderecoEntity;
import com.projetointegrador.entities.FornecedorEntity;
import com.projetointegrador.entities.GuiaEntradaEntity;
import com.projetointegrador.entities.ProdutoEntity;
import com.projetointegrador.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
@CrossOrigin(origins = "http://localhost:4200/")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/findAll")
    public ResponseEntity<List<FornecedorEntity>> findAll() {
        return ResponseEntity.ok(fornecedorService.findAll());
    }

    @GetMapping("/findById")
    public ResponseEntity<FornecedorEntity> findById(@RequestParam Long id) {
        return ResponseEntity.ok(fornecedorService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<FornecedorEntity> update(@RequestBody FornecedorEntity clienteEntity) {
        return ResponseEntity.ok(fornecedorService.update(clienteEntity));
    }
}