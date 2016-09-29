package com.sdo.borachok.noteapp.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sdo.borachok.noteapp.model.person.Person;
import com.sdo.borachok.noteapp.service.TokenService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private TokenService tokenService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> login(@RequestBody Person person) {

		String token = tokenService.generateToken(person);

		return new ResponseEntity<String>(token, OK);
	}
}