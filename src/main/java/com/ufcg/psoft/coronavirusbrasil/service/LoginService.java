package com.ufcg.psoft.coronavirusbrasil.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.ufcg.psoft.coronavirusbrasil.DTO.LoginDTO;
import com.ufcg.psoft.coronavirusbrasil.model.Usuario;

public interface LoginService {
	public String login(LoginDTO loginDTO);
	public Authentication getAuthentication(HttpServletRequest request);
	public Usuario getAuthenticationIdByToken(String token);
}
