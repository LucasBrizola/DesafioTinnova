package com.DesafioTinnova.DesafioTinnova.Q3;

public class Fatorial {

    private int fatorial;

    public Fatorial(){}

    public int getFatorial() {
        return fatorial;
    }

    public void calcularFatorial(int num){
        if(num == 0){
            this.fatorial = 1;
            return;
        }

        if(num < 0){
            throw new RuntimeException("não existe fatorial de numeros negativos");
        }
        fatorial = num;
        for (int i = (num-1); i > 0; i--){
            this.fatorial = fatorial * i;
        }
    }
}
