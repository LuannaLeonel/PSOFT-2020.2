package com.ufcg.psoft.coronavirusbrasil.DTO;

public class LaboratorioTestesDTO {

    private String nome;

    private EnderecoDTO endereco;

    private Integer cep;

    private Long cnpj;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public Long getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }
}