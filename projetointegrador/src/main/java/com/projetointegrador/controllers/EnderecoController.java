package com.projetointegrador.controllers;

import com.projetointegrador.entities.EnderecoEntity;
import com.projetointegrador.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
@CrossOrigin(origins = "http://localhost:4200/")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;


    @GetMapping("/find/{id}")
    public ResponseEntity<EnderecoEntity> findById(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<EnderecoEntity>> findAll() {
        return ResponseEntity.ok(enderecoService.findAll());
    }

    @PutMapping("/update")
    public ResponseEntity<EnderecoEntity> update(@RequestBody EnderecoEntity enderecoEntity) {
        return ResponseEntity.ok(enderecoService.update(enderecoEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.delete(id));
    }
}
