package com.ufcg.psoft.coronavirusbrasil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.psoft.coronavirusbrasil.model.UnidadeSaude;

@Repository
public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, Long> {

	Optional<UnidadeSaude> findByCnpj(Long cnpj);
	
}

