package com.ufcg.psoft.coronavirusbrasil.erro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErroLaboratorio {

    static final String LABORATORIO_NAO_CASTRADO = "Laboratório não encontrado";

    static final String LABORATORIO_JA_CADASTRADO = "O laboratorio já está cadastrado";

    public static ResponseEntity<CustomErrorType> erroLaboratorioNaoEnconrtrado() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroLaboratorio.LABORATORIO_NAO_CASTRADO),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<?> erroLaboratorioJaCadastrado() {
        return new ResponseEntity<CustomErrorType>(new CustomErrorType(ErroLaboratorio.LABORATORIO_JA_CADASTRADO), HttpStatus.CONFLICT);
    }
}
