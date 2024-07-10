package com.hugoalves.sales.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CnpjExistenteException.class)
    public ResponseEntity<Object> handleCnpjExistenteException(CnpjExistenteException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(CnpjInexistenteException.class)
    public ResponseEntity<Object> handleCnpjInexistenteException(CnpjInexistenteException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(ListaDeClientesVaziaException.class)
    public ResponseEntity<Object> handleListaDeClientesVaziaException(ListaDeClientesVaziaException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(ListaDeVendasVaziaException.class)
    public ResponseEntity<Object> handleListaDeVendasVaziaException(ListaDeVendasVaziaException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(VendaExistenteException.class)
    public ResponseEntity<Object> handleVendaExistenteException(VendaExistenteException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(VendaInexistenteException.class)
    public ResponseEntity<Object> handleVendaInexistenteException(VendaInexistenteException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}
