package com.sdo.borachok.noteapp.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sdo.borachok.noteapp.converter.PersonToJwtUserDtoConverter;
import com.sdo.borachok.noteapp.model.person.Person;
import com.sdo.borachok.noteapp.security.transfer.JwtUserDto;
import com.sdo.borachok.noteapp.security.util.JwtTokenGenerator;
import com.sdo.borachok.noteapp.service.PersonService;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final String SECRET = "note-app-secret-code";

	@Autowired
	private PersonService personService;

	@Autowired
	private PersonToJwtUserDtoConverter converter;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> login(@RequestBody Person person) {

		Person currentPerson = personService.getByEmail(person.getEmail());

		if (currentPerson == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}

		if (!currentPerson.getPassword().equals(person.getPassword())) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		JwtUserDto userDto = converter.convert(currentPerson);

		String stringToken = JwtTokenGenerator.generateToken(userDto, SECRET);
		String jsonToken = "{\"token\":\"" + stringToken + "\"}";

		return new ResponseEntity<String>(jsonToken, OK);
	}
}
