package com.hugoalves.sales.controller;

import com.hugoalves.sales.model.Cliente;
import com.hugoalves.sales.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    private ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @GetMapping
    private ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{cnpj}")
    private ResponseEntity<Optional<Cliente>> buscarCliente(@PathVariable String cnpj) {
        return ResponseEntity.ok(clienteService.findByCnpj(cnpj));
    }

    @DeleteMapping("{cnpj}")
    private ResponseEntity<Void> deletarCliente(@PathVariable String cnpj) {
        clienteService.deleteByCnpj(cnpj);
        return ResponseEntity.noContent().build();
    }



}
