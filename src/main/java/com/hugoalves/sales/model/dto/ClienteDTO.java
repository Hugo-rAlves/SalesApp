package com.hugoalves.sales.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteDTO {

    private String nome;
    private String cnpj;
    private String email;
    private String telefone;
    private String UF;
    private String localizacao;

}
