package com.sdo.borachok.noteapp.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.sdo.borachok.noteapp.model.person.Person;
import com.sdo.borachok.noteapp.service.PersonService;

@Controller
@RequestMapping("/api/v1/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PreAuthorize("hasAuthority('admin')")
	@RequestMapping(method = GET)
	public ResponseEntity<List<Person>> getAll() {

		List<Person> persons = personService.getAll();

		return new ResponseEntity<List<Person>>(persons, OK);
	}

	@PreAuthorize("hasAuthority('admin') OR #id == principal.id")
	@RequestMapping(value = "/{id}", method = GET)
	public ResponseEntity<Person> get(@PathVariable("id") Integer id) {

		Person person = personService.getById(id);

		return new ResponseEntity<Person>(person, OK);
	}

	@PreAuthorize("hasAuthority('admin') OR #id == principal.id")
	@RequestMapping(value = "/{id}", method = DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {

		personService.delete(id);

		return new ResponseEntity<Void>(NO_CONTENT);
	}

	@PreAuthorize("hasAuthority('admin')")
	@RequestMapping(method = DELETE)
	public ResponseEntity<Person> deleteAll() {

		personService.deleteAll();

		return new ResponseEntity<Person>(NO_CONTENT);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<Person> create(@RequestBody @Valid Person person, UriComponentsBuilder ucBuilder) {

		personService.create(person);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("api/v1/persons/{id}").buildAndExpand(person.getId()).toUri());

		return new ResponseEntity<Person>(person, headers, CREATED);
	}

	@PreAuthorize("hasAuthority('admin') OR #id == principal.id")
	@RequestMapping(value = "/{id}", method = PUT)
	public ResponseEntity<Person> update(@PathVariable("id") Integer id, @RequestBody @Valid Person person) {

		personService.update(id, person);

		return new ResponseEntity<Person>(person, OK);
	}

}
