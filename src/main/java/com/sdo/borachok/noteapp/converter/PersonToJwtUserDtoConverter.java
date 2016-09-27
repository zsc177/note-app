package com.sdo.borachok.noteapp.converter;

import com.sdo.borachok.noteapp.model.person.Person;
import com.sdo.borachok.noteapp.security.transfer.JwtUserDto;

public interface PersonToJwtUserDtoConverter {

	public JwtUserDto convert(Person person);
}
