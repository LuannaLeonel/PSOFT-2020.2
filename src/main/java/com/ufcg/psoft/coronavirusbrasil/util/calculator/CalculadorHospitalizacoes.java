package com.ufcg.psoft.coronavirusbrasil.util.calculator;

import com.ufcg.psoft.coronavirusbrasil.enums.AreaDeBusca;
import com.ufcg.psoft.coronavirusbrasil.enums.Sexo;
import com.ufcg.psoft.coronavirusbrasil.model.Hospital;
import com.ufcg.psoft.coronavirusbrasil.model.Paciente;
import com.ufcg.psoft.coronavirusbrasil.repository.HospitalRepository;
import com.ufcg.psoft.coronavirusbrasil.util.CalculadorIdade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CalculadorHospitalizacoes {

    @Autowired
    static HospitalRepository hospitalRepository;

    public static String calculadorHospitalizacoes(Sexo sexo, int faixaEtariaInicio, int faixaEtariaFim, AreaDeBusca area, String local){
        int hosp = 0;
        List<Hospital> hospitalList = new ArrayList<Hospital>() {
        };
        if(area.equals(AreaDeBusca.CIDADE)){
            for(Hospital h : hospitalRepository.findAll()){
                if(h.getEndereco().getCidade().equals(local)){
                    hospitalList.add(h);
                }
            }
        } else if(area.equals(AreaDeBusca.ESTADO)){
            for(Hospital h : hospitalRepository.findAll()) {
                if (h.getEndereco().getEstado().equals(local)) {
                    hospitalList.add(h);
                }
            }
        } else hospitalList = hospitalRepository.findAll();

        for (Hospital h : hospitalList) {
            List<Paciente> positivos = h.getPacientesCovidPositivo();
            for(Paciente p : positivos){
                int idadePaciente = CalculadorIdade.calculaIdade(p.getNascimento());
                boolean dentroFaixaEtaria = idadePaciente <= faixaEtariaFim && idadePaciente >= faixaEtariaInicio;
                if (p.getSexo().equals(sexo) && dentroFaixaEtaria) {
                    hosp++;
                }
            }
        }

        return String.format("O número de óbitos para o sexo %s, dentro da faixa etária de %d aos %d anos," +
                " no %s foi de %d pessoas.", sexo, faixaEtariaInicio, faixaEtariaFim, local, hosp);
    }
}
