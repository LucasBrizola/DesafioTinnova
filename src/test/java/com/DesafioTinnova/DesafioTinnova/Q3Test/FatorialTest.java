package com.DesafioTinnova.DesafioTinnova.Q3Test;

import com.DesafioTinnova.DesafioTinnova.Q3.Fatorial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FatorialTest {

    @Test
    @DisplayName("deve retornar fatorial de 0")
    public void deveRetornarFatorialDe0(){
        Fatorial fatorial = new Fatorial();
        fatorial.calcularFatorial(0);

        Assertions.assertEquals(1, fatorial.getFatorial());
    }

    @Test
    @DisplayName("deve retornar fatorial de 5")
    public void deveRetornarFatorialDe1(){
        Fatorial fatorial = new Fatorial();
        fatorial.calcularFatorial(6);

        Assertions.assertEquals(720, fatorial.getFatorial());
    }

    @Test
    @DisplayName("deve retornar erro fatorial de numero negativo")
    public void deveRetornarErroFatorialNumeroNegativo(){
        Fatorial fatorial = new Fatorial();

        Assertions.assertThrows(RuntimeException.class, () -> fatorial.calcularFatorial(-1));
    }
}
