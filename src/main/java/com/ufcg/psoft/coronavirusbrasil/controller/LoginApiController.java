package com.ufcg.psoft.coronavirusbrasil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.coronavirusbrasil.DTO.LoginDTO;
import com.ufcg.psoft.coronavirusbrasil.service.LoginService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginApiController {

	@Autowired
	LoginService loginService;

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
		String jwt = loginService.login(loginDTO);
		return new ResponseEntity<>(jwt, HttpStatus.OK);
	}
}