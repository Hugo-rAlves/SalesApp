package com.hugoalves.sales.controller;

import com.hugoalves.sales.model.Venda;
import com.hugoalves.sales.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    private ResponseEntity<Venda> cadastrarVenda(@RequestBody Venda venda){
        return ResponseEntity.ok(vendaService.save(venda));
    }

    @GetMapping
    private ResponseEntity<List<Venda>> listarVendas(){
        return ResponseEntity.ok(vendaService.findAll());
    }

    @GetMapping("/cnpj/{cnpj}")
    private ResponseEntity<List<Venda>> buscarVendaPorCnpj(@PathVariable String cnpj){
        return ResponseEntity.ok(vendaService.findByCnpj(cnpj));
    }

    @GetMapping("/id/{id}")
    private ResponseEntity<Optional<Venda>> buscarVendaPorId(@PathVariable Long id){
        return ResponseEntity.ok(vendaService.findById(id));
    }

    @DeleteMapping
    private ResponseEntity<Void> deletarVenda(@PathVariable Long id){
        vendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
