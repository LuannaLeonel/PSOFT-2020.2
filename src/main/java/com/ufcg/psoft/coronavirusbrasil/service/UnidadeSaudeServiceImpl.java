package com.ufcg.psoft.coronavirusbrasil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.coronavirusbrasil.DTO.UnidadeSaudeDTO;
import com.ufcg.psoft.coronavirusbrasil.model.UnidadeSaude;
import com.ufcg.psoft.coronavirusbrasil.repository.UnidadeSaudeRepository;

@Service
public class UnidadeSaudeServiceImpl implements UnidadeSaudeService {

    @Autowired
    private UnidadeSaudeRepository unidadeSaudeRepository;

    @Override
    public Optional<UnidadeSaude> getUnidadeSaudeById(Long id) {
        return unidadeSaudeRepository.findById(id);
    }

    @Override
    public Optional<UnidadeSaude> getUnidadeSaudeByCNPJ(Long cnpj) {
        return unidadeSaudeRepository.findByCnpj(cnpj);
    }

    @Override
    public void removerUnidadeSaudeCadastrado(UnidadeSaude unidadeSaude) {
        unidadeSaudeRepository.delete(unidadeSaude);
    }

    @Override
    public void salvarUnidadeSaudeCadastrado(UnidadeSaude unidadeSaude) {
        unidadeSaudeRepository.save(unidadeSaude);
    }

    @Override
    public List<UnidadeSaude> listarUnidadesSaude() {
        return unidadeSaudeRepository.findAll();
    }

    @Override
    public UnidadeSaude criaUnidadeSaude(UnidadeSaudeDTO unidadeSaudeDTO) {
    	String nome = unidadeSaudeDTO.getNome();
    	String endereco = unidadeSaudeDTO.getEndereco();
    	
    	Long cnpj = unidadeSaudeDTO.getCNPJ();
    	Long cep = unidadeSaudeDTO.getCep();
    	
        UnidadeSaude unidadeSaude = new UnidadeSaude(nome, endereco, cnpj, cep);

        salvarUnidadeSaudeCadastrado(unidadeSaude);

        return unidadeSaude;
    }

    @Override
    public UnidadeSaude atualizaUnidadeSaude(UnidadeSaudeDTO unidadeSaudeDTO, UnidadeSaude unidadeSaude) {
        unidadeSaude.setEndereco(unidadeSaudeDTO.getEndereco());
        unidadeSaude.setNome(unidadeSaudeDTO.getNome());
        unidadeSaude.setCNPJ(unidadeSaudeDTO.getCNPJ());
        unidadeSaude.setCep(unidadeSaudeDTO.getCep());

        salvarUnidadeSaudeCadastrado(unidadeSaude);

        return unidadeSaude;
    }
}
