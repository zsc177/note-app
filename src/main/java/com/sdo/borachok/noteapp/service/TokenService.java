package com.sdo.borachok.noteapp.service;

import com.sdo.borachok.noteapp.model.person.Person;

public interface TokenService {

	public String generateToken(Person person);

}