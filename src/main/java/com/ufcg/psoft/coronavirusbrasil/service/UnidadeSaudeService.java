package com.ufcg.psoft.coronavirusbrasil.service;

import com.ufcg.psoft.coronavirusbrasil.DTO.UnidadeSaudeDTO;
import com.ufcg.psoft.coronavirusbrasil.model.UnidadeSaude;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UnidadeSaudeService {

    public Optional<UnidadeSaude> getUnidadeSaudeById(Long id);

    public Optional<UnidadeSaude> getUnidadeSaudeByCNPJ(Long cnpj);

    public void removerUnidadeSaudeCadastrado(UnidadeSaude unidadeSaude);

    public void salvarUnidadeSaudeCadastrado(UnidadeSaude unidadeSaude);

    public List<UnidadeSaude> listarUnidadesSaude();

    public UnidadeSaude criaUnidadeSaude(UnidadeSaudeDTO unidadeSaudeDTO);

    public UnidadeSaude atualizaUnidadeSaude(UnidadeSaudeDTO unidadeSaudeDTO, UnidadeSaude unidadeSaude);
}
