package com.DesafioTinnova.DesafioTinnova.Q2;

public class BubbleSort {
    private int[] vetor;

    public BubbleSort() {
    }

    public int[] getVetor() {
        return vetor;
    }

    public int getVetorValue(int i) {
        return vetor[i];
    }

    public void setVetor(int[] vetor) {
        this.vetor = vetor;
    }

    public void bubbleSort() {
        int valorFinal = vetor.length - 1;
        for (int i = 0; i <= vetor.length; i++) {
            for (int j = 0; j < valorFinal; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j + 1];
                    vetor[j + 1] = vetor[j];
                    vetor[j] = temp;
                }
            }
            valorFinal--;
        }
    }

}
