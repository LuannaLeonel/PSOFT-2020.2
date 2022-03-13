package com.ufcg.psoft.coronavirusbrasil.DTO;

public class HospitalDTO {

    private String nome;

    private Long cnpj;
    
    private Long cep;

    private EnderecoDTO endereco;

    private String senha;

	private int qtdLeitosEnfermaria;

    private int qtdLeitosUTI;

    private int obitos;

    private int casosRecuperados;

    private int ocupacaoLeitos;

    private int totalCasosConfirmados;

    public int getTotalCasosConfirmados() {
        return totalCasosConfirmados;
    }

    public void setTotalCasosConfirmados(int totalCasosConfirmados) {
        this.totalCasosConfirmados = totalCasosConfirmados;
    }

    public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdLeitosEnfermaria() {
        return qtdLeitosEnfermaria;
    }

    public void setQtdLeitosEnfermaria(int qtdLeitosEnfermaria) {
        this.qtdLeitosEnfermaria = qtdLeitosEnfermaria;
    }

    public int getQtdLeitosUTI() {
        return qtdLeitosUTI;
    }

    public void setQtdLeitosUTI(int qtdLeitosUTI) {
        this.qtdLeitosUTI = qtdLeitosUTI;
    }

    public int getObitos() {
        return obitos;
    }

    public void setObitos(int obitos) {
        this.obitos = obitos;
    }

    public int getCasosRecuperados() {
        return casosRecuperados;
    }

    public void setCasosRecuperados(int casosRecuperados) {
        this.casosRecuperados = casosRecuperados;
    }

    public int getOcupacaoLeitos() {
        return ocupacaoLeitos;
    }

    public void setOcupacaoLeitos(int ocupacaoLeitos) {
        this.ocupacaoLeitos = ocupacaoLeitos;
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

    public EnderecoDTO getEndereço() {
        return endereco;
    }

    public void setEndereço(EnderecoDTO endereço) {
        this.endereco = endereço;
    }
}
