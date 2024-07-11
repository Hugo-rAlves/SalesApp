package com.hugoalves.sales.model.dto;

import com.hugoalves.sales.model.Cliente;
import com.hugoalves.sales.model.enumeration.StatusPagamento;

import java.time.LocalDate;

public record VendaDTO(Cliente cliente, LocalDate data, StatusPagamento status, double valor) {
}
