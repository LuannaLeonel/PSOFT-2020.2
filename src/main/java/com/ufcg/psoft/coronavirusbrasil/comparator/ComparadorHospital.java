package com.ufcg.psoft.coronavirusbrasil.comparator;

import java.util.Comparator;

import com.ufcg.psoft.coronavirusbrasil.model.Hospital;

public class ComparadorHospital implements Comparator<Hospital> {
	
	private Long cep;
	
	public ComparadorHospital(Long cep) {
		this.cep = cep;
	}

	@Override
	public int compare(Hospital hospitalA, Hospital hospitalB) {
		Long distanciaHospitalA = Math.abs(this.cep - hospitalA.getCep());
		Long distanciaHospitalB = Math.abs(this.cep - hospitalB.getCep());
		
		Long diff = distanciaHospitalA - distanciaHospitalB;
		
		if (diff > 0) return 1;
		else if (diff == 0) return 0;
		return -1;
	}
	
}
