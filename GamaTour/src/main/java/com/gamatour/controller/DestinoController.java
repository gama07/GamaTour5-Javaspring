package com.gamatour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gamatour.models.Destino;
import com.gamatour.repositories.DestinoRepository;

import java.util.List;

@RestController
@RequestMapping("/destino")
public class DestinoController {

    @Autowired
    private DestinoRepository destinoRepository;

    @GetMapping("/todosdestinos")
    public ResponseEntity<List<Destino>> getAllDestinos() {
        List<Destino> destinos = destinoRepository.findAll();
        return ResponseEntity.ok(destinos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> getDestinoById(@PathVariable Integer id) {
        return destinoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/salvardestino")
    public ResponseEntity<Destino> salvarDestino(@RequestBody Destino destino) {
        Destino destinoCriado = destinoRepository.save(destino);
        return new ResponseEntity<>(destinoCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destino> atualizarDestino(@PathVariable Integer id, @RequestBody Destino destinoAtualizado) {
        return destinoRepository.findById(id)
                .map(destino -> {
                    destino.setNomeDestino(destinoAtualizado.getNomeDestino());
                    Destino destinoAtualizadoSalvo = destinoRepository.save(destino);
                    return ResponseEntity.ok(destinoAtualizadoSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarDestino(@PathVariable Integer id) {
        return destinoRepository.findById(id)
                .map(destino -> {
                    destinoRepository.delete(destino);
                    return ResponseEntity.ok(id + " Deleted");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
