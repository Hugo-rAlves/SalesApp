package com.hugoalves.sales.service.exceptions;

public class CnpjExistenteException extends IllegalArgumentException{

    public CnpjExistenteException(String cnpj){
        super("Este CNPJ ja existe.");
    }
}
