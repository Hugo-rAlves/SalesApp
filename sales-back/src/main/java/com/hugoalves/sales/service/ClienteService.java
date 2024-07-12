package com.hugoalves.sales.service;

import com.hugoalves.sales.model.Cliente;
import com.hugoalves.sales.model.dto.ClienteDTO;
import com.hugoalves.sales.model.mapper.ClienteMapper;
import com.hugoalves.sales.model.repositories.ClienteRepository;
import com.hugoalves.sales.service.exceptions.ClienteInexistenteException;
import com.hugoalves.sales.service.exceptions.CnpjExistenteException;
import com.hugoalves.sales.service.exceptions.CnpjInexistenteException;
import com.hugoalves.sales.service.exceptions.ListaDeClientesVaziaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteMapper clienteMapper;

    public Cliente save(Cliente cliente){
        if (repository.existsByCnpj(cliente.getCnpj())){
            throw new CnpjExistenteException(cliente.getCnpj());
        } else {
            return repository.save(cliente);
        }
    }

    public List<Cliente> findAll() throws ListaDeClientesVaziaException {
        List<Cliente> clientes = repository.findAll();
        if (clientes.isEmpty()){
            throw new ListaDeClientesVaziaException();
        } else {
            return clientes;
        }
    }

    public Cliente findByCnpj(String cnpj){
        if (!repository.existsByCnpj(cnpj)){
            throw new CnpjInexistenteException(cnpj);
        } else {
            return repository.findByCnpj(cnpj);
        }
    }

    public void deleteById(Long id){
        if (!repository.existsById(id)){
            throw new ClienteInexistenteException(id);
        } else {
            repository.deleteById(id);
        }
    }

    public Cliente updateByCnpj(ClienteDTO clienteDTO){
        Cliente clienteExistente = repository.findByCnpj(clienteDTO.cnpj());
        if (clienteExistente == null) {
            throw new CnpjInexistenteException(clienteDTO.cnpj());
        } else {
            Cliente cliente = clienteMapper.toEntity(clienteDTO);
            cliente.setId(clienteExistente.getId());
            cliente.setCnpj(clienteDTO.cnpj());

            return repository.save(cliente);
        }
    }
}
