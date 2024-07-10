package com.hugoalves.sales.service.exceptions;

public class CnpjInexistenteException extends IllegalArgumentException{

    public CnpjInexistenteException(String cnpj){
        super("O CNPJ: " + cnpj + " não existe.");
    }
}
