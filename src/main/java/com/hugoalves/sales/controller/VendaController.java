package com.hugoalves.sales.controller;

import com.hugoalves.sales.model.Venda;
import com.hugoalves.sales.model.dto.VendaDTO;
import com.hugoalves.sales.model.mapper.VendaMapper;
import com.hugoalves.sales.service.VendaService;
import com.hugoalves.sales.service.exceptions.ListaDeVendasVaziaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private VendaMapper vendaMapper;

    @PostMapping
    private ResponseEntity<Venda> cadastrarVenda(@RequestBody @Valid VendaDTO vendaDTO){
        Venda venda = vendaMapper.toEntity(vendaDTO);
        return ResponseEntity.ok(vendaService.save(venda));
    }

    @GetMapping
    private ResponseEntity<List<VendaDTO>> listarVendas() throws ListaDeVendasVaziaException {
        List<Venda> listaVendas = vendaService.findAll();
        return ResponseEntity.ok(vendaMapper.toDtoList(listaVendas));
    }

    @GetMapping("/cnpj/{cnpj}")
    private ResponseEntity<List<VendaDTO>> buscarVendaPorCnpj(@PathVariable String cnpj) throws ListaDeVendasVaziaException {
        List<Venda> listaVendasCnpj = vendaService.findByCnpj(cnpj);
        return ResponseEntity.ok(vendaMapper.toDtoList(listaVendasCnpj));
    }

    @GetMapping("/id/{id}")
    private ResponseEntity<VendaDTO> buscarVendaPorId(@PathVariable Long id){
        Venda venda = vendaService.findById(id);
        return ResponseEntity.ok(vendaMapper.toDto(venda));
    }

    @DeleteMapping
    private ResponseEntity<Void> deletarVenda(@PathVariable Long id){
        vendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
