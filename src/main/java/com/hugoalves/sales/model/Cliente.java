package com.hugoalves.sales.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Column(unique = true)
    private String cnpj;

    @NotNull
    private String email;

    @NotNull
    private String telefone;

    @NotNull
    private String UF;

    @NotNull
    private String localizacao;

}
