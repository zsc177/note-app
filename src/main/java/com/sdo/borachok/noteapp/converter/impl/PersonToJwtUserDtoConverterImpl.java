package com.sdo.borachok.noteapp.converter.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdo.borachok.noteapp.converter.PersonToJwtUserDtoConverter;
import com.sdo.borachok.noteapp.model.person.Person;
import com.sdo.borachok.noteapp.security.transfer.JwtUserDto;

@Service
@Transactional
public class PersonToJwtUserDtoConverterImpl implements PersonToJwtUserDtoConverter {

	public JwtUserDto convert(Person person) {

		JwtUserDto userDto = new JwtUserDto();

		userDto.setId(person.getId());
		userDto.setEmail(person.getEmail());
		userDto.setPassword(person.getPassword());
		userDto.setRole(person.getRole());

		return userDto;
	}
}
