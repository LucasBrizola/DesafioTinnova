package com.DesafioTinnova.DesafioTinnova.service.exception;

public class VeiculoMissingValueException extends RuntimeException {

    public VeiculoMissingValueException() {
        super("veiculo necessita de nome, ano, marca e descricao.");
    }
}
