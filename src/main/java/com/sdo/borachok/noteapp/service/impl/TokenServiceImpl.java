package com.sdo.borachok.noteapp.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdo.borachok.noteapp.converter.PersonToJwtUserDtoConverter;
import com.sdo.borachok.noteapp.model.person.Person;
import com.sdo.borachok.noteapp.security.transfer.JwtUserDto;
import com.sdo.borachok.noteapp.security.util.JwtTokenGenerator;
import com.sdo.borachok.noteapp.service.TokenService;
import com.sdo.borachok.noteapp.service.ValidateService;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

	private static final String SECRET = "note-app-secret-code";

	@Autowired
	private PersonToJwtUserDtoConverter converter;

	@Autowired
	private ValidateService validateService;

	@Override
	public String generateToken(Person person) {

		Person validatedPerson = validateService.validatePerson(person);

		String token = creatTokenFromPerson(validatedPerson);

		return token;
	}

	private String creatTokenFromPerson(Person person) {

		JwtUserDto userDto = converter.convert(person);

		String stringToken = JwtTokenGenerator.generateToken(userDto, SECRET);
		String jsonToken = "{\"token\":\"" + stringToken + "\"}";

		return jsonToken;
	}

}
