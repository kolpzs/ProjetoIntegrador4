package com.projetointegrador.controllers;

import com.projetointegrador.entities.GuiaEntradaEntity;
import com.projetointegrador.services.GuiaEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guia-entrada")
@CrossOrigin(origins = "http://localhost:4200/")
public class GuiaEntradaController {

    @Autowired
    private GuiaEntradaService guiaEntradaService;

    @GetMapping("/findAll")
    public ResponseEntity<List<GuiaEntradaEntity>> findAll() {
        List<GuiaEntradaEntity> guiasEntradas = guiaEntradaService.findAll();
        return ResponseEntity.ok(guiasEntradas);
    }

    @GetMapping("/findById")
    public ResponseEntity<GuiaEntradaEntity> findById(Long id) {
        return ResponseEntity.ok(guiaEntradaService.findById(id));
    }
}