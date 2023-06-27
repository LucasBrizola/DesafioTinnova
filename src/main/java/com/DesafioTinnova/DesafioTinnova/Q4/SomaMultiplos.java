package com.DesafioTinnova.DesafioTinnova.Q4;

public class SomaMultiplos {

    private int soma = 0;

    public SomaMultiplos() {
    }

    public int getSoma() {
        return soma;
    }

    public void somaMultiplos(int num) {
        num = num - 1;
        if(num < 3){
            throw new RuntimeException("número não possui múltiplos de 3 ou 5");
        }

        for (int i = num; i > 2; i--) {
            if (i % 3 == 0 || i % 5 == 0) {
                soma = soma + i;
            }
        }
    }
}
