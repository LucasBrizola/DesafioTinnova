package com.DesafioTinnova.DesafioTinnova.service.exception;

public class VeiculosEmptyException extends RuntimeException{
    public VeiculosEmptyException() { super("sem veiculos cadastrados"); }
}
