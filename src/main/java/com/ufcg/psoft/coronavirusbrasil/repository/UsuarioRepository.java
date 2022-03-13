package com.ufcg.psoft.coronavirusbrasil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.coronavirusbrasil.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByUsernameAndPassword(String username, String password);
	Optional<Usuario> findByUsername(String username);
}
