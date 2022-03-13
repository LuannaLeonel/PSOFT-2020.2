package com.ufcg.psoft.coronavirusbrasil.DTO;

public class UnidadeSaudeDTO {
	
    private String nome;

    private String endereco;
    
	private Long cnpj;
	
	private Long cep;
	
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
    
	public void setCep(Long cep) {
		this.cep = cep;
	}
	
	public Long getCep() {
		return this.cep;
	}
}
