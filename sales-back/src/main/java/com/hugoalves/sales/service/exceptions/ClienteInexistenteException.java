package com.hugoalves.sales.service.exceptions;

public class ClienteInexistenteException extends IllegalArgumentException{

    public ClienteInexistenteException(Long id){
        super("Nao existe cliente com o id " + id);
    }
}
