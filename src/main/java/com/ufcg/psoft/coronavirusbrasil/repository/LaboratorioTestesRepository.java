package com.ufcg.psoft.coronavirusbrasil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufcg.psoft.coronavirusbrasil.model.LaboratorioTestes;
import com.ufcg.psoft.coronavirusbrasil.model.Usuario;

@Repository
public interface LaboratorioTestesRepository extends JpaRepository<LaboratorioTestes, Long> {

    public boolean existsByNome(String nome);

    public Optional<LaboratorioTestes> findByCnpj(Long cnpj);
    
    public Optional<LaboratorioTestes> findByUsuario(Usuario usuario);
}
