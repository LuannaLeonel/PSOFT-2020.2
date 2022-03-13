package com.ufcg.psoft.coronavirusbrasil.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.coronavirusbrasil.DTO.LoginDTO;
import com.ufcg.psoft.coronavirusbrasil.model.Usuario;
import com.ufcg.psoft.coronavirusbrasil.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	static final long EXPIRATION_TIME = 86_400_000;
    static final String SECRET = "16bd8dfba2191761b65d5a795806e530";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

	@Override
	public String login(LoginDTO loginDTO) {
		
		String username = loginDTO.getUsername();
		String password = loginDTO.getPassword();
		
		Optional<Usuario> usuario = usuarioRepository.findByUsernameAndPassword(username, password);
		
		if (!usuario.isPresent()) {
			return "ERRO";
		}
		
        return createToken(usuario.get().getUsername());
	}
	
	@Override
    public Authentication getAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER_STRING);
		
		   if (token != null) {
	            String login = Jwts.parser()
	                    .setSigningKey(SECRET)
	                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
	                    .getBody()
	                    .getSubject();

	            if (login != null) {
	                return getUsernamePasswordRoles(login);
	            }

	        }
	        return null;
    }
	
	@Override
	public Usuario getAuthenticationIdByToken(String token) {
		String username = this.getNameByToken(token);
		return usuarioRepository.findByUsername(username).get();
	}
	
	private String getNameByToken(String token) {
		
		   if (token != null) {
	            String username = Jwts.parser()
	                    .setSigningKey(SECRET)
	                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
	                    .getBody()
	                    .getSubject();

	            if (username != null) {
	                return username;
	            }

	        }
	        return null;
	}
	
    private String createToken(String login) {
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    
    private UsernamePasswordAuthenticationToken getUsernamePasswordRoles(String username) {
    	Optional<Usuario> user = usuarioRepository.findByUsername(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.get().getTipo()));
        return new UsernamePasswordAuthenticationToken(user.get().getTipo(), null, authorities);

    }

	
}