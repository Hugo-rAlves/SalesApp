package com.hugoalves.sales.service;

import com.hugoalves.sales.model.Venda;
import com.hugoalves.sales.model.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repository;

    public Venda save(Venda venda){
        return repository.save(venda);
    }

    public List<Venda> findAll(){
        return repository.findAll();
    }

    public List<Venda> findByCnpj(String cnpj){
        return repository.findByCnpj(cnpj);
    }

    public Optional<Venda> findById(Long id){
        return repository.findById(id);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
