package com.ufcg.psoft.coronavirusbrasil.model;

import javax.persistence.*;

@Entity
public class UnidadeSaude {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;

    private String endereco;
    
    private Long cep;

	private Long cnpj;
    
    public UnidadeSaude() {}
    
    public UnidadeSaude(String nome, String endereco, Long cnpj, Long cep) {
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.cep = cep;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public Long getCNPJ() {
    	return this.cnpj;
    }
    
    public void setCNPJ(Long cnpj) {
    	this.cnpj = cnpj;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
	public void setCep(Long cep) {
		this.cep = cep;
	}
	
	public Long getCep() {
		return this.cep;
	}
}
