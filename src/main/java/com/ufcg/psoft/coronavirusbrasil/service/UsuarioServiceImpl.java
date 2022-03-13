package com.ufcg.psoft.coronavirusbrasil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.coronavirusbrasil.DTO.HospitalDTO;
import com.ufcg.psoft.coronavirusbrasil.enums.UsuarioType;
import com.ufcg.psoft.coronavirusbrasil.model.Hospital;
import com.ufcg.psoft.coronavirusbrasil.model.Usuario;
import com.ufcg.psoft.coronavirusbrasil.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void salvarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario criarUsuarioHospital(String tipo, HospitalDTO hospitalDTO, Hospital hospital) {
        Usuario usuario = new Usuario(hospitalDTO.getNome(), hospitalDTO.getSenha());
        hospital.setUsuario(usuario);
        usuario.setTipo(UsuarioType.HOSPITAL);
        return usuario;
    }
}