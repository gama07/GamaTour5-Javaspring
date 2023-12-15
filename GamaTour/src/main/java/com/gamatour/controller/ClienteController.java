package com.gamatour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gamatour.models.Cliente;
import com.gamatour.repositories.ClienteRepository;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/allclientes")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/savecliente")
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
        Cliente clienteCriado = clienteRepository.save(cliente);
        return new ResponseEntity<>(clienteCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNomeCliente(clienteAtualizado.getNomeCliente());
                    cliente.setCpfCliente(clienteAtualizado.getCpfCliente());
                    cliente.setEmailCliente(clienteAtualizado.getEmailCliente());

                    Cliente clienteAtualizadoSalvo = clienteRepository.save(cliente);
                    return ResponseEntity.ok(clienteAtualizadoSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return ResponseEntity.ok(id + " Deleted");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
