package com.ufcg.psoft.coronavirusbrasil.erro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufcg.psoft.coronavirusbrasil.DTO.FuncionarioDTO;

public class ErroFuncionario {
	
	static final String LISTA_FUNCIONARIO_VAZIA = "Sem funcionários cadastrados";
	
	static final String FUNCIONARIO_CPF_NAO_CASTRADO = "Funcionario com cpf %s não está cadastrado";
	
	static final String FUNCIONARIO_ID_NAO_CASTRADO = "Funcionario com id %s não está cadastrado";
	
	static final String FUNCIONARIO_JA_CADASTRADO = "O funcionario %s nome %s já esta cadastrado";

	public static ResponseEntity<?> erroListaFuncionariosVazia() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroFuncionario.LISTA_FUNCIONARIO_VAZIA),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroFuncionarioCpfNaoEnconrtrado(long cpf) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroFuncionario.FUNCIONARIO_CPF_NAO_CASTRADO, cpf)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroFuncionarioIdNaoEnconrtrado(long cpf) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroFuncionario.FUNCIONARIO_ID_NAO_CASTRADO, cpf)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<?> erroFuncionarioJaCadastrado(FuncionarioDTO funcionarioDTO) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroFuncionario.FUNCIONARIO_JA_CADASTRADO,
				funcionarioDTO.getCPF(), funcionarioDTO.getNome())), HttpStatus.CONFLICT);
	}
	
}
