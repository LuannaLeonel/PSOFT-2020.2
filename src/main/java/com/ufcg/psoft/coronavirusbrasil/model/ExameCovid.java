package com.ufcg.psoft.coronavirusbrasil.model;

import javax.persistence.*;

import com.ufcg.psoft.coronavirusbrasil.enums.TipoTeste;

import java.util.Date;

@Entity
public class ExameCovid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long cpfDoPaciente;

	private TipoTeste tipoTeste;

	private boolean isCovid;

	private Date data;

	public ExameCovid(Long cpfDoPaciente, TipoTeste tipoTeste, boolean isCovid, Date data) {
		this.cpfDoPaciente = cpfDoPaciente;
		this.tipoTeste = tipoTeste;
		this.isCovid = isCovid;
		this.data = data;
	}

	public ExameCovid() {

	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public Long getCpfDoPaciente() {
		return cpfDoPaciente;
	}

	public void setCpfDoPaciente(Long cpfDoPaciente) {
		this.cpfDoPaciente = cpfDoPaciente;
	}

	public TipoTeste getTipoTeste() {
		return tipoTeste;
	}

	public void setTipoTeste(TipoTeste tipoTeste) {
		this.tipoTeste = tipoTeste;
	}

	public boolean isCovid() {
		return isCovid;
	}

	public void setCovid(boolean covid) {
		isCovid = covid;
	}
}
