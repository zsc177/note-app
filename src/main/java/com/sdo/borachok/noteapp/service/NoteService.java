package com.sdo.borachok.noteapp.service;

import java.util.List;

import com.sdo.borachok.noteapp.model.note.Note;

public interface NoteService {

	public List<Note> getAll();

	public List<Note> getByAuthorId(Integer id);

	public Note getById(Integer id);

	public void delete(Integer id);

	public void deleteAll(Integer authorId);

	public Note create(Integer authorId, Note note);

	public Note update(Integer noteId, Note note);

}
