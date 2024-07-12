package com.hugoalves.sales.service;

import com.hugoalves.sales.model.Cliente;
import com.hugoalves.sales.model.Venda;
import com.hugoalves.sales.model.dto.VendaDTO;
import com.hugoalves.sales.model.mapper.VendaMapper;
import com.hugoalves.sales.model.repositories.ClienteRepository;
import com.hugoalves.sales.model.repositories.VendaRepository;
import com.hugoalves.sales.service.exceptions.CnpjInexistenteException;
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
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VendaMapper vendaMapper;

    public Venda save(VendaDTO vendaDTO){
        Cliente cliente = clienteRepository.findByCnpj(vendaDTO.cliente().getCnpj());

        Venda venda = vendaMapper.toEntity(vendaDTO);
        venda.setCliente(cliente);

        return repository.save(venda);
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

    public Venda updateById(Long id, VendaDTO vendaDTO){
        if (!repository.existsById(id)) {
            throw new VendaInexistenteException(id);
        } else {
            Cliente cliente = clienteRepository.findByCnpj(vendaDTO.cliente().getCnpj());

            Venda venda = repository.findById(id).orElseThrow(() -> new VendaInexistenteException(id));

            venda.setCliente(cliente);
            venda.setData(vendaDTO.data());
            venda.setStatus(vendaDTO.status());
            venda.setValor(vendaDTO.valor());

            return repository.save(venda);
        }
    }
}
