package com.ufcg.psoft.coronavirusbrasil.comparator;

import java.util.Comparator;

import com.ufcg.psoft.coronavirusbrasil.model.UnidadeSaude;

public class ComparadorUnidadeSaude implements Comparator<UnidadeSaude> {

	private Long cep;
	
	public ComparadorUnidadeSaude(Long cep) {
		this.cep = cep;
	}

	@Override
	public int compare(UnidadeSaude unidadeSaudeA, UnidadeSaude unidadeSaudeB) {
		Long distanciaUnidadeSaudeA = Math.abs(this.cep - unidadeSaudeA.getCep());
		Long distanciaUnidadeSaudeB = Math.abs(this.cep - unidadeSaudeB.getCep());
		
		Long diff = distanciaUnidadeSaudeA - distanciaUnidadeSaudeB;
		
		if (diff > 0) return 1;
		else if (diff == 0) return 0;
		return -1;
	}
}
