package com.ufcg.psoft.coronavirusbrasil.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ufcg.psoft.coronavirusbrasil.DTO.EnderecoDTO;
import com.ufcg.psoft.coronavirusbrasil.enums.Regiao;
import com.ufcg.psoft.coronavirusbrasil.util.RetornaRegiao;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int numero;
	
	private String rua;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;

	private Regiao regiao;
	
	private String pais;
	
	private String CEP;
	
	public Endereco() {}
	
	public Endereco(EnderecoDTO enderecoDTO) {
		this.numero = enderecoDTO.getNumero();
		this.rua = enderecoDTO.getRua();
		this.bairro = enderecoDTO.getBairro();
		this.cidade = enderecoDTO.getCidade();
		this.estado = enderecoDTO.getEstado();
		this.pais = enderecoDTO.getPais();
		this.CEP = enderecoDTO.getCEP();
		this.regiao = Regiao.valueOf(RetornaRegiao.getRegiao(this.estado));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getRegiao(){
		return regiao.name();
	};
}