package com.ufcg.psoft.coronavirusbrasil.util.calculator;

import com.ufcg.psoft.coronavirusbrasil.enums.Sexo;
import com.ufcg.psoft.coronavirusbrasil.model.Hospital;
import com.ufcg.psoft.coronavirusbrasil.model.Paciente;
import com.ufcg.psoft.coronavirusbrasil.repository.HospitalRepository;
import com.ufcg.psoft.coronavirusbrasil.util.CalculadorIdade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CalculadorObitosBrasil implements Calculador{

    @Autowired
    HospitalRepository hospitalRepository;

    public String calculaObitos(Sexo sexo, int faixaEtariaInicio, int faixaEtariaFim, String local) {
        int numeroDeObitos = 0;
        for (Hospital h : this.hospitalRepository.findAll()) {
            List<Paciente> obitos = h.getObitos();
            for (Paciente p : obitos) {
                int idadePaciente = CalculadorIdade.calculaIdade(p.getNascimento());
                boolean dentroFaixaEtaria = idadePaciente <= faixaEtariaFim && idadePaciente >= faixaEtariaInicio;
                if (p.getSexo().equals(sexo) && dentroFaixaEtaria) {
                    numeroDeObitos++;
                }
            }
        }
        return String.format("O número de óbitos para o sexo %s, dentro da faixa etária de %d aos %d anos," +
                " no %s foi de %d pessoas.", sexo, faixaEtariaInicio, faixaEtariaFim, local, numeroDeObitos);
    }
}
