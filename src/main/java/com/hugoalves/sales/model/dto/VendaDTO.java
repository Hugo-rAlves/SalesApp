package com.hugoalves.sales.model.dto;

import com.hugoalves.sales.model.Cliente;
import com.hugoalves.sales.model.enumeration.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendaDTO {

    private Cliente cliente;
    private LocalDate data;
    private StatusPagamento status;
    private Double valor;
}
