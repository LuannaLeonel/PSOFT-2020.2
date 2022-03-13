package com.ufcg.psoft.coronavirusbrasil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.coronavirusbrasil.DTO.ExameDTO;
import com.ufcg.psoft.coronavirusbrasil.DTO.LaboratorioTestesDTO;
import com.ufcg.psoft.coronavirusbrasil.erro.ErroLaboratorio;
import com.ufcg.psoft.coronavirusbrasil.model.ExameCovid;
import com.ufcg.psoft.coronavirusbrasil.model.LaboratorioTestes;
import com.ufcg.psoft.coronavirusbrasil.model.Usuario;
import com.ufcg.psoft.coronavirusbrasil.service.LaboratorioTestesService;
import com.ufcg.psoft.coronavirusbrasil.service.LoginService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LaboratorioApiController {

    @Autowired
    LaboratorioTestesService laboratorioTestesService;

    @Autowired
    LoginService loginService;

    @PreAuthorize("hasRole('ROLE_LABORATORIO')")
    @RequestMapping(value = "/laboratorio/teste/", method = RequestMethod.POST)
    public ResponseEntity<?> cadastraTeste(@RequestHeader String Authorization, @RequestBody ExameDTO exameDTO) {

        Usuario usuario = loginService.getAuthenticationIdByToken(Authorization);

        Long idLaboratorio = laboratorioTestesService.getIdLaboratorioByUsuario(usuario);

        Optional<LaboratorioTestes> laboratorioTestesOptional = this.laboratorioTestesService.getLaboratorioById(idLaboratorio);

        if (laboratorioTestesOptional.isEmpty()) {
            return ErroLaboratorio.erroLaboratorioNaoEnconrtrado();
        }

        LaboratorioTestes laboratorioTestes = laboratorioTestesOptional.get();
        ExameCovid exameCovid = this.laboratorioTestesService.cadastraResultadoExame(laboratorioTestes, exameDTO);
        return new ResponseEntity<>(exameCovid, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/laboratorio/cadastro", method = RequestMethod.POST)
    public ResponseEntity<?> cadastraLaboratorio(@RequestHeader String Authorization, @RequestBody LaboratorioTestesDTO laboratorioTestesDTO) {

        LaboratorioTestes laboratorioTestes = this.laboratorioTestesService.criaLaboratorio(laboratorioTestesDTO);
        if (laboratorioTestes == null) {
            return ErroLaboratorio.erroLaboratorioNaoEnconrtrado();
        }
        return new ResponseEntity<>(laboratorioTestes, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/laboratorio/atualizar/{cnpj}", method = RequestMethod.POST)
    public ResponseEntity<?> atualizaLaboratorio(@RequestHeader String Authorization, @RequestBody LaboratorioTestesDTO laboratorioTestesDTO, @PathVariable Long cnpj) {

        Optional<LaboratorioTestes> laboratorioTestesOptional = this.laboratorioTestesService.getLaboratorioByCnpj(cnpj);
        if (laboratorioTestesOptional.isEmpty()) {
            return ErroLaboratorio.erroLaboratorioNaoEnconrtrado();
        }

        LaboratorioTestes laboratorioTestes = laboratorioTestesOptional.get();

        return new ResponseEntity<>(this.laboratorioTestesService.atualizaLaboratorio(laboratorioTestesDTO, laboratorioTestes), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/laboratorio/", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestHeader String Authorization) {

        List<LaboratorioTestes> laboratorioTestesList = this.laboratorioTestesService.listarLaboratorios();

        return new ResponseEntity<>(laboratorioTestesList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/laboratorio/{cnpj}", method = RequestMethod.POST)
    public ResponseEntity<?> getLaboratorio(@RequestHeader String Authorization, @PathVariable Long cnpj) {

        Optional<LaboratorioTestes> laboratorioTestesOptional = this.laboratorioTestesService.getLaboratorioByCnpj(cnpj);
        if (laboratorioTestesOptional.isEmpty()) {
            return ErroLaboratorio.erroLaboratorioNaoEnconrtrado();
        }

        LaboratorioTestes laboratorioTestes = laboratorioTestesOptional.get();
        return new ResponseEntity<>(laboratorioTestes, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/laboratorio/{cnpj}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaLaboratorio(@RequestHeader String Authorization, @PathVariable Long cnpj) {

        Optional<LaboratorioTestes> laboratorioTestesOptional = this.laboratorioTestesService.getLaboratorioByCnpj(cnpj);
        if (laboratorioTestesOptional.isEmpty()) {
            return ErroLaboratorio.erroLaboratorioNaoEnconrtrado();
        }

        LaboratorioTestes laboratorioTestes = laboratorioTestesOptional.get();
        this.laboratorioTestesService.removerLaboratorioCadastrado(laboratorioTestes);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
