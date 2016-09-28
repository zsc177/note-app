package com.sdo.borachok.noteapp.model.person.exception;

public class IncorrectLoginOrPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String INCORRECT_LOGIN_OR_PASSWORD = "Incorrect login or password";

	public IncorrectLoginOrPasswordException() {
		super(INCORRECT_LOGIN_OR_PASSWORD);
	}

	@Override
	public String getMessage() {
		return INCORRECT_LOGIN_OR_PASSWORD;
	}
}
