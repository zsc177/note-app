package com.sdo.borachok.noteapp.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sdo.borachok.noteapp.model.note.Note;
import com.sdo.borachok.noteapp.service.NoteService;

@Controller
@RequestMapping("/api/v1/persons")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@PreAuthorize("#id == principal.id")
	@RequestMapping(value = "/{id}/notes", method = GET)
	public ResponseEntity<List<Note>> getAll(@PathVariable("id") Integer id) {

		List<Note> notes = noteService.getByAuthorId(id);

		if (notes == null || notes.isEmpty()) {
			return new ResponseEntity<List<Note>>(NOT_FOUND);
		}

		return new ResponseEntity<List<Note>>(notes, OK);
	}

	// ------------------- Get note
	// --------------------------------------------------------
	// @RequestMapping(value = "/{id}", method = GET)
	// public ResponseEntity<Person> get(@PathVariable("id") Integer id) {
	//
	// Person person = personService.getById(id);
	//
	// if (person == null) {
	// return new ResponseEntity<Person>(NOT_FOUND);
	// }
	//
	// return new ResponseEntity<Person>(person, OK);
	// }
}
