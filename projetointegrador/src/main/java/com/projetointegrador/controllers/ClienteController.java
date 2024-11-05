package com.projetointegrador.controllers;

import com.projetointegrador.entities.*;
import com.projetointegrador.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200/")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/findAll")
    public ResponseEntity<List<ClienteEntity>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/findById")
    public ResponseEntity<ClienteEntity> findById(@RequestParam Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<ClienteEntity> update(@RequestBody ClienteEntity clienteEntity) {
        return ResponseEntity.ok(clienteService.update(clienteEntity));
    }
}