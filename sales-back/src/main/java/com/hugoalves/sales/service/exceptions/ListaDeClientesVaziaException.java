package com.hugoalves.sales.service.exceptions;

public class ListaDeClientesVaziaException extends Exception{

    public ListaDeClientesVaziaException(){
        super("A lista de clientes está vazia.");
    }
}
