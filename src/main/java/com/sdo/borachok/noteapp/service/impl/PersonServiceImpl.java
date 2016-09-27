package com.sdo.borachok.noteapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdo.borachok.noteapp.model.person.Person;
import com.sdo.borachok.noteapp.repository.PersonRepository;
import com.sdo.borachok.noteapp.service.PersonService;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> getAll() {
		return personRepository.findAll();
	}

	@Override
	public Person getById(Integer id) {
		return personRepository.findOne(id);
	}

	@Override
	public Person getByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	@Override
	public void delete(Integer id) {
		personRepository.delete(id);

	}

	@Override
	public void deleteAll() {
		personRepository.deleteAll();

	}

	@Override
	public Person create(Person person) {
		return personRepository.saveAndFlush(person);
	}

	@Override
	public Person update(Person person) {
		return personRepository.saveAndFlush(person);
	}

	@Override
	public boolean isExist(Person person) {
		return getByEmail(person.getEmail()) != null;
	}

}
