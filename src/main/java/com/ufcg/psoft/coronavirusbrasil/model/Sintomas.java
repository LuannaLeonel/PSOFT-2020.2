package com.ufcg.psoft.coronavirusbrasil.model;

import com.ufcg.psoft.coronavirusbrasil.DTO.SintomasDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Sintomas {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean febre;

    private boolean tosseSeca;

    private boolean cansaco;

    private boolean doresCorpo;

    private boolean dorGargante;

    private boolean diarreia;

    private boolean conjutivite;

    private boolean dorCabeca;

    private boolean perdaPaladar;

    private boolean perdaOlfato;

    private boolean erupcaoCutanea;

    private boolean descoloracaoDedos;

    private boolean faltaDeAr;

    private boolean dorPeito;

    private boolean perdaMovimento;
    
    public Sintomas() {}

    public Sintomas(SintomasDTO sintomasDTO) {
        this.febre = sintomasDTO.isFebre();
        this.tosseSeca = sintomasDTO.isTosseSeca();
        this.cansaco = sintomasDTO.isCansaco();
        this.doresCorpo = sintomasDTO.isDoresCorpo();
        this.dorGargante = sintomasDTO.isDorGargante();
        this.diarreia = sintomasDTO.isDiarreia();
        this.conjutivite = sintomasDTO.isConjutivite();
        this.dorCabeca = sintomasDTO.isDorCabeca();
        this.perdaPaladar = sintomasDTO.isPerdaPaladar();
        this.perdaOlfato = sintomasDTO.isPerdaOlfato();
        this.erupcaoCutanea = sintomasDTO.isErupcaoCutanea();
        this.descoloracaoDedos = sintomasDTO.isDescoloracaoDedos();
        this.faltaDeAr = sintomasDTO.isFaltaDeAr();
        this.dorPeito = sintomasDTO.isDorPeito();
        this.perdaMovimento = sintomasDTO.isPerdaMovimento();
    }

    public Sintomas(boolean febre, boolean tosseSeca, boolean cansaco, boolean doresCorpo,
                    boolean dorGargante, boolean diarreia, boolean conjutivite, boolean dorCabeca,
                    boolean perdaPaladar, boolean perdaOlfato, boolean erupcaoCutanea, boolean descoloracaoDedos,
                    boolean faltaDeAr, boolean dorPeito, boolean perdaMovimento) {
        this.febre = febre;
        this.tosseSeca = tosseSeca;
        this.cansaco = cansaco;
        this.doresCorpo = doresCorpo;
        this.dorGargante = dorGargante;
        this.diarreia = diarreia;
        this.conjutivite = conjutivite;
        this.dorCabeca = dorCabeca;
        this.perdaPaladar = perdaPaladar;
        this.perdaOlfato = perdaOlfato;
        this.erupcaoCutanea = erupcaoCutanea;
        this.descoloracaoDedos = descoloracaoDedos;
        this.faltaDeAr = faltaDeAr;
        this.dorPeito = dorPeito;
        this.perdaMovimento = perdaMovimento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean isFebre() {
        return febre;
    }

    public void setFebre(boolean febre) {
        this.febre = febre;
    }

    public boolean isTosseSeca() {
        return tosseSeca;
    }

    public void setTosseSeca(boolean tosseSeca) {
        this.tosseSeca = tosseSeca;
    }

    public boolean isCansaco() {
        return cansaco;
    }

    public void setCansaco(boolean cansaco) {
        this.cansaco = cansaco;
    }

    public boolean isDoresCorpo() {
        return doresCorpo;
    }

    public void setDoresCorpo(boolean doresCorpo) {
        this.doresCorpo = doresCorpo;
    }

    public boolean isDorGargante() {
        return dorGargante;
    }

    public void setDorGargante(boolean dorGargante) {
        this.dorGargante = dorGargante;
    }

    public boolean isDiarreia() {
        return diarreia;
    }

    public void setDiarreia(boolean diarreia) {
        this.diarreia = diarreia;
    }

    public boolean isConjutivite() {
        return conjutivite;
    }

    public void setConjutivite(boolean conjutivite) {
        this.conjutivite = conjutivite;
    }

    public boolean isDorCabeca() {
        return dorCabeca;
    }

    public void setDorCabeca(boolean dorCabeca) {
        this.dorCabeca = dorCabeca;
    }

    public boolean isPerdaPaladar() {
        return perdaPaladar;
    }

    public void setPerdaPaladar(boolean perdaPaladar) {
        this.perdaPaladar = perdaPaladar;
    }

    public boolean isPerdaOlfato() {
        return perdaOlfato;
    }

    public void setPerdaOlfato(boolean perdaOlfato) {
        this.perdaOlfato = perdaOlfato;
    }

    public boolean isErupcaoCutanea() {
        return erupcaoCutanea;
    }

    public void setErupcaoCutanea(boolean erupcaoCutanea) {
        this.erupcaoCutanea = erupcaoCutanea;
    }

    public boolean isDescoloracaoDedos() {
        return descoloracaoDedos;
    }

    public void setDescoloracaoDedos(boolean descoloracaoDedos) {
        this.descoloracaoDedos = descoloracaoDedos;
    }

    public boolean isFaltaDeAr() {
        return faltaDeAr;
    }

    public void setFaltaDeAr(boolean faltaDeAr) {
        this.faltaDeAr = faltaDeAr;
    }

    public boolean isDorPeito() {
        return dorPeito;
    }

    public void setDorPeito(boolean dorPeito) {
        this.dorPeito = dorPeito;
    }

    public boolean isPerdaMovimento() {
        return perdaMovimento;
    }

    public void setPerdaMovimento(boolean perdaMovimento) {
        this.perdaMovimento = perdaMovimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sintomas sintomas = (Sintomas) o;
        return febre == sintomas.febre
                && tosseSeca == sintomas.tosseSeca
                && cansaco == sintomas.cansaco
                && doresCorpo == sintomas.doresCorpo
                && dorGargante == sintomas.dorGargante
                && diarreia == sintomas.diarreia
                && conjutivite == sintomas.conjutivite
                && dorCabeca == sintomas.dorCabeca
                && perdaPaladar == sintomas.perdaPaladar
                && perdaOlfato == sintomas.perdaOlfato
                && erupcaoCutanea == sintomas.erupcaoCutanea
                && descoloracaoDedos == sintomas.descoloracaoDedos
                && faltaDeAr == sintomas.faltaDeAr
                && dorPeito == sintomas.dorPeito
                && perdaMovimento == sintomas.perdaMovimento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, febre, tosseSeca, cansaco,
                doresCorpo, dorGargante, diarreia, conjutivite,
                dorCabeca, perdaPaladar, perdaOlfato, erupcaoCutanea,
                descoloracaoDedos, faltaDeAr, dorPeito, perdaMovimento);
    }
}