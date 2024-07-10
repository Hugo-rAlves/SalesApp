package com.hugoalves.sales.controller;

import com.hugoalves.sales.model.Cliente;
import com.hugoalves.sales.model.dto.ClienteDTO;
import com.hugoalves.sales.model.mapper.ClienteMapper;
import com.hugoalves.sales.service.ClienteService;
import com.hugoalves.sales.service.exceptions.ListaDeClientesVaziaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @PostMapping
    private ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @GetMapping
    private ResponseEntity<List<ClienteDTO>> listarClientes() throws ListaDeClientesVaziaException {
        List<Cliente> listaClientes = clienteService.findAll();
        return ResponseEntity.ok(clienteMapper.toDtoList(listaClientes));
    }

    @GetMapping("/{cnpj}")
    private ResponseEntity<ClienteDTO> buscarCliente(@PathVariable String cnpj) {
        Cliente cliente = clienteService.findByCnpj(cnpj);
        return ResponseEntity.ok(clienteMapper.toDto(cliente));
    }

    @DeleteMapping("{cnpj}")
    private ResponseEntity<Void> deletarCliente(@PathVariable String cnpj) {
        clienteService.deleteByCnpj(cnpj);
        return ResponseEntity.noContent().build();
    }



}
