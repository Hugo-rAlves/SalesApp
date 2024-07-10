package com.hugoalves.sales.service.exceptions;

public class VendaExistenteException extends IllegalArgumentException{

    public VendaExistenteException(){
        super("Já existe uma venda com esse ID.");
    }
}
