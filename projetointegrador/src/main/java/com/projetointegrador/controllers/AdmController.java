package com.projetointegrador.controllers;

import com.projetointegrador.entities.AdmEntity;
import com.projetointegrador.entities.EnderecoEntity;
import com.projetointegrador.entities.FuncionarioEntity;
import com.projetointegrador.services.AdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adm")
public class AdmController {

    @Autowired
    private AdmService admService;

    @PostMapping("/save")
    public ResponseEntity<AdmEntity> save(@RequestBody AdmEntity admEntity) {
        return ResponseEntity.ok(admService.save(admEntity));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AdmEntity> findById(@PathVariable Long id) {
        return ResponseEntity.ok(admService.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AdmEntity>> findAll() {
        return ResponseEntity.ok(admService.findAll());
    }

    @PutMapping("/update")
    public ResponseEntity<AdmEntity> update(@RequestBody AdmEntity admEntity) {
        return ResponseEntity.ok(admService.update(admEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(admService.delete(id));
    }

    @PostMapping("/saveFuncionario/{id}/{senha}")
    public ResponseEntity<FuncionarioEntity> saveFuncionario(@PathVariable Long id, @PathVariable String senha, @RequestBody FuncionarioEntity funcionario) {
        return ResponseEntity.ok(admService.saveFuncionario(id, senha, funcionario));
    }

    @PostMapping("/saveEndereco/{id}/{senha}")
    public ResponseEntity<EnderecoEntity> saveEndereco(@PathVariable Long id, @PathVariable String senha, @RequestBody EnderecoEntity endereco) {
        return ResponseEntity.ok(admService.saveEndereco(id, senha, endereco));
    }

    @PostMapping("/associarEnderecoFuncionario")
    public ResponseEntity<EnderecoEntity> associarEnderecoFuncionario(@RequestParam Long funcionario_id, @RequestParam Long endereco_id) {
        return ResponseEntity.ok(admService.associarEnderecoFuncionario(funcionario_id, endereco_id));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody AdmEntity admEntity) {
        return ResponseEntity.ok(admService.login(admEntity.getNome(), admEntity.getSenha()));
    }
}
