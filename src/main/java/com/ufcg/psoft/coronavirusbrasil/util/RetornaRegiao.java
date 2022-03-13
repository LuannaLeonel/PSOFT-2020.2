package com.ufcg.psoft.coronavirusbrasil.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class RetornaRegiao {

    private static final List<String> Norte = new ArrayList<String>(Arrays.asList("ACRE", "AMAZONAS", "AMAPÁ", "PARÁ", "RONDÔNIA", "RORAIMA", "TOCANTINS"));
    private static final List<String> Nordeste = new ArrayList<>(Arrays.asList("ALAGOAS", "BAHIA", "CEARÁ", "MARANHÃO", "PIAUÍ", "PERNAMBUCO", "PARAÍBA", "RIO GRANDE DO NORTE", "SERGIPE"));
    private static final List<String> Centro_Oeste = new ArrayList<>(Arrays.asList("GOIÁS", "MATO GROSSO", "MATO GROSSO DO SUL", "DISTRITO FEDERAL"));
    private static final List<String> Sul = new ArrayList<>(Arrays.asList("PARANÁ", "RIO GRANDE DO SUL", "SANTA CATARINA"));
    private static List<String> Sudeste = new ArrayList<>(Arrays.asList("ESPÍRITO SANTO", "MINAS GERAIS", "RIO DE JANEIRO", "SÃO PAULO"));


    public static String getRegiao(String estado){
        estado = estado.toUpperCase();

        if(Norte.contains(estado)) return "NORTE";
        else if(Nordeste.contains(estado)) return "NORDESTE";
        else if(Centro_Oeste.contains(estado)) return "CENTRO_OESTE";
        else if(Sudeste.contains(estado)) return "SUDESTE";
        else return "SUL";
    }

}
