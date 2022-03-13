package com.ufcg.psoft.coronavirusbrasil.erro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufcg.psoft.coronavirusbrasil.DTO.UnidadeSaudeDTO;

public class ErroUnidadeSaude {
	
	static final String LISTA_UNIDADE_SAUDE_VAZIA = "Sem unidades de saúde cadastradas";
	
	static final String UNIDADE_SAUDE_CNPJ_NAO_CASTRADO = "Unidade de Saude com cnpj %s não está cadastrado";
	
	static final String UNIDADE_SAUDE_ID_NAO_CASTRADO = "Unidade de Saude com id %s não está cadastrado";
	
	static final String UNIDADE_SAUDE_JA_CADASTRADO = "Unidade de Saude com cnpj %s já esta cadastrado";

	public static ResponseEntity<?> erroListaUnidadesSaudeVazia() {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroUnidadeSaude.LISTA_UNIDADE_SAUDE_VAZIA),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroUnidadeSaudeIdNaoEnconrtrada(long id) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroUnidadeSaude.UNIDADE_SAUDE_ID_NAO_CASTRADO, id)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<CustomErrorType> erroUnidadeSaudeCnpjNaoEnconrtrada(long cnpj) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroUnidadeSaude.UNIDADE_SAUDE_CNPJ_NAO_CASTRADO, cnpj)),
				HttpStatus.NOT_FOUND);
	}
	
	public static ResponseEntity<?> erroUnidadeSaudeCadastrado(UnidadeSaudeDTO unidadeSaudeDTO) {
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(String.format(ErroUnidadeSaude.UNIDADE_SAUDE_JA_CADASTRADO,
				unidadeSaudeDTO.getCNPJ())), HttpStatus.CONFLICT);
	}
}
