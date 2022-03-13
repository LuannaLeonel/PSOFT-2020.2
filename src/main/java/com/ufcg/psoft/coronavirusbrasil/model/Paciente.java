package com.ufcg.psoft.coronavirusbrasil.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.ufcg.psoft.coronavirusbrasil.DTO.PacienteDTO;
import com.ufcg.psoft.coronavirusbrasil.enums.Sexo;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Sexo sexo;

    private Date internacao;

    private Date inicioSintomas;

    private Date nascimento;

    private Long cpf;
    
    private boolean foiAObito;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    private Sintomas sintomas;

    private boolean isCovid;

    public Paciente(PacienteDTO pacienteDTO) {
        this.nome = pacienteDTO.getNome();
        this.sexo = pacienteDTO.getSexo();
        this.internacao = pacienteDTO.getInternacao();
        this.inicioSintomas = pacienteDTO.getInicioSintomas();
        this.nascimento = pacienteDTO.getNascimento();
        this.cpf = pacienteDTO.getCpf();
        this.endereco = new Endereco(pacienteDTO.getEnderecoDTO());
        this.sintomas = new Sintomas(pacienteDTO.getSintomasDTO());
        this.isCovid = pacienteDTO.isCovid();
        this.foiAObito=false;
    }

    public Paciente(String nome, Sexo sexo, Date internacao, Date inicioSintomas, Date nascimento, Long cpf, Sintomas sintomas, Endereco endereco, boolean isCovid) {
        this.nome = nome;
        this.sexo = sexo;
        this.internacao = internacao;
        this.inicioSintomas = inicioSintomas;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.isCovid = isCovid;
        this.foiAObito=false;
    }

    public Paciente() {

    }

    public boolean isCovid() {
        return isCovid;
    }

    public void setCovid(boolean covid) {
        isCovid = covid;
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

    public Date getInternacao() {
        return internacao;
    }

    public void setInternacao(Date internacao) {
        this.internacao = internacao;
    }

    public Date getInicioSintomas() {
        return inicioSintomas;
    }

    public void setInicioSintomas(Date inicioSintomas) {
        this.inicioSintomas = inicioSintomas;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Sintomas getSintomas() {
        return sintomas;
    }
    
    public boolean foiAObito() {
    	return this.foiAObito;
    }
    
    public void setFoiAObito(boolean foiAObito) {
    	this.foiAObito=foiAObito;
    }
}