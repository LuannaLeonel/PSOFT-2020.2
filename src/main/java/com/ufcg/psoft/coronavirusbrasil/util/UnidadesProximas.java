package com.ufcg.psoft.coronavirusbrasil.util;

import java.util.List;

import com.ufcg.psoft.coronavirusbrasil.model.Hospital;
import com.ufcg.psoft.coronavirusbrasil.model.UnidadeSaude;

public class UnidadesProximas {

	private List<Hospital> hospitaisProximos;
	private List<UnidadeSaude> unidadesSaudeProximas;
	
	public UnidadesProximas(List<Hospital> hospitais, List<UnidadeSaude> unidadesSaude) {
		this.hospitaisProximos = hospitais;
		this.unidadesSaudeProximas = unidadesSaude;
	}
	
	@Override
	public String toString() {
		String stringHospitaisProximos = "Hospitais próximos:\n";
		
		for (int i = 0; i < hospitaisProximos.size(); i++) {
			if (i > 4) break;
			
			Hospital hospital = hospitaisProximos.get(i);
			
			stringHospitaisProximos += (i + 1) + ". " + hospital.getNome() + "\n";
		}
		
		String stringUnidadesSaudeProximas = "\nUnidades Saúde próximas:\n";
		
		for (int i = 0; i < unidadesSaudeProximas.size(); i++) {
			if (i > 4) break;
		
			UnidadeSaude unidadeSaude = unidadesSaudeProximas.get(i);
			
			stringUnidadesSaudeProximas += (i + 1) + ". " + unidadeSaude.getNome() + "\n";
		}
		
		return stringHospitaisProximos + stringUnidadesSaudeProximas;
	}
}
