package com.hugoalves.sales.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table (name = "cliente")
@Entity(name = "cliente")
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
    private String uf;

    @NotNull
    private String localizacao;

    public Cliente(String nome, String email, String telefone, String uf, String localizacao) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.uf = uf;
        this.localizacao = localizacao;
    }

    public Cliente(String nome, String cnpj, String email, String telefone, String uf, String localizacao) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.uf = uf;
        this.localizacao = localizacao;
    }

}
