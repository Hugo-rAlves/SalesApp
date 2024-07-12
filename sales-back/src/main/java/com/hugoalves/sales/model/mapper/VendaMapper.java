package com.hugoalves.sales.model.mapper;

import com.hugoalves.sales.model.Cliente;
import com.hugoalves.sales.model.Venda;
import com.hugoalves.sales.model.dto.VendaDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VendaMapper {

    public Venda toEntity(VendaDTO vendaDTO) {
        Venda venda = new Venda();

        venda.setData(vendaDTO.data());
        venda.setStatus(vendaDTO.status());
        venda.setValor(vendaDTO.valor());

        return venda;
    }

    public VendaDTO toDto(Venda venda) {
        return new VendaDTO(venda.getCliente(),
                venda.getData(),
                venda.getStatus(),
                venda.getValor());
    }

    public List<VendaDTO> toDtoList(List<Venda> listaVendas) {
        List<VendaDTO> listaVendaDTO = new ArrayList<>();
        for (Venda venda : listaVendas) {
            listaVendaDTO.add(toDto(venda));
        }
        return listaVendaDTO;
    }
}
