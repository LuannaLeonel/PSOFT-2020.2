package com.ufcg.psoft.coronavirusbrasil.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ufcg.psoft.coronavirusbrasil.enums.UsuarioType;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String username;

	private String password;
	
	@Enumerated(EnumType.STRING)
	private UsuarioType tipo;
	
	public Usuario() {}
	
	public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsuarioType getTipo() {
		return tipo;
	}

	public void setTipo(UsuarioType tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}	
}