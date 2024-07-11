package com.hugoalves.sales.service;

import com.hugoalves.sales.model.Venda;
import com.hugoalves.sales.model.repositories.VendaRepository;
import com.hugoalves.sales.service.exceptions.ListaDeVendasVaziaException;
import com.hugoalves.sales.service.exceptions.VendaExistenteException;
import com.hugoalves.sales.service.exceptions.VendaInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repository;

    public Venda save(Venda venda){
        if (repository.existsById(venda.getId())){
            throw new VendaExistenteException();
        } else {
            return repository.save(venda);
        }
    }

    public List<Venda> findAll() throws ListaDeVendasVaziaException {
        List<Venda> vendas = repository.findAll();
        if (vendas.isEmpty()){
            throw new ListaDeVendasVaziaException();
        } else {
            return vendas;
        }
    }

    public List<Venda> findByCnpj(String cnpj) throws ListaDeVendasVaziaException {
        List<Venda> vendas = repository.findByCnpj(cnpj);
        if (vendas.isEmpty()){
            throw new ListaDeVendasVaziaException();
        } else {
            return vendas;
        }
    }

    public Venda findById(Long id){
        if (!repository.existsById(id)){
            throw new VendaInexistenteException(id);
        } else {
            return repository.findById(id).get();
        }
    }

    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new VendaInexistenteException(id);
        } else {
            repository.deleteById(id);
        }
    }
}
