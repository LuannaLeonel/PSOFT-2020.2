package com.ufcg.psoft.coronavirusbrasil.model;

import com.ufcg.psoft.coronavirusbrasil.enums.Sexo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Funcionario {
	
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
  
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private Long CPF;
    
    @OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
    
    public Funcionario() {}
    
    public Funcionario(String nome, Sexo sexo, Long CPF) {
    	this.nome = nome;
    	this.sexo = sexo;
    	this.CPF = CPF;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Long getCpf() {
        return CPF;
    }

    public void setCpf(Long CPF) {
        this.CPF = CPF;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
    public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}