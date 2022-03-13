package com.ufcg.psoft.coronavirusbrasil.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.ufcg.psoft.coronavirusbrasil.service.LoginService;

public class JWTAuthenticationFilter extends GenericFilterBean {

    private final LoginService loginService;

    public JWTAuthenticationFilter(ApplicationContext ctx) {
        this.loginService = ctx.getBean(LoginService.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
    	
        Authentication authentication = loginService.getAuthentication((HttpServletRequest) request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}