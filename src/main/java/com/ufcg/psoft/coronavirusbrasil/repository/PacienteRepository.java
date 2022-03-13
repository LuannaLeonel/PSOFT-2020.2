package com.ufcg.psoft.coronavirusbrasil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.coronavirusbrasil.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	public Optional<Paciente> getPacienteByCpf(Long cpf);
}
