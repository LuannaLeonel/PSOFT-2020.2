package com.ufcg.psoft.coronavirusbrasil.util;

public class GeraInformacoesGerais {

    private static final double POPULACAO_BRASIL = 211000000;

    private int casosConfirmadosAcumulados;

    private int obitosConfirmados;

    public String geraInformacoesGerais(int casosConfirmadosAcumulados, int casosRecuperados, int obitosConfirmados, int casosNovos24h) {
        this.casosConfirmadosAcumulados = casosConfirmadosAcumulados;
        this.obitosConfirmados = obitosConfirmados;


        String dados = String.format("*CASOS CONFIRMADOS ACUMULADOS*\n" +
                        "Valor absoluto de casos: %d \n" +
                        "Valor em relação a população brasileira: %.2f \n " +
                        "\n*CASOS RECUPERADOS*\n" +
                        "Valor absoluto de casos recuperados: %d \n" +
                        "Relativo a quantidade de casos confirmados: %.2f \n" +
                        "\n*ÓBITOS CONFIRMADOS*\n" +
                        "Valor absoluto de óbitos confirmados: %d\n" +
                        "Relativo a quantidade de casos confirmados: %.2f \n" +
                        "\n*NOVOS CASOS NAS ULTIMAS 24H*\n" +
                        "Valor Absoluto: %d \n" +
                        "\nTaxa de letalidade: %.2f", casosConfirmadosAcumulados, valorEmRelacaoAPopulacao(),
                casosRecuperados, valorEmRelacaoACasosConfirmados(casosRecuperados),
                obitosConfirmados, valorEmRelacaoACasosConfirmados(obitosConfirmados), casosNovos24h,
                taxaDeLetalidade()
        );
        return dados;
    }

    public double valorEmRelacaoAPopulacao() {
        return casosConfirmadosAcumulados / POPULACAO_BRASIL;
    }

    public double valorEmRelacaoACasosConfirmados(int comparado) {
        return comparado / (double) casosConfirmadosAcumulados;
    }

    public double taxaDeLetalidade() {
        return obitosConfirmados / (double) casosConfirmadosAcumulados;
    }
}
