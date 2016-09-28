package com.sdo.borachok.noteapp.model.person.exception;

public class EmailAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_EMAIL_ALREADY_EXIST = "Email already exist";

	public EmailAlreadyExistException() {
		super(MESSAGE_EMAIL_ALREADY_EXIST);
	}

	@Override
	public String getMessage() {
		return MESSAGE_EMAIL_ALREADY_EXIST;
	}
}
