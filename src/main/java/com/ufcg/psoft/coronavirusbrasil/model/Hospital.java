package com.ufcg.psoft.coronavirusbrasil.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ufcg.psoft.coronavirusbrasil.DTO.EnderecoDTO;
import com.ufcg.psoft.coronavirusbrasil.DTO.HospitalDTO;

@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Long cep;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    private Long cnpj;

    private int qtdLeitosEnfermaria;

    private int qtdLeitosUTI;

    private int qtdObitos;

    private int casosRecuperados;

    private int totalCasosConfirmados;

    private int ocupacaoLeitos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paciente> obitos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paciente> recuperados;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paciente> leitosUTI;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paciente> leitosEnfermaria;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paciente> pacientesCovidPositivo;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Hospital() {
    }

    public Hospital(HospitalDTO hospitalDTO) {
        this.nome = hospitalDTO.getNome();
        this.cnpj = hospitalDTO.getCNPJ();
        this.cep = hospitalDTO.getCep();
        this.endereco = new Endereco(hospitalDTO.getEndereço());
        this.qtdLeitosEnfermaria = hospitalDTO.getQtdLeitosEnfermaria();
        this.qtdLeitosUTI = hospitalDTO.getQtdLeitosUTI();
        this.casosRecuperados = hospitalDTO.getCasosRecuperados();
        this.ocupacaoLeitos = hospitalDTO.getOcupacaoLeitos();
        this.totalCasosConfirmados = hospitalDTO.getTotalCasosConfirmados();
        this.recuperados = new ArrayList<Paciente>(casosRecuperados);
        this.obitos = new ArrayList<Paciente>(qtdObitos);
        this.leitosEnfermaria = new ArrayList<Paciente>(qtdLeitosEnfermaria);
        this.leitosUTI = new ArrayList<Paciente>(qtdLeitosUTI);
        this.pacientesCovidPositivo = new ArrayList<>();
    }

    public Hospital(String nome, Long cnpj, Long cep, Endereco endereco,  int qtdLeitosEnfermaria, int qtdLeitosUTI,
                    int qtdObitos, int casosRecuperados, int ocupacaoLeitos, int covidPositivo,
                    int totalCasosConfirmados) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.cep = cep;
        this.endereco = endereco;
        this.qtdLeitosEnfermaria = qtdLeitosEnfermaria;
        this.qtdLeitosUTI = qtdLeitosUTI;
        this.qtdObitos = qtdObitos;
        this.casosRecuperados = casosRecuperados;
        this.ocupacaoLeitos = ocupacaoLeitos;
        this.totalCasosConfirmados = totalCasosConfirmados;
        this.recuperados = new ArrayList<Paciente>(casosRecuperados);
        this.obitos = new ArrayList<Paciente>(qtdObitos);
        this.leitosEnfermaria = new ArrayList<Paciente>(qtdLeitosEnfermaria);
        this.leitosUTI = new ArrayList<Paciente>(qtdLeitosUTI);
        this.pacientesCovidPositivo = new ArrayList<>(covidPositivo);
    }

    public int getTotalCasosConfirmados() {
        return totalCasosConfirmados;
    }

    public void setTotalCasosConfirmados(int totalCasosConfirmados) {
        this.totalCasosConfirmados = totalCasosConfirmados;
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

    public List<Paciente> getObitos() {
        return obitos;
    }

    public int getCasosRecuperados() {
        return recuperados.size();
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void atualizaLeitosEnfermaria(int qtdLeitos) {
        this.qtdLeitosEnfermaria += qtdLeitos;
    }

    public void atualizaLeitosUTI(int qtdLeitos) {
        this.qtdLeitosUTI += qtdLeitos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCep(Long cep) {
        this.cep = cep;
    }

    public Long getCep() {
        return this.cep;
    }

    public void addPacienteRecuperados(Paciente paciente) {
        this.recuperados.add(paciente);
    }

    public void addPacienteLeitosUTI(Paciente paciente) {
        if (paciente.isCovid()) {
            this.pacientesCovidPositivo.add(paciente);
            this.totalCasosConfirmados++;
        }
        this.leitosUTI.add(paciente);
    }

    public void addPacinteLeitosEnfermaria(Paciente paciente) {
        if (paciente.isCovid()) {
            this.pacientesCovidPositivo.add(paciente);
            this.totalCasosConfirmados++;
        }
        this.leitosEnfermaria.add(paciente);
    }

    public void addPacienteObitos(Paciente paciente) {
        this.obitos.add(paciente);
        paciente.setFoiAObito(true);
        this.qtdObitos++;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public int getQtdObitos() {
        return qtdObitos;
    }

    public void setQtdObitos(int qtdObitos) {
        this.qtdObitos = qtdObitos;
    }

    public void setObitos(List<Paciente> obitos) {
        this.obitos = obitos;
    }

    public List<Paciente> getRecuperados() {
        return recuperados;
    }

    public void setRecuperados(List<Paciente> recuperados) {
        this.recuperados = recuperados;
    }

    public List<Paciente> getLeitosUTI() {
        return leitosUTI;
    }

    public void setLeitosUTI(List<Paciente> leitosUTI) {
        this.leitosUTI = leitosUTI;
    }

    public List<Paciente> getLeitosEnfermaria() {
        return leitosEnfermaria;
    }

    public void setLeitosEnfermaria(List<Paciente> leitosEnfermaria) {
        this.leitosEnfermaria = leitosEnfermaria;
    }

    public List<Paciente> getPacientesCovidPositivo() {
        return pacientesCovidPositivo;
    }
    
    public int getNumeroLeitosUTIOcupados() {
    	return this.leitosUTI.size();
    }
    
    public int getNumerosLeitosEnfermariaOcupados() {
    	return this.leitosEnfermaria.size();
    }

    public void setPacientesCovidPositivo(List<Paciente> pacientesCovidPositivo) {
        this.pacientesCovidPositivo = pacientesCovidPositivo;
    }
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = new Endereco(endereco);
    }

    public void retirarPaciente(Paciente paciente) {
        for (Paciente p : leitosEnfermaria) {
            if (p.equals(paciente)) {
                leitosEnfermaria.remove(paciente);
                return;
            }
        }

        for (Paciente p : leitosUTI) {
            if (p.equals(paciente)) {
                leitosUTI.remove(paciente);
                return;
            }
        };

        for (Paciente p : leitosUTI) {
            if (p.equals(paciente)) {
                pacientesCovidPositivo.remove(paciente);
                return;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Hospital hospital = (Hospital) o;
        return qtdLeitosEnfermaria == hospital.qtdLeitosEnfermaria && qtdLeitosUTI == hospital.qtdLeitosUTI
                && qtdObitos == hospital.qtdObitos && casosRecuperados == hospital.casosRecuperados
                && ocupacaoLeitos == hospital.ocupacaoLeitos && nome.equals(hospital.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, qtdLeitosEnfermaria, qtdLeitosUTI, qtdObitos, casosRecuperados,
                ocupacaoLeitos);
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cep=" + cep +
                ", cnpj=" + cnpj +
                ", qtdLeitosEnfermaria=" + qtdLeitosEnfermaria +
                ", qtdLeitosUTI=" + qtdLeitosUTI +
                ", qtdObitos=" + qtdObitos +
                ", casosRecuperados=" + casosRecuperados +
                ", totalCasosConfirmados=" + totalCasosConfirmados +
                ", ocupacaoLeitos=" + ocupacaoLeitos +
                ", obitos=" + obitos +
                ", recuperados=" + recuperados +
                ", leitosUTI=" + leitosUTI +
                ", leitosEnfermaria=" + leitosEnfermaria +
                ", pacientesCovidPositivo=" + pacientesCovidPositivo +
                '}';
    }
}
