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

import com.sdo.borachok.noteapp.model.note.Note;
import com.sdo.borachok.noteapp.service.NoteService;

@Controller
@RequestMapping("/api/v1/persons")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@PreAuthorize("#personId == principal.id")
	@RequestMapping(value = "/{personId}/notes", method = GET)
	public ResponseEntity<List<Note>> getAll(@PathVariable("personId") Integer personId) {

		List<Note> notes = noteService.getByAuthorId(personId);

		return new ResponseEntity<List<Note>>(notes, OK);
	}

	@PreAuthorize("#personId == principal.id")
	@RequestMapping(value = "/{personId}/notes/{noteId}", method = GET)
	public ResponseEntity<Note> get(@PathVariable("personId") Integer personId,
			@PathVariable("noteId") Integer noteId) {

		Note note = noteService.getById(noteId);

		return new ResponseEntity<Note>(note, OK);
	}

	@PreAuthorize("#personId == principal.id")
	@RequestMapping(value = "/{personId}/notes/{noteId}", method = DELETE)
	public ResponseEntity<Void> delete(@PathVariable("personId") Integer personId,
			@PathVariable("noteId") Integer noteId) {

		noteService.delete(noteId);

		return new ResponseEntity<Void>(NO_CONTENT);
	}

	@PreAuthorize("#personId == principal.id")
	@RequestMapping(value = "/{personId}/notes", method = DELETE)
	public ResponseEntity<Void> deleteAll() {

		noteService.deleteAll();

		return new ResponseEntity<Void>(NO_CONTENT);
	}

	@PreAuthorize("#personId == principal.id")
	@RequestMapping(value = "/{personId}/notes", method = POST)
	public ResponseEntity<Note> create(@PathVariable("personId") Integer personId, @RequestBody @Valid Note note,
			UriComponentsBuilder ucBuilder) {

		note = noteService.create(personId, note);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("api/v1/persons/{personId}/notes/{noteId}")
				.buildAndExpand(personId, note.getId()).toUri());

		return new ResponseEntity<Note>(note, headers, CREATED);
	}

	@PreAuthorize("#personId == principal.id")
	@RequestMapping(value = "/{personId}/notes/{noteId}", method = PUT)
	public ResponseEntity<Note> update(@PathVariable("personId") Integer personId,
			@PathVariable("noteId") Integer noteId, @RequestBody @Valid Note note) {

		noteService.update(noteId, note);

		return new ResponseEntity<Note>(note, OK);
	}
}
