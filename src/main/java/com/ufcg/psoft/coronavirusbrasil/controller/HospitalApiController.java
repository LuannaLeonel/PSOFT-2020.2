package com.ufcg.psoft.coronavirusbrasil.controller;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.coronavirusbrasil.enums.TipoLeito;
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

import com.ufcg.psoft.coronavirusbrasil.DTO.HospitalDTO;
import com.ufcg.psoft.coronavirusbrasil.DTO.PacienteDTO;
import com.ufcg.psoft.coronavirusbrasil.erro.ErroHospital;
import com.ufcg.psoft.coronavirusbrasil.model.Hospital;
import com.ufcg.psoft.coronavirusbrasil.model.Paciente;
import com.ufcg.psoft.coronavirusbrasil.model.Usuario;
import com.ufcg.psoft.coronavirusbrasil.service.HospitalService;
import com.ufcg.psoft.coronavirusbrasil.service.LoginService;
import com.ufcg.psoft.coronavirusbrasil.service.UsuarioService;

/**
 * Controller responsável pela gerência de hospitais
 * 
 */
@RestController
@RequestMapping("/api/hospital")
@CrossOrigin
public class HospitalApiController {

    @Autowired
    HospitalService hospitalService;
    
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	LoginService loginService;
    
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarHospitais(@RequestHeader String Authorization) {
		
		List<Hospital> hospitais = hospitalService.listarHospitais();
		
		if (hospitais.isEmpty()) {
			return ErroHospital.erroListaHospitaisVazia();
		}
		
		return new ResponseEntity<>(hospitais.toString(), HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/{cnpj}", method = RequestMethod.GET)
	public ResponseEntity<?> getHospital(@RequestHeader String Authorization, @PathVariable("cnpj") long cnpj) {
		
		Optional<Hospital> optionalHospital = hospitalService.getHospitalByCNPJ(cnpj);
		
		if (optionalHospital.isEmpty()) {
			return ErroHospital.erroHospitalCnpjNaoEnconrtrado(cnpj);
		}
		
		return new ResponseEntity<>(optionalHospital.get().toString(), HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> criarHospital(@RequestHeader String Authorization, @RequestBody HospitalDTO hospitalDTO, UriComponentsBuilder ucBuilder) {

		Optional<Hospital> optionalHospital = hospitalService.getHospitalByCNPJ(hospitalDTO.getCNPJ());
		
		if (!optionalHospital.isEmpty()) {
			return ErroHospital.erroHospitalJaCadastrado(hospitalDTO);
		}
		
		Hospital hospital = hospitalService.criaHospital(hospitalDTO);
		hospitalService.salvarHospitalCadastrado(hospital);
		
		Usuario usuario = usuarioService.criarUsuarioHospital("HOSPITAL", hospitalDTO, hospital);
		usuarioService.salvarUsuario(usuario);
		
		return new ResponseEntity<>("Cadastro realizado com sucesso!", HttpStatus.CREATED);
		
	}
	
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarHospital(@RequestHeader String Authorization, @RequestBody HospitalDTO hospitalDTO, @PathVariable("id") long id) {

    	Optional<Hospital> optionalHospital = hospitalService.getHospitalById(id);
        
        if (optionalHospital.isEmpty()) {
            return ErroHospital.erroHospitalIdNaoEnconrtrado(id);
        }

        return new ResponseEntity<>(hospitalService.atualizaHospital(hospitalDTO, optionalHospital.get()), HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
	@RequestMapping(value = "/{cnpj}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerHospital(@RequestHeader String Authorization, @PathVariable("cnpj") long cnpj) {

		Optional<Hospital> optionalHospital = hospitalService.getHospitalByCNPJ(cnpj);
		
		if (optionalHospital.isEmpty()) {
			return ErroHospital.erroHospitalCnpjNaoEnconrtrado(cnpj);
		}
		
		hospitalService.removerHospitalCadastrado(optionalHospital.get());
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	@PreAuthorize("hasRole('ROLE_HOSPITAL')")
	@RequestMapping(value = "/atualizar/uti/", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizaQuantidadeLeitos(@RequestHeader String Authorization, @RequestParam int novaQtdDeLeitos, @RequestParam TipoLeito tipo){
		
		Usuario usuario = loginService.getAuthenticationIdByToken(Authorization);
		
		Long idHospital = hospitalService.getIdHospitalByUsuario(usuario);
		
		Optional<Hospital> optionalHospital = hospitalService.getHospitalById(idHospital);
		
		if(!optionalHospital.isPresent()) {
			return ErroHospital.erroHospitalIdNaoEnconrtrado(idHospital);
		}
		
		Hospital hospital = optionalHospital.get();
		if(!hospitalService.ehPossivelAdicionarLeito(hospital, novaQtdDeLeitos, tipo)) {
			return ErroHospital.erroImpossivelatualizarLeito();
		}
		
		hospitalService.atualizaLeitos(hospital, novaQtdDeLeitos, tipo);
		hospitalService.salvarHospitalCadastrado(hospital);
		
		return new ResponseEntity<String>("atualização realizada com sucesso!", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_HOSPITAL')")
	@RequestMapping(value = "/adicionarPaciente", method = RequestMethod.POST)
	public ResponseEntity<?> adicionaPacienteLeito(@RequestHeader String Authorization, @RequestBody PacienteDTO pacienteDTO, @RequestParam TipoLeito tipo){
		
		Usuario usuario = loginService.getAuthenticationIdByToken(Authorization);
		
		Long idHospital = hospitalService.getIdHospitalByUsuario(usuario);
		
		Optional<Hospital> optionalHospital = hospitalService.getHospitalById(idHospital);
		
		if(!optionalHospital.isPresent()) {
			return ErroHospital.erroHospitalIdNaoEnconrtrado(idHospital);
		}
		
		Hospital hospital = optionalHospital.get();

		Paciente paciente = hospitalService.criaPaciente(pacienteDTO);
		
		boolean leitosDisponiveis = hospitalService.verificaLeitoDisponivel(hospital, tipo);
		
		if(!leitosDisponiveis) {
			return ErroHospital.erroImpossivelAdicionarPacienteLeito();
		}
		
		hospitalService.adicionarPaciente(hospital, paciente, tipo);
		hospitalService.salvarHospitalCadastrado(hospital);
		
		return new ResponseEntity<String>("paciente adicionar com sucesso!", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_HOSPITAL')")
	@RequestMapping(value = "/adicionarPacienteObito", method = RequestMethod.PUT)
	public ResponseEntity<?> adicionaPacienteObito(@RequestHeader String Authorization, @RequestParam Long cpfPaciente){
		
		Usuario usuario = loginService.getAuthenticationIdByToken(Authorization);
		
		Long idHospital = hospitalService.getIdHospitalByUsuario(usuario);
		
		Optional<Hospital> hospitalOptional = hospitalService.getHospitalById(idHospital);
		
		if(!hospitalOptional.isPresent()) {
			return ErroHospital.erroHospitalIdNaoEnconrtrado(idHospital);
		}
		
		Hospital hospital = hospitalOptional.get();

		Optional<Paciente> optionalPaciente = hospitalService.getPacienteByCPF(cpfPaciente);
		if (optionalPaciente.isEmpty()) {
			return ErroHospital.erroHospitalPacienteNaoCadastrado(cpfPaciente);
		}
		Paciente paciente = optionalPaciente.get();
		
		hospitalService.adicionarPacienteObito(hospital, paciente);
		hospitalService.salvarHospitalCadastrado(hospital);
		
		return new ResponseEntity<String>("Cadastro do paciente que veio a obito realizada com sucesso!", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_HOSPITAL')")
	@RequestMapping(value = "/adicionarPacienteRecuperado", method = RequestMethod.PUT)
	public ResponseEntity<?> adicionaPacienterecuperado(@RequestHeader String Authorization, @RequestBody Long cpfPaciente){
		
		Usuario usuario = loginService.getAuthenticationIdByToken(Authorization);
		
		Long idHospital = hospitalService.getIdHospitalByUsuario(usuario);
		
		Optional<Hospital> hospitalOptional = hospitalService.getHospitalById(idHospital);
		
		if(!hospitalOptional.isPresent()) {
			return ErroHospital.erroHospitalIdNaoEnconrtrado(idHospital);
		}
		
		Hospital hospital = hospitalOptional.get();

		Optional<Paciente> optionalPaciente = hospitalService.getPacienteByCPF(cpfPaciente);
		if (optionalPaciente.isEmpty()) {
			return ErroHospital.erroHospitalPacienteNaoCadastrado(cpfPaciente);
		}
		Paciente paciente = optionalPaciente.get();
		
		hospitalService.adicionarPacienteRecuperado(hospital, paciente);
		hospitalService.salvarHospitalCadastrado(hospital);
		
		return new ResponseEntity<String>("Cadastro do paciente que se recuperou realizada com sucesso!", HttpStatus.OK);
	}
}