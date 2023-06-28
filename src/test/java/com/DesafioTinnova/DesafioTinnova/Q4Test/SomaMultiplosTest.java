package com.DesafioTinnova.DesafioTinnova.Q4Test;

import com.DesafioTinnova.DesafioTinnova.questoes.Q4.SomaMultiplos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SomaMultiplosTest {

    @Test
    @DisplayName("deve somar multiplos de 7")
    public void deveSomarMultiplosDe7(){
        SomaMultiplos somaMultiplos = new SomaMultiplos();
        somaMultiplos.somaMultiplos(7);

        Assertions.assertEquals(14, somaMultiplos.getSoma());
    }

    @Test
    @DisplayName("deve somar multiplos abaixo de 10")
    public void deveSomarMultiplosAbaixoDe10(){
        SomaMultiplos somaMultiplos = new SomaMultiplos();
        somaMultiplos.somaMultiplos(10);

        Assertions.assertEquals(23, somaMultiplos.getSoma());
    }

    @Test
    @DisplayName("deve retornar erro fatorial de numero negativo")
    public void deveRetornarErroNumeroSemMultiplo(){
        SomaMultiplos somaMultiplos = new SomaMultiplos();

        Assertions.assertThrows(RuntimeException.class, () -> somaMultiplos.somaMultiplos(3));
    }
}
