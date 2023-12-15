package com.gamatour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gamatour.models.Passagem;
import com.gamatour.repositories.PassagemRepository;

import java.util.List;

@RestController
@RequestMapping("/passagem")
public class PassagemController {

    @Autowired
    private PassagemRepository passagemRepository;

    @GetMapping("/todaspassagens")
    public ResponseEntity<List<Passagem>> getAllPassagens() {
        List<Passagem> passagens = passagemRepository.findAll();
        return ResponseEntity.ok(passagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passagem> getPassagemById(@PathVariable Long id) {
        Passagem passagem = passagemRepository.findById(id)
                .orElse(null);

        return (passagem != null) ?
                ResponseEntity.ok(passagem) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/salvarpassagem")
    public ResponseEntity<Passagem> salvarPassagem(@RequestBody Passagem passagem) {
        Passagem passagemCriada = passagemRepository.save(passagem);
        return new ResponseEntity<>(passagemCriada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passagem> atualizarPassagem(@PathVariable Long id, @RequestBody Passagem passagemAtualizada) {
        return passagemRepository.findById(id)
                .map(passagem -> {
                    passagem.setDataPartida(passagemAtualizada.getDataPartida());
                    passagem.setNumPassageiros(passagemAtualizada.getNumPassageiros());
                    passagem.setDestino(passagemAtualizada.getDestino());
                    passagem.setDataRetorno(passagemAtualizada.getDataRetorno());
                    passagem.setCliente(passagemAtualizada.getCliente());
                    passagem.setPrecoPassagem(passagemAtualizada.getPrecoPassagem());

                    Passagem passagemAtualizadaSalva = passagemRepository.save(passagem);
                    return ResponseEntity.ok(passagemAtualizadaSalva);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPassagem(@PathVariable Long id) {
        return passagemRepository.findById(id)
                .map(passagem -> {
                    passagemRepository.delete(passagem);
                    return ResponseEntity.ok(id + " Deleted");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
