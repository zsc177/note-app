package com.sdo.borachok.noteapp.model.note;

import com.sdo.borachok.noteapp.model.api.ResourceNotFoundException;

public class NoteNotFoundException extends ResourceNotFoundException {

	private static final long serialVersionUID = 1L;

	private static final String NOTE_NOT_FOUND = "Note not found";

	public NoteNotFoundException() {
		super(NOTE_NOT_FOUND);
	}

	@Override
	public String getMessage() {
		return NOTE_NOT_FOUND;
	}
}
