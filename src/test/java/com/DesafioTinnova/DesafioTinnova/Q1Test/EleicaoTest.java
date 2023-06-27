package com.DesafioTinnova.DesafioTinnova.Q1Test;

import com.DesafioTinnova.DesafioTinnova.Q1.Eleicao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EleicaoTest {

    Eleicao eleicao = new Eleicao(1000, 800, 150, 50);

    @Test
    @DisplayName("calcular percentual de votosValidos por totalEleitores")
    public void deveCalcularPercentualVotosValidosPorTotalEleitores(){
        double resultado = eleicao.calcularPercentualVotosValidosPorTotalEleitores();

        Assertions.assertEquals(80, resultado);
    }

    @Test
    @DisplayName("calcular percentual de votosBrancos por totalEleitores")
    public void deveCalcularPercentualVotosBrancosPorTotalEleitores(){
        double resultado = eleicao.calcularPercentualVotosBrancosPorTotalEleitores();

        Assertions.assertEquals(15, resultado);
    }

    @Test
    @DisplayName("calcular percentual de votosNulos por totalEleitores")
    public void deveCalcularPercentualVotosNulosPorTotalEleitores(){
        double resultado = eleicao.calcularPercentualVotosNulosPorTotalEleitores();

        Assertions.assertEquals(5.0, resultado);
    }

}
