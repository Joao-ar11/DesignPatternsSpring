package com.example.DesignPatternsSpring.controller;

import com.example.DesignPatternsSpring.model.Cliente;
import com.example.DesignPatternsSpring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente) {
        clienteService.inserirCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletearCliente(@PathVariable("id") Long id) {
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
