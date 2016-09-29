package com.sdo.borachok.noteapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdo.borachok.noteapp.model.person.Person;
import com.sdo.borachok.noteapp.model.person.exception.EmailAlreadyExistException;
import com.sdo.borachok.noteapp.model.person.exception.PersonNotFoundException;
import com.sdo.borachok.noteapp.repository.PersonRepository;
import com.sdo.borachok.noteapp.service.PersonService;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> getAll() {

		List<Person> persons = personRepository.findAll();

		if (persons == null || persons.isEmpty()) {
			throw new PersonNotFoundException();
		}

		return persons;
	}

	@Override
	public Person getById(Integer id) {

		Person person = personRepository.findOne(id);

		if (person == null) {
			throw new PersonNotFoundException();
		}

		return person;
	}

	@Override
	public Person getByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	@Override
	public void delete(Integer id) {

		this.getById(id);

		personRepository.delete(id);
	}

	@Override
	public void deleteAll() {
		personRepository.deleteAll();

	}

	@Override
	public Person create(Person person) {

		if (this.isExist(person)) {
			throw new EmailAlreadyExistException();
		}

		// TODO: make some logic here to avoid role changing by default user
		person.setRole("user");

		return personRepository.saveAndFlush(person);
	}

	@Override
	public Person update(Integer id, Person person) {

		Person currentPerson = this.getById(id);

		if (currentPerson == null) {
			throw new PersonNotFoundException();
		}

		if (this.isExist(person) && !person.getEmail().equals(currentPerson.getEmail())) {
			throw new EmailAlreadyExistException();
		}

		person.setId(currentPerson.getId());

		// TODO: make some logic here to avoid role changing by default user
		person.setRole("user");

		return personRepository.saveAndFlush(person);
	}

	@Override
	public boolean isExist(Person person) {
		return getByEmail(person.getEmail()) != null;
	}

}
