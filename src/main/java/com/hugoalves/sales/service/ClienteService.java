package com.hugoalves.sales.service;

import com.hugoalves.sales.model.Cliente;
import com.hugoalves.sales.model.repositories.ClienteRepository;
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

    public void deleteByCnpj(String cnpj){
        if (!repository.existsByCnpj(cnpj)){
            throw new CnpjInexistenteException(cnpj);
        } else {
            repository.deleteByCnpj(cnpj);
        }
    }
}
