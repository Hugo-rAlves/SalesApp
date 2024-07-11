package com.hugoalves.sales.model.mapper;

import com.hugoalves.sales.model.Cliente;
import com.hugoalves.sales.model.dto.ClienteDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();

        cliente.setNome(clienteDTO.nome());
        cliente.setCnpj(clienteDTO.cnpj());
        cliente.setEmail(clienteDTO.email());
        cliente.setTelefone(clienteDTO.telefone());
        cliente.setUf(clienteDTO.uf());
        cliente.setLocalizacao(clienteDTO.localizacao());

        return cliente;
    }

    public ClienteDTO toDto(Cliente cliente) {
        return new ClienteDTO(cliente.getNome(),
                cliente.getCnpj(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getUf(),
                cliente.getLocalizacao());
    }

    public List<ClienteDTO> toDtoList(List<Cliente> listaClientes) {
        List<ClienteDTO> listaClienteDTO = new ArrayList<>();
        for (Cliente cliente : listaClientes) {
            listaClienteDTO.add(toDto(cliente));
        }
        return listaClienteDTO;
    }

}
