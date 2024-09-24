package com.projetointegrador.controllers;

import com.projetointegrador.entities.GuiaSaidaEntity;
import com.projetointegrador.services.GuiaSaidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guia-saida")
public class GuiaSaidaController {

    @Autowired
    private GuiaSaidaService guiaSaidaService;

    @GetMapping("/findAll")
    public ResponseEntity<List<GuiaSaidaEntity>> findAll() {
        List<GuiaSaidaEntity> guiasSaidas = guiaSaidaService.findAll();
        return ResponseEntity.ok(guiasSaidas);
    }

    @GetMapping("/findById")
    public ResponseEntity<GuiaSaidaEntity> findById(@RequestParam Long id) {
        return ResponseEntity.ok(guiaSaidaService.findById(id));
    }
}