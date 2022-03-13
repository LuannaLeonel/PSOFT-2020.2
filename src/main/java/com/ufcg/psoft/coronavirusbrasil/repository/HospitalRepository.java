package com.ufcg.psoft.coronavirusbrasil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.psoft.coronavirusbrasil.model.Hospital;
import com.ufcg.psoft.coronavirusbrasil.model.Usuario;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>{

	Optional<Hospital> findByCnpj(Long cnpj);
	
	Optional<Hospital> findByUsuario(Usuario usuario);	
}
