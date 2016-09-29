package com.sdo.borachok.noteapp.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdo.borachok.noteapp.model.person.Person;
import com.sdo.borachok.noteapp.model.person.exception.IncorrectLoginOrPasswordException;
import com.sdo.borachok.noteapp.service.PersonService;
import com.sdo.borachok.noteapp.service.ValidateService;

@Service
@Transactional
public class ValidateServiceImpl implements ValidateService {

	@Autowired
	private PersonService personService;

	@Override
	public Person validatePerson(Person person) {

		Person validatedPerson = personService.getByEmail(person.getEmail());

		if (validatedPerson == null || !validatedPerson.getPassword().equals(person.getPassword())) {
			throw new IncorrectLoginOrPasswordException();
		}

		return validatedPerson;
	}

}
