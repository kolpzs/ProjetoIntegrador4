package com.projetointegrador.controllers;

import com.projetointegrador.entities.*;
import com.projetointegrador.services.FuncionarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin(origins = "http://localhost:4200/")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/findById")
    public ResponseEntity<FuncionarioEntity> findById(@RequestParam Long id) {
        return ResponseEntity.ok(funcionarioService.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<FuncionarioEntity>> findAll() {
        return ResponseEntity.ok(funcionarioService.findAll());
    }

    @PutMapping("/update")
    public ResponseEntity<FuncionarioEntity> update(@RequestBody FuncionarioEntity funcionarioEntity) {
        return ResponseEntity.ok(funcionarioService.update(funcionarioEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.delete(id));
    }

    @PostMapping("/saveCliente")
    public ResponseEntity<ClienteEntity> saveCliente(@RequestBody ClienteEntity cliente) {
        return ResponseEntity.ok(funcionarioService.saveCliente(cliente));
    }

    @PostMapping("/saveFornecedor")
    public ResponseEntity<FornecedorEntity> saveFornecedor(@RequestBody FornecedorEntity fornecedor) {
        return ResponseEntity.ok(funcionarioService.saveFornecedor(fornecedor));
    }

    @PostMapping("/saveProduto/{id}")
    public ResponseEntity<ProdutoEntity> saveProduto(@RequestBody ProdutoEntity produto, @PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.saveProduto(produto, id));
    }

    @PostMapping("/saveGuiaEntrada")
    public ResponseEntity<GuiaEntradaEntity> saveGuiaEntrada(@RequestBody GuiaEntradaEntity guiaEntrada, @RequestParam Long produto_id, @RequestParam Long fornecedor_id, @RequestParam Long funcionario_id) {
        return ResponseEntity.ok(funcionarioService.saveGuiaEntrada(guiaEntrada, produto_id, fornecedor_id, funcionario_id));
    }

    @PostMapping("/saveGuiaSaida")
    public ResponseEntity<GuiaSaidaEntity> saveGuiaSaida(@RequestBody GuiaSaidaEntity guiaSaida, @RequestParam Long produto_id, @RequestParam Long cliente_id, @RequestParam Long funcionario_id) {
        return ResponseEntity.ok(funcionarioService.saveGuiaSaida(guiaSaida, produto_id, cliente_id, funcionario_id));
    }

    @PutMapping("/associarEnderecoFornecedor")
    public ResponseEntity<EnderecoEntity> associarEnderecoFornecedor(@RequestParam Long fornecedor_id, @RequestParam Long endereco_id) {
        return ResponseEntity.ok(funcionarioService.associarEnderecoFornecedor(fornecedor_id, endereco_id));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestParam String cpf) {
        return ResponseEntity.ok(funcionarioService.login(cpf));
    }
}
