package com.ufcg.psoft.coronavirusbrasil.erro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufcg.psoft.coronavirusbrasil.DTO.HospitalDTO;

public class ErroHospital {

	static final String LISTA_HOSPITAL_VAZIA = "Sem hospitais cadastrados";
	
	static final String Hospital_CNPJ_NAO_CASTRADO = "Hospital com cnpj %s não está cadastrado";
	
	static final String Hospital_ID_NAO_CASTRADO = "Hospital com id %s não está cadastrado";
	
	static final String Hospital_JA_CADASTRADO = "O Hospital com cnpj %s já esta cadastrado";

	static final String PACIENTE_NAO_CADASTRADO = "O Paciente com o cpf %s não está cadastrado";
	
	static final String IMPOSSIVEL_ADICIONAR_PACIENTE_AO_LEITO = "Os leitos do Hospital estão todos ocupados";
	
	static final String IMPOSSIVEL_ATUALIZAR_LEITO = "a quantidade de leitos suportados não pode ser menor que zero";

	public static ResponseEntity<?> erroListaHospitaisVazia() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroHospital.LISTA_HOSPITAL_VAZIA),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroHospitalCnpjNaoEnconrtrado(long cnpj) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroHospital.Hospital_CNPJ_NAO_CASTRADO, cnpj)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroHospitalIdNaoEnconrtrado(long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroHospital.Hospital_ID_NAO_CASTRADO, id)),
				HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<CustomErrorType> erroHospitalPacienteNaoCadastrado(long cpf) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroHospital.PACIENTE_NAO_CADASTRADO, cpf)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<?> erroHospitalJaCadastrado(HospitalDTO hospitalDTO) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroHospital.Hospital_JA_CADASTRADO,
				hospitalDTO.getCNPJ())), HttpStatus.CONFLICT);
	}
	
	public static ResponseEntity<?> erroImpossivelAdicionarPacienteLeito() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroHospital.IMPOSSIVEL_ADICIONAR_PACIENTE_AO_LEITO),
				HttpStatus.CONFLICT);
	}
	
	public static ResponseEntity<?> erroImpossivelatualizarLeito() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroHospital.IMPOSSIVEL_ATUALIZAR_LEITO),
				HttpStatus.BAD_REQUEST);
	}
}
