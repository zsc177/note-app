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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.sdo.borachok.noteapp.model.note.Note;
import com.sdo.borachok.noteapp.security.model.AuthenticatedUser;
import com.sdo.borachok.noteapp.service.NoteService;

@Controller
@RequestMapping("/api/v1/notes")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@RequestMapping(method = GET)
	public ResponseEntity<List<Note>> getAll(@AuthenticationPrincipal AuthenticatedUser activeUser) {

		List<Note> notes = noteService.getByAuthorId(activeUser.getId());

		return new ResponseEntity<List<Note>>(notes, OK);
	}

	@RequestMapping(value = "/{id}", method = GET)
	@PreAuthorize("@securityService.canAccessNote(#activeUser, #id)")
	public ResponseEntity<Note> get(@PathVariable("id") Integer id,
			@AuthenticationPrincipal AuthenticatedUser activeUser) {

		Note note = noteService.getById(id);

		return new ResponseEntity<Note>(note, OK);
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	@PreAuthorize("@securityService.canAccessNote(#activeUser, #id)")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id,
			@AuthenticationPrincipal AuthenticatedUser activeUser) {

		noteService.delete(id);

		return new ResponseEntity<Void>(NO_CONTENT);
	}

	@RequestMapping(method = DELETE)
	public ResponseEntity<Void> deleteAll(@AuthenticationPrincipal AuthenticatedUser activeUser) {

		noteService.deleteAll(activeUser.getId());

		return new ResponseEntity<Void>(NO_CONTENT);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<Note> create(@RequestBody @Valid Note note, UriComponentsBuilder ucBuilder,
			@AuthenticationPrincipal AuthenticatedUser activeUser) {

		note = noteService.create(activeUser.getId(), note);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("api/v1/{noteId}").buildAndExpand(note.getId()).toUri());

		return new ResponseEntity<Note>(note, headers, CREATED);
	}

	@PreAuthorize("@securityService.canAccessNote(#activeUser, #id)")
	@RequestMapping(value = "/{id}", method = PUT)
	public ResponseEntity<Note> update(@PathVariable("id") Integer id, @RequestBody @Valid Note note,
			@AuthenticationPrincipal AuthenticatedUser activeUser) {

		noteService.update(id, note);

		return new ResponseEntity<Note>(note, OK);
	}
}
