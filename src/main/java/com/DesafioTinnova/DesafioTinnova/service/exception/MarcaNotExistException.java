package com.DesafioTinnova.DesafioTinnova.service.exception;

public class MarcaNotExistException extends RuntimeException{
    public MarcaNotExistException(String marca) { super("marca " + marca + " não existe, favor corrigir"); }
}
