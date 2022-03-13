package com.ufcg.psoft.coronavirusbrasil.service;

import org.springframework.stereotype.Service;

import com.ufcg.psoft.coronavirusbrasil.DTO.HospitalDTO;
import com.ufcg.psoft.coronavirusbrasil.model.Hospital;
import com.ufcg.psoft.coronavirusbrasil.model.Usuario;

@Service
public interface UsuarioService {
	public void salvarUsuario(Usuario usuario);
	public Usuario criarUsuarioHospital(String tipo, HospitalDTO hospitalDTO, Hospital hospital);
}
