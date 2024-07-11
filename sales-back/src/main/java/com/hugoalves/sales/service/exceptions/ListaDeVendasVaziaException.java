package com.hugoalves.sales.service.exceptions;

public class ListaDeVendasVaziaException extends Exception{

    public ListaDeVendasVaziaException(){
        super("A lista de vendas está vazia.");
    }
}
