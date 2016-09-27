package com.sdo.borachok.noteapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdo.borachok.noteapp.model.note.Note;
import com.sdo.borachok.noteapp.repository.NoteRepository;
import com.sdo.borachok.noteapp.service.NoteService;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepository;

	@Override
	public List<Note> getAll() {
		return noteRepository.findAll();
	}
	
	public List<Note> getByAuthorId(Integer id) {
		return noteRepository.findByAuthorId(id);
	}

	@Override
	public Note getById(Integer id) {
		return noteRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		noteRepository.delete(id);

	}

	@Override
	public void deleteAll() {
		noteRepository.deleteAll();

	}

	@Override
	public Note create(Note note) {
		return noteRepository.saveAndFlush(note);
	}

	@Override
	public Note update(Note note) {
		return noteRepository.saveAndFlush(note);
	}

}
