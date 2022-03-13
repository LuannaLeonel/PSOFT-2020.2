package com.ufcg.psoft.coronavirusbrasil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.psoft.coronavirusbrasil.model.Funcionario;
import com.ufcg.psoft.coronavirusbrasil.model.UnidadeSaude;
import com.ufcg.psoft.coronavirusbrasil.DTO.FuncionarioDTO;
import com.ufcg.psoft.coronavirusbrasil.DTO.UnidadeSaudeDTO;
import com.ufcg.psoft.coronavirusbrasil.erro.ErroFuncionario;
import com.ufcg.psoft.coronavirusbrasil.erro.ErroUnidadeSaude;
import com.ufcg.psoft.coronavirusbrasil.service.UnidadeSaudeService;

/**
 * Controller responsável pela gerência de unidades de saúde
 */
@RestController
@RequestMapping("/api/unidade-saude")
@CrossOrigin
public class UnidadeSaudeApiController {

    @Autowired
    UnidadeSaudeService unidadeSaudeService;

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listarUnidadesSaude(@RequestHeader String Authorization) {

        List<UnidadeSaude> unidadesSaude = unidadeSaudeService.listarUnidadesSaude();

        if (unidadesSaude.isEmpty()) {
            return ErroUnidadeSaude.erroListaUnidadesSaudeVazia();
        }

        return new ResponseEntity<>(unidadesSaude, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/{cnpj}", method = RequestMethod.GET)
    public ResponseEntity<?> getUnidadeSaude(@RequestHeader String Authorization, @PathVariable("cnpj") long cnpj) {

        Optional<UnidadeSaude> optionalUnidadeSaude = unidadeSaudeService.getUnidadeSaudeByCNPJ(cnpj);

        if (optionalUnidadeSaude.isEmpty()) {
            return ErroUnidadeSaude.erroUnidadeSaudeCnpjNaoEnconrtrada(cnpj);
        }

        return new ResponseEntity<>(optionalUnidadeSaude.get(), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> criarUnidadeSaude(@RequestHeader String Authorization, @RequestBody UnidadeSaudeDTO unidadeSaudeDTO, UriComponentsBuilder ucBuilder) {
        Optional<UnidadeSaude> optionalUnidadeSaude = unidadeSaudeService.getUnidadeSaudeByCNPJ(unidadeSaudeDTO.getCNPJ());

        if (!optionalUnidadeSaude.isEmpty()) {
            return ErroUnidadeSaude.erroUnidadeSaudeCadastrado(unidadeSaudeDTO);
        }

        UnidadeSaude unidadeSaude = unidadeSaudeService.criaUnidadeSaude(unidadeSaudeDTO);

        return new ResponseEntity<>(unidadeSaude, HttpStatus.CREATED);

    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> atualizarUnidadeSaude(@RequestHeader String Authorization, @RequestBody UnidadeSaudeDTO unidadeSaudeDTO, @PathVariable("id") long id) {

        Optional<UnidadeSaude> optionalUnidadeSaude = unidadeSaudeService.getUnidadeSaudeById(id);

        if (optionalUnidadeSaude.isEmpty()) {
            return ErroUnidadeSaude.erroUnidadeSaudeIdNaoEnconrtrada(id);
        }

        return new ResponseEntity<>(unidadeSaudeService.atualizaUnidadeSaude(unidadeSaudeDTO, optionalUnidadeSaude.get()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @RequestMapping(value = "/{cnpj}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removerUnidadeSaude(@RequestHeader String Authorization, @PathVariable("cnpj") long cnpj) {

        Optional<UnidadeSaude> optionalUnidadeSaude = unidadeSaudeService.getUnidadeSaudeByCNPJ(cnpj);

        if (optionalUnidadeSaude.isEmpty()) {
            return ErroUnidadeSaude.erroUnidadeSaudeCnpjNaoEnconrtrada(cnpj);
        }

        unidadeSaudeService.removerUnidadeSaudeCadastrado(optionalUnidadeSaude.get());

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
