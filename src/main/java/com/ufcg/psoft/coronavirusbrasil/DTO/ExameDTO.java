package com.ufcg.psoft.coronavirusbrasil.DTO;

import com.ufcg.psoft.coronavirusbrasil.enums.TipoTeste;

import java.util.Date;

public class ExameDTO {

    private Long cpfDoPaciente;

    private TipoTeste tipoTeste;

    private boolean isCovid;

    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getCpfDoPaciente() {
        return cpfDoPaciente;
    }

    public void setCpfDoPaciente(Long cpfDoPaciente) {
        this.cpfDoPaciente = cpfDoPaciente;
    }

    public TipoTeste getTipoTeste() {
        return tipoTeste;
    }

    public void setTipoTeste(TipoTeste tipoTeste) {
        this.tipoTeste = tipoTeste;
    }

    public boolean isCovid() {
        return isCovid;
    }

    public void setCovid(boolean covid) {
        isCovid = covid;
    }
}
