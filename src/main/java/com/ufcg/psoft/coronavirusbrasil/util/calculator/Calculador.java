
package com.ufcg.psoft.coronavirusbrasil.util.calculator;

import com.ufcg.psoft.coronavirusbrasil.enums.Sexo;

public interface Calculador {

    String calculaObitos(Sexo sexo, int faixaEtariaInicio, int faixaEtariaFim, String local);
}