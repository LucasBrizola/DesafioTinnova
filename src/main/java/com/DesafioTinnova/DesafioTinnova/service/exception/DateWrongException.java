package com.DesafioTinnova.DesafioTinnova.service.exception;

import java.time.LocalDateTime;

public class DateWrongException extends RuntimeException{
    public DateWrongException() { super("ano do carro deve ser válido (entre 1900 e ano de hoje (" + LocalDateTime.now().getYear() + ")"); }
}
