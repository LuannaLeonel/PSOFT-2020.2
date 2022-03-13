package com.ufcg.psoft.coronavirusbrasil.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.coronavirusbrasil.DTO.ExameDTO;
import com.ufcg.psoft.coronavirusbrasil.DTO.LaboratorioTestesDTO;
import com.ufcg.psoft.coronavirusbrasil.enums.AreaDeBusca;
import com.ufcg.psoft.coronavirusbrasil.model.ExameCovid;
import com.ufcg.psoft.coronavirusbrasil.model.LaboratorioTestes;
import com.ufcg.psoft.coronavirusbrasil.model.Usuario;

public interface LaboratorioTestesService {

    public Optional<LaboratorioTestes> getLaboratorioById(Long id);

    public Optional<LaboratorioTestes> getLaboratorioByCnpj(Long cnpj);
    
    public Long getIdLaboratorioByUsuario(Usuario usuario);

    public void removerLaboratorioCadastrado(LaboratorioTestes laboratorioTestes);

    public void salvarLaboratorioCadastrado(LaboratorioTestes laboratorioTestes);

    public List<LaboratorioTestes> listarLaboratorios();

    public LaboratorioTestes criaLaboratorio(LaboratorioTestesDTO laboratorioTestesDTO);

    public LaboratorioTestes atualizaLaboratorio(LaboratorioTestesDTO laboratorioTestesDTO, LaboratorioTestes laboratorioTestes);

    public ExameCovid cadastraResultadoExame(LaboratorioTestes laboratorioTestes, ExameDTO exameDTO);

    public int relatorioCasosConfirmadosAcumulados();

    public int casosNovos24h();

    public int relatorioCasosConfirmadosAcumulados(AreaDeBusca areaDeBusca, String regiao);
}
