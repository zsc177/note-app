package com.sdo.borachok.noteapp.model.person.exception;

import com.sdo.borachok.noteapp.model.api.ResourceNotFoundException;

public class PersonNotFoundException extends ResourceNotFoundException {

	private static final long serialVersionUID = 1L;

	private static final String PERSON_NOT_FOUND = "Person not found";

	public PersonNotFoundException() {
		super(PERSON_NOT_FOUND);
	}

	@Override
	public String getMessage() {
		return PERSON_NOT_FOUND;
	}
}
