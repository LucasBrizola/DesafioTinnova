package com.DesafioTinnova.DesafioTinnova.Q1;

public class Eleicao {
    private int totalEleitores;
    private int votosValidos;
    private int votosBrancos;
    private int votosNulos;

    public Eleicao(int totalEleitores, int votosValidos, int votosBrancos, int votosNulos){
        this.totalEleitores = totalEleitores;
        this.votosValidos = votosValidos;
        this.votosBrancos = votosBrancos;
        this.votosNulos = votosNulos;
    }

    public double calcularPercentualVotosValidosPorTotalEleitores(){
        return (double) (votosValidos*100)/totalEleitores;
    }

    public double calcularPercentualVotosBrancosPorTotalEleitores(){
        return (double) (votosBrancos*100)/totalEleitores;
    }

    public double calcularPercentualVotosNulosPorTotalEleitores(){
        return (double) (votosNulos*100)/totalEleitores;
    }

}
