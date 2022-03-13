package com.ufcg.psoft.coronavirusbrasil.DTO;

import com.ufcg.psoft.coronavirusbrasil.enums.Sexo;

import java.util.Date;

public class PacienteDTO {

    private String nome;

    private Sexo sexo;

    private Date nascimento;

    private EnderecoDTO enderecoDTO;

    private Date internacao;

    private Date inicioSintomas;

    private Long cpf;

    private boolean isCovid;

    private SintomasDTO sintomasDTO;

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

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public Date getInicioSintomas() {
        return inicioSintomas;
    }

    public void setInicioSintomas(Date inicioSintomas) {
        this.inicioSintomas = inicioSintomas;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public SintomasDTO getSintomasDTO() {
        return sintomasDTO;
    }

    public void setSintomasDTO(SintomasDTO sintomasDTO) {
        this.sintomasDTO = sintomasDTO;
    }

    public Date getInternacao() {
        return internacao;
    }

    public void setInternacao(Date internacao) {
        this.internacao = internacao;
    }
}
