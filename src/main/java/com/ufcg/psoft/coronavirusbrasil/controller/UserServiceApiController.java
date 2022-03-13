package com.ufcg.psoft.coronavirusbrasil.controller;

import java.util.List;
import java.util.Collections;

import com.ufcg.psoft.coronavirusbrasil.enums.AreaDeBusca;
import com.ufcg.psoft.coronavirusbrasil.service.LaboratorioTestesService;
import com.ufcg.psoft.coronavirusbrasil.util.GeraInformacoesGerais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ufcg.psoft.coronavirusbrasil.comparator.ComparadorHospital;
import com.ufcg.psoft.coronavirusbrasil.comparator.ComparadorUnidadeSaude;

import com.ufcg.psoft.coronavirusbrasil.util.UnidadesProximas;

import com.ufcg.psoft.coronavirusbrasil.model.Hospital;
import com.ufcg.psoft.coronavirusbrasil.model.UnidadeSaude;
import com.ufcg.psoft.coronavirusbrasil.service.HospitalService;
import com.ufcg.psoft.coronavirusbrasil.service.UnidadeSaudeService;

/**
 * Controller responsável pela gerência dos serviços de usuário
 */
@RestController
@RequestMapping("/api/usuario")
@CrossOrigin
public class UserServiceApiController {

    @Autowired
    HospitalService hospitalService;

    @Autowired
    UnidadeSaudeService unidadeSaudeService;

    @Autowired
    LaboratorioTestesService laboratorioTestesService;

    @RequestMapping(value = "/unidades-proximas/{cep}", method = RequestMethod.GET)
    public ResponseEntity<?> getUnidadesProximas(@PathVariable("cep") long cep) {

        List<Hospital> hospitais = hospitalService.listarHospitais();
        List<UnidadeSaude> unidadesSaude = unidadeSaudeService.listarUnidadesSaude();

        ComparadorHospital comparadorHospital = new ComparadorHospital(cep);
        ComparadorUnidadeSaude comparadorUnidadeSaude = new ComparadorUnidadeSaude(cep);

        Collections.sort(hospitais, comparadorHospital);
        Collections.sort(unidadesSaude, comparadorUnidadeSaude);

        UnidadesProximas unidadesProximas = new UnidadesProximas(hospitais, unidadesSaude);

        String unidadesProximasString = unidadesProximas.toString();

        return new ResponseEntity<>(unidadesProximasString, HttpStatus.OK);
    }

    @RequestMapping(value = "/informacoes-gerais", method = RequestMethod.GET)
    public ResponseEntity<?> getUnidadesProximas() {

        int casosConfirmadosAcumulados = laboratorioTestesService.relatorioCasosConfirmadosAcumulados()
                + hospitalService.relatorioCasosCovid();
        int casosRecuperados = hospitalService.relatorioCasosRecuperados();
        int obitosConfirmados = hospitalService.calculaObitos();
        int casosNovos24h = laboratorioTestesService.casosNovos24h() +
                hospitalService.relatorioCasosCovid24H();

        GeraInformacoesGerais geraInformacoesGerais = new GeraInformacoesGerais();
        String intro = "Este é o relatório gerado com os dados mais atuais em " +
                " relação à covid-19 no Brasil: \n";

        String relatorio = intro + geraInformacoesGerais.geraInformacoesGerais(casosConfirmadosAcumulados,
                casosRecuperados, obitosConfirmados, casosNovos24h);

        return new ResponseEntity<>(relatorio, HttpStatus.OK);
    }
    @RequestMapping(value = "/informacoes-gerais-local", method = RequestMethod.GET)
    public ResponseEntity<?> getInformaçõesGeraisLocal(@RequestParam AreaDeBusca areaDeBusca, @RequestParam String regiao) {
        regiao = regiao.toUpperCase();

        GeraInformacoesGerais geraInformacoesGerais = new GeraInformacoesGerais();

        String relatorio = String.format("Este é o relatório gerado com os dados mais atuais em " +
                " relação à covid-19 em %s: \n",regiao);

        int casosConfirmadosAcumulados = laboratorioTestesService.relatorioCasosConfirmadosAcumulados(areaDeBusca,regiao)
                + hospitalService.relatorioCasosCovidArea(areaDeBusca,regiao);
        int casosRecuperados = hospitalService.relatorioCasosRecuperadosArea(areaDeBusca, regiao);
        int obitosConfirmados = hospitalService.calculaObitosArea(areaDeBusca,regiao);
        int casosNovos24h = hospitalService.relatorioCasosCovid24H(areaDeBusca,regiao);

        relatorio += geraInformacoesGerais.geraInformacoesGerais(casosConfirmadosAcumulados,
                casosRecuperados, obitosConfirmados, casosNovos24h);

        return new ResponseEntity<>(relatorio, HttpStatus.OK);
    }




}
