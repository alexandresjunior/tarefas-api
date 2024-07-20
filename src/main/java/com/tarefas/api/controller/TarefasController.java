package com.tarefas.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.model.Tarefa;
import com.tarefas.api.service.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> cadastrarTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvarTarefa(tarefa));
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        return ResponseEntity.ok().body(tarefaService.listarTarefas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPeloId(@PathVariable("id") Long id) {
        Optional<Tarefa> tarefa = tarefaService.buscarTarefa(id);

        if (tarefa.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(tarefa.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable("id") Long id) {
        Optional<Tarefa> tarefa = tarefaService.buscarTarefa(id);

        if (tarefa.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
    
}
