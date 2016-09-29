package com.sdo.borachok.noteapp.service;

import java.util.List;

import com.sdo.borachok.noteapp.model.person.Person;

public interface PersonService {

	public List<Person> getAll();

	public Person getById(Integer id);

	public Person getByEmail(String email);

	public void delete(Integer id);

	public void deleteAll();

	public Person create(Person person);

	public Person update(Integer id, Person person);

	public boolean isExist(Person person);

}
