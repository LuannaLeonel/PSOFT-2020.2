package com.ufcg.psoft.coronavirusbrasil.model;

import com.ufcg.psoft.coronavirusbrasil.DTO.EnderecoDTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class LaboratorioTestes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    private Integer cep;

    private Long cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExameCovid> exameCovidPositivos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExameCovid> exameCovidNegativos;

    public LaboratorioTestes(String nome, EnderecoDTO endereco, Integer cep, Long cnpj) {
        this.nome = nome;
        this.endereco = new Endereco(endereco);
        this.cep = cep;
        this.cnpj = cnpj;
        this.exameCovidPositivos = new ArrayList<>();
        this.exameCovidNegativos = new ArrayList<>();
    }

    public LaboratorioTestes() {
    }

    public List<ExameCovid> getExameCovidPositivos() {
        return exameCovidPositivos;
    }

    public void setExameCovidPositivos(ArrayList<ExameCovid> exameCovidList) {
        this.exameCovidPositivos = exameCovidList;
    }

    public void adicionaExameCovid(ExameCovid exameCovid) {
        if (exameCovid.isCovid()) {
            this.exameCovidPositivos.add(exameCovid);
        } else this.exameCovidNegativos.add(exameCovid);
    }

    public void setExameCovidPositivos(List<ExameCovid> exameCovidPositivos) {
        this.exameCovidPositivos = exameCovidPositivos;
    }

    public List<ExameCovid> getExameCovidNegativos() {
        return exameCovidNegativos;
    }

    public void setExameCovidNegativos(List<ExameCovid> exameCovidNegativos) {
        this.exameCovidNegativos = exameCovidNegativos;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }
}