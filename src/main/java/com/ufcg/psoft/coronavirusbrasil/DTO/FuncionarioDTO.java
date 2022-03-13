package com.ufcg.psoft.coronavirusbrasil.DTO;

public class FuncionarioDTO {
    private String nome;

    private Boolean ehHomem;

    private Long cpf;

    public void setNome(String nome) {
    	this.nome = nome;
    }
    
    public String getNome() {
    	return this.nome;
    }
    
    public void setEhHomem(Boolean ehHomem) {
    	this.ehHomem = ehHomem;
    }
    
    public Boolean getEhHomem() {
    	return this.ehHomem;
    }
    
    public void setCPF(Long cpf) {
    	this.cpf = cpf;
    }
    
    public Long getCPF() {
    	return this.cpf;
    }
}
