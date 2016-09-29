package com.sdo.borachok.noteapp.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdo.borachok.noteapp.model.note.Note;
import com.sdo.borachok.noteapp.model.note.NoteNotFoundException;
import com.sdo.borachok.noteapp.repository.NoteRepository;
import com.sdo.borachok.noteapp.service.NoteService;
import com.sdo.borachok.noteapp.service.PersonService;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private PersonService personService;

	@Override
	public List<Note> getAll() {

		List<Note> notes = noteRepository.findAll();

		if (notes == null || notes.isEmpty()) {
			throw new NoteNotFoundException();
		}

		return notes;
	}

	public List<Note> getByAuthorId(Integer id) {
		return noteRepository.findByAuthorId(id);
	}

	@Override
	public Note getById(Integer id) {

		Note note = noteRepository.findOne(id);

		if (note == null) {
			throw new NoteNotFoundException();
		}

		return note;
	}

	@Override
	public void delete(Integer id) {

		this.getById(id);

		noteRepository.delete(id);

	}

	@Override
	public void deleteAll() {
		noteRepository.deleteAll();

	}

	@Override
	public Note create(Integer personId, Note note) {

		note.setDate(LocalDate.now());
		note.setAuthor(personService.getById(personId));

		return noteRepository.saveAndFlush(note);
	}

	@Override
	public Note update(Integer noteId, Note note) {

		Note currentNote = this.getById(noteId);

		note.setId(currentNote.getId());

		return noteRepository.saveAndFlush(note);
	}

}
