package com.DesafioTinnova.DesafioTinnova.service.exception;

public class VeiculoNotFoundException extends RuntimeException{
    public VeiculoNotFoundException(Long id) { super("veiculo " + id + " não encontrado"); }
}
