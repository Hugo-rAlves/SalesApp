package com.hugoalves.sales.model;

import com.hugoalves.sales.model.enumeration.StatusPagamento;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "venda")
@Entity(name = "venda")
@NoArgsConstructor
@Getter
@Setter
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_cnpj")
    @NotNull
    private Cliente cliente;

    @NotNull
    private LocalDate data;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @NotNull
    private double valor;
}
