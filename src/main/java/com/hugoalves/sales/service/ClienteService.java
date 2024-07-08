package com.hugoalves.sales.service;

import com.hugoalves.sales.model.Cliente;
import com.hugoalves.sales.model.repositories.ClienteRepository;
import com.hugoalves.sales.service.exceptions.CnpjExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Optional<Cliente> findByCnpj(String cnpj){
        return repository.findByCnpj(cnpj);
    }

    public void deleteByCnpj(String cnpj){
        repository.deleteByCnpj(cnpj);
    }
}
