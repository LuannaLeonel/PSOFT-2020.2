package com.ufcg.psoft.coronavirusbrasil.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.coronavirusbrasil.enums.AreaDeBusca;
import com.ufcg.psoft.coronavirusbrasil.enums.Sexo;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.psoft.coronavirusbrasil.DTO.FuncionarioDTO;
import com.ufcg.psoft.coronavirusbrasil.DTO.SintomasDTO;
import com.ufcg.psoft.coronavirusbrasil.erro.ErroFuncionario;
import com.ufcg.psoft.coronavirusbrasil.model.Funcionario;
import com.ufcg.psoft.coronavirusbrasil.model.Paciente;
import com.ufcg.psoft.coronavirusbrasil.model.Sintomas;
import com.ufcg.psoft.coronavirusbrasil.service.FuncionarioService;
import com.ufcg.psoft.coronavirusbrasil.service.LoginService;;

/**
 * Controller responsável pela gerência de funcionários
 */
@RestController
@RequestMapping("/api/funcionario")
@CrossOrigin
public class FuncionarioApiController {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    LoginService loginService;

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listarFuncionario(@RequestHeader String Authorization) {

        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();

        if (funcionarios.isEmpty()) {
            return ErroFuncionario.erroListaFuncionariosVazia();
        }

        return new ResponseEntity<>(funcionarios, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/{cpf}", method = RequestMethod.GET)
    public ResponseEntity<?> getFuncionario(@RequestHeader String Authorization, @PathVariable("cpf") long cpf) {

        Optional<Funcionario> optionalFuncionario = funcionarioService.getFuncionarioByCPF(cpf);

        if (optionalFuncionario.isEmpty()) {
            return ErroFuncionario.erroFuncionarioCpfNaoEnconrtrado(cpf);
        }

        return new ResponseEntity<>(optionalFuncionario.get(), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> criarFuncionario(@RequestHeader String Authorization, @RequestBody FuncionarioDTO funcionarioDTO, UriComponentsBuilder ucBuilder) {

        Optional<Funcionario> optionalFuncionario = funcionarioService.getFuncionarioByCPF(funcionarioDTO.getCPF());

        if (!optionalFuncionario.isEmpty()) {
            return ErroFuncionario.erroFuncionarioJaCadastrado(funcionarioDTO);
        }

        Funcionario funcionario = funcionarioService.criaFuncionario(funcionarioDTO);

        return new ResponseEntity<>(funcionario, HttpStatus.CREATED);

    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarFuncionario(@RequestHeader String Authorization, @RequestBody FuncionarioDTO funcionarioDTO, @PathVariable("id") long id) {

        Optional<Funcionario> optionalFuncionario = funcionarioService.getFuncionarioById(id);

        if (optionalFuncionario.isEmpty()) {
            return ErroFuncionario.erroFuncionarioIdNaoEnconrtrado(id);
        }

        return new ResponseEntity<>(funcionarioService.atualizaFuncionario(funcionarioDTO, optionalFuncionario.get()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/{cpf}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removerFuncionario(@RequestHeader String Authorization, @PathVariable("cpf") long cpf) {

        Optional<Funcionario> optionalFuncionario = funcionarioService.getFuncionarioByCPF(cpf);

        if (optionalFuncionario.isEmpty()) {
            return ErroFuncionario.erroFuncionarioCpfNaoEnconrtrado(cpf);
        }

        funcionarioService.removeFuncionario(optionalFuncionario.get());

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_FUNCIONARIO')")
    @RequestMapping(value = "/relatorio/semana", method = RequestMethod.GET)
    public ResponseEntity<?> relatorioSemana(@RequestHeader String Authorization, @RequestParam String semana, @RequestParam String nivelRelatorio, @RequestParam String regiao) {

        SimpleDateFormat smdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Paciente> paciente = null;

        try {
            paciente = funcionarioService.getPacientesSemana(smdf.parse(semana), nivelRelatorio, regiao);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<List<Paciente>>(paciente, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_FUNCIONARIO')")
    @RequestMapping(value = "/relatorio/sintomas", method = RequestMethod.PUT)
    public ResponseEntity<?> relatorioSintomas(@RequestHeader String Authorization, @RequestBody SintomasDTO sintomasDTO, @RequestParam String nivelRelatorio, @RequestParam String regiao) {

        Sintomas sintomas = funcionarioService.criaSintomas(sintomasDTO);
        List<Paciente> paciente = null;

        paciente = funcionarioService.getPacientesSintomas(sintomas, nivelRelatorio, regiao);

        return new ResponseEntity<List<Paciente>>(paciente, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_FUNCIONARIO')")
    @RequestMapping(value = "/relatorio/obitos", method = RequestMethod.GET)
    public ResponseEntity<?> relatorioObitos(@RequestHeader String Authorization, @RequestParam int faixaEtariaInicio, @RequestParam int faixaEtariaFim, @RequestParam Sexo sexo, @RequestParam AreaDeBusca areaDeBusca, @RequestParam String nomeArea) {
        String relatorio = funcionarioService.relatorioMortesCovid(faixaEtariaInicio, faixaEtariaFim, sexo, areaDeBusca, nomeArea);

        return new ResponseEntity<String>(relatorio, HttpStatus.OK);
    }
    
	@PreAuthorize("hasRole('ROLE_FUNCIONARIO')")
	@RequestMapping(value = "/relatorio/obito/sintomas", method = RequestMethod.PUT)
	public ResponseEntity<?> relatorioObitosPorTipoDeSintomas(@RequestHeader String Authorization, @RequestBody SintomasDTO sintomasDTO, @RequestParam("Cidade/Estado") String nivelRelatorio, @RequestParam String regiao) {
		
        Sintomas sintomas = funcionarioService.criaSintomas(sintomasDTO);		
		List<Paciente> obitos = null;
		
		obitos = funcionarioService.getPacientesObitoSintomas(sintomas, nivelRelatorio, regiao);
		
		return new ResponseEntity<List<Paciente>>(obitos, HttpStatus.OK);	
	}
    @PreAuthorize("hasRole('ROLE_FUNCIONARIO')")
    @RequestMapping(value = "/relatorio/hospitalizacoes", method = RequestMethod.PUT)
    public ResponseEntity<?> relatorioHopitalizacoesCovid(@RequestHeader String Authorization, @RequestParam int faixaEtariaInicio, @RequestParam int faixaEtariaFim, @RequestParam Sexo sexo, @RequestParam AreaDeBusca areaDeBusca, @RequestParam String nomeArea) {
        String relatorio = funcionarioService.relatorioHospitalizacoesCovid(faixaEtariaInicio, faixaEtariaFim, sexo, areaDeBusca, nomeArea);

        return new ResponseEntity<String>(relatorio, HttpStatus.OK);
    }

}