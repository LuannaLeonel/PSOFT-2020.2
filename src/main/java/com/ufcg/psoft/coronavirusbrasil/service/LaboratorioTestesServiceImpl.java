package com.ufcg.psoft.coronavirusbrasil.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.coronavirusbrasil.DTO.ExameDTO;
import com.ufcg.psoft.coronavirusbrasil.enums.AreaDeBusca;
import com.ufcg.psoft.coronavirusbrasil.model.*;
import com.ufcg.psoft.coronavirusbrasil.repository.TestesRepository;

import org.springframework.beans.factory.annotation.Autowired;

import com.ufcg.psoft.coronavirusbrasil.DTO.LaboratorioTestesDTO;
import com.ufcg.psoft.coronavirusbrasil.repository.LaboratorioTestesRepository;
import org.springframework.stereotype.Service;

@Service
public class LaboratorioTestesServiceImpl implements LaboratorioTestesService {

    @Autowired
    private LaboratorioTestesRepository laboratorioTestesRepository;

    @Autowired
    private TestesRepository testesRepository;

    @Override
    public Optional<LaboratorioTestes> getLaboratorioById(Long id) {
        return laboratorioTestesRepository.findById(id);
    }

    @Override
    public Optional<LaboratorioTestes> getLaboratorioByCnpj(Long cnpj) {
        return laboratorioTestesRepository.findByCnpj(cnpj);
    }

    @Override
    public void removerLaboratorioCadastrado(LaboratorioTestes laboratorioTestes) {
        laboratorioTestesRepository.delete(laboratorioTestes);
    }

    @Override
    public void salvarLaboratorioCadastrado(LaboratorioTestes laboratorioTestes) {
        laboratorioTestesRepository.save(laboratorioTestes);
    }

    @Override
    public List<LaboratorioTestes> listarLaboratorios() {
        return laboratorioTestesRepository.findAll();
    }

    @Override
    public LaboratorioTestes criaLaboratorio(LaboratorioTestesDTO laboratorioTestesDTO) {
        if (laboratorioTestesRepository.existsByNome(laboratorioTestesDTO.getNome())) {
            return null;
        }

        LaboratorioTestes laboratorioTestes = new LaboratorioTestes(laboratorioTestesDTO.getNome(),
                laboratorioTestesDTO.getEndereco(), laboratorioTestesDTO.getCep(), laboratorioTestesDTO.getCnpj());
        salvarLaboratorioCadastrado(laboratorioTestes);

        return laboratorioTestes;
    }

    @Override
    public LaboratorioTestes atualizaLaboratorio(LaboratorioTestesDTO laboratorioTestesDTO, LaboratorioTestes laboratorioTestes) {
        laboratorioTestes.setNome(laboratorioTestesDTO.getNome());
        laboratorioTestes.setEndereco(new Endereco(laboratorioTestesDTO.getEndereco()));
        laboratorioTestes.setCep(laboratorioTestesDTO.getCep());
        laboratorioTestes.setCnpj(laboratorioTestesDTO.getCnpj());

        salvarLaboratorioCadastrado(laboratorioTestes);

        return laboratorioTestes;
    }

    public ExameCovid cadastraResultadoExame(LaboratorioTestes laboratorioTestes, ExameDTO exameDTO) {
        ExameCovid exameCovid = fromDTOToExame(exameDTO);
        laboratorioTestes.adicionaExameCovid(exameCovid);
        testesRepository.save(exameCovid);
        laboratorioTestesRepository.save(laboratorioTestes);
        return exameCovid;
    }

    public ExameCovid fromDTOToExame(ExameDTO exameDTO) {
        return new ExameCovid(exameDTO.getCpfDoPaciente(), exameDTO.getTipoTeste(), exameDTO.isCovid(), exameDTO.getData());
    }

    @Override
    public Long getIdLaboratorioByUsuario(Usuario usuario) {
        return laboratorioTestesRepository.findByUsuario(usuario).get().getId();
    }

    public int relatorioCasosConfirmadosAcumulados() {
        int casosConfirmadosAcumulados = 0;
        for (LaboratorioTestes l : this.laboratorioTestesRepository.findAll()) {
            casosConfirmadosAcumulados += l.getExameCovidPositivos().size();
        }
        return casosConfirmadosAcumulados;
    }

    public int relatorioCasosConfirmadosAcumulados(AreaDeBusca area, String local) {
        int casosConfirmadosAcumulados = 0;
        for (LaboratorioTestes l : this.laboratorioTestesRepository.findAll()) {
            if (area.equals(AreaDeBusca.CIDADE)){
                if (l.getEndereco().getCidade().equals(local)){
                    casosConfirmadosAcumulados += l.getExameCovidPositivos().size();
                }
            } else if(area.equals(AreaDeBusca.ESTADO)){
                if(l.getEndereco().getEstado().equals(local)){
                    casosConfirmadosAcumulados += l.getExameCovidPositivos().size();
                }
            } else if (area.equals(AreaDeBusca.REGIAO)){
                if(l.getEndereco().getRegiao().equals(local)){
                    casosConfirmadosAcumulados += l.getExameCovidPositivos().size();
                }
            }
        }
        return casosConfirmadosAcumulados;
    }

    public int casosNovos24h() {
        int casosConfirmados24h = 0;

        for (LaboratorioTestes l : this.laboratorioTestesRepository.findAll()) {
            for (ExameCovid e : l.getExameCovidPositivos()) {
                Instant agora = Instant.now();
                boolean ultimas24h =
                        (!e.getData().toInstant().isBefore(agora.minus(24, ChronoUnit.HOURS)))
                                && (e.getData().toInstant().isBefore(agora));
                if (ultimas24h) casosConfirmados24h += 1;
            }
        }
        return casosConfirmados24h;
    }
}