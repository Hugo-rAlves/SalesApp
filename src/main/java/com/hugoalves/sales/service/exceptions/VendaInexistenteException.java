package com.hugoalves.sales.service.exceptions;

public class VendaInexistenteException extends IllegalArgumentException{

    public VendaInexistenteException(Long id){
        super("Nao existe venda com o id " + id + ".");
    }
}
